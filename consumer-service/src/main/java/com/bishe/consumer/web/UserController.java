package com.bishe.consumer.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bishe.consumer.entity.User;
import com.bishe.consumer.enums.ResultEnum;
import com.bishe.consumer.fegin.Code2Session;
import com.bishe.consumer.fegin.HunterClient;
import com.bishe.consumer.redis.key.prefix.UserKeyPrefix;
import com.bishe.consumer.service.RedisService;
import com.bishe.consumer.service.UserService;
import com.bishe.consumer.utils.BaseRes;
import com.bishe.consumer.utils.BaseResUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
@PropertySource({"classpath:weixin.properties"})
public class UserController {

    private final static String NOUSER = "NOUSER";

    private final static String NOCAR = "NOCAR";

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.grantType}")
    private String grantType;

    @Autowired
    private Code2Session code2Session;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private HunterClient hunterClient;


    @RequestMapping(value = "/onLogin")
    public JSONObject onLogin(String code) {
        JSONObject resultJson = new JSONObject();
        ResponseEntity<String> result = code2Session.getOpenId(appid, secret, code, grantType);
        JSONObject res = JSONObject.parseObject(result.getBody());
        if (res == null) {
            resultJson.put("errmsg", "请求微信错误");
            return resultJson;
        }
        int errCode = res.getIntValue("errcode");
        if (result.getStatusCode() == HttpStatus.OK) {

            if (0 == errCode) {
                String openId = res.getString("openid");
                String sessionKey = res.getString("session_key");
                log.info("openId={}", openId);
                log.info("session_key={}", sessionKey);
                //todo 判断用户与数据信息
//                User user =userService.getUser(openId);
//                if(Objects.isNull(user)){
//                    resultJson.put("errmsg","用户不存在");
//                    resultJson.put("code",-1);
//                    return resultJson;
//                }
                String userSessionId = setSession(openId, sessionKey);
                resultJson.put("sessionId", userSessionId);
                resultJson.put("code", 0);


            } else {
                resultJson.put("errmsg", res.getString("errmsg"));
            }
            return resultJson;

        }
        resultJson.put("errcode", res.getIntValue("errcode"));
        resultJson.put("errmsg", res.getString("errmsg"));
        log.info("result={}", result);
        return resultJson;

    }


    //    @RequestMapping(value = "/session")
    private String setSession(String openid, String sessionKey) {
        String userSession = openid + "_" + sessionKey;
        String userSessionId = String.valueOf(System.currentTimeMillis());
        UserKeyPrefix key = new UserKeyPrefix("user", 600);

        redisService.set(key, userSessionId, userSession);
        return userSessionId;
    }

    @RequestMapping(value = "/userInfo")
    public BaseRes userInfo(String userSessionKey, String userinfo) {
        String userSession = redisService.get(new UserKeyPrefix("user"), userSessionKey, String.class);
        if (StringUtils.isEmpty(userSession)) {
            return BaseResUtil.error(-1, "登陆信息失效");
        }
        JSONObject jsonObject = new JSONObject();
        String openId = userSession.split("_")[0];
        User user = userService.getUser(openId);
        if (Objects.isNull(user)) {
            JSONObject userInfo = (JSONObject) JSONObject.parse(userinfo);
            String nickName = userInfo.getString("nickName");
            String gender = userInfo.getIntValue("gender") == 1 ? "man" : "woman";
            String city = userInfo.getString("city");
            String country = userInfo.getString("country");
            user = User.builder().nickName(nickName).gender(gender).city(city).country(country).openId(openId).build();
            if (userService.setUser(user)) {
                return BaseResUtil.success();
//                return BaseResUtil.error(-1, "登陆信息失效");
            } else {
                return BaseResUtil.error(-2, "储存用户数据失败");
            }
        }

        return BaseResUtil.success();
//        return BaseResUtil.error(-1, "登陆信息失效");
    }

    @RequestMapping(value = "/getBill")
    public BaseRes getHistoryBill(String userSessionKey, String date) throws ParseException {
        String userSession = redisService.get(new UserKeyPrefix("user"), userSessionKey, String.class);
        String openId = userSession.split("_")[0];
        User user = userService.getUser(openId);
        if (null != user) {
            //todo 调用商店服务，获取数据
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
            Date endDate = sf.parse(date);
            System.out.println(endDate.getTime());
        }
        return BaseResUtil.success();
    }


    @PostMapping("/bindCar")
    public BaseRes bindCar(String userSessionKey,String carNum){

        String userSession = redisService.get(new UserKeyPrefix("user"), userSessionKey, String.class);
        if (StringUtils.isEmpty(userSession)) {
            return BaseResUtil.error(-1, "登陆信息失效");
        }
        JSONObject jsonObject = new JSONObject();
        String openId = userSession.split("_")[0];
        User user = userService.getUser(openId);

        if(Objects.isNull(user)){
            log.info("未登陆");
            return BaseResUtil.error(ResultEnum.ACCOUNT_NOT);
        }

        try {
            // 调用远程方法更新小车绑定状态
            BaseRes<Object> result = hunterClient.updateUserId(carNum,user.getOpenId());
            if(result.getCode() != 0){
                log.error("远程方法调用失败");
                return BaseResUtil.error(ResultEnum.RPC_ERROR);
            }
        }catch (Exception e){
            log.error("远程调用错误={}",e);
        }

        try {
            //调用本地方法更新用户绑定状态
            if(userService.updateCarNum(user.getOpenId(),carNum)){
                return BaseResUtil.success();
            }else {
                return BaseResUtil.error(ResultEnum.BIND_CAR_ERROR);
            }
        }catch (Exception e){
            log.error("本地用户绑定小车错误={}",e);
        }

        return BaseResUtil.error(ResultEnum.BIND_ERROR);
    }


    @PostMapping("/unBind")
    public BaseRes unBind(String userSessionKey){
        String userSession = redisService.get(new UserKeyPrefix("user"), userSessionKey, String.class);
        if (StringUtils.isEmpty(userSession)) {
            return BaseResUtil.error(-1, "登陆信息失效");
        }
        JSONObject jsonObject = new JSONObject();
        String openId = userSession.split("_")[0];
        User user = userService.getUser(openId);
        if(Objects.isNull(user)){
            log.info("解除绑定，出现异常");
            return BaseResUtil.error(ResultEnum.ACCOUNT_NOT);
        }

        try {
            // 调用远程方法更新小车绑定状态
            BaseRes<Object> result = hunterClient.updateUserId(user.getCarNum(),NOUSER);
            if(result.getCode() != 0){
                log.error("解除绑定，远程方法调用失败");
                return BaseResUtil.error(ResultEnum.RPC_ERROR);
            }
        }catch (Exception e){
            log.error("解除绑定，远程调用错误={}",e);
        }

        try {
            //调用本地方法更新用户绑定状态
            if(userService.updateCarNum(user.getOpenId(),NOCAR)){
                return BaseResUtil.success();
            }else {
                return BaseResUtil.error(ResultEnum.BIND_CAR_ERROR);
            }
        }catch (Exception e){
            log.error("解除绑定，本地用户绑定小车错误={}",e);
        }

        return BaseResUtil.error(ResultEnum.BIND_ERROR);

    }


//    @RequestMapping(value = "/session")
//    public String setSession(HttpServletRequest request){
//        String userSessionId = String.valueOf(System.currentTimeMillis());
//        request.getSession().setAttribute("user",userSessionId);
//        return userSessionId;
//    }
//
//    @RequestMapping(value = "/getSession")
//    public String getSession(HttpServletRequest request){
//
//        String userSession = (String) request.getSession().getAttribute("user");
//        System.out.println(userSession);return userSession;
//    }


}
