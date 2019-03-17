package com.bishe.consumer.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bishe.consumer.fegin.Code2Session;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@PropertySource({"classpath:weixin.properties"})
public class UserController {

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


    @RequestMapping(value = "/onLogin")
    public JSONObject onLogin(String code){
        JSONObject resultJson = new JSONObject();
        ResponseEntity<String> result = code2Session.getOpenId(appid,secret,code,grantType);
        JSONObject res = JSONObject.parseObject(result.getBody());
        if(res == null){
            resultJson.put("errmsg","请求微信错误");
            return resultJson;
        }
        int errCode = res.getIntValue("errcode");
        if(result.getStatusCode()== HttpStatus.OK){

            if(0 == errCode){
                String openId = res.getString("openid");
                String sessionKey = res.getString("session_key");
                log.info("openId={}",openId);
                log.info("session_key={}",sessionKey);
                String userSessionId = setSession(openId,sessionKey);
                resultJson.put("sessionId",userSessionId);


            }else {
                resultJson.put("errmsg",res.getString( "errmsg"));
            }
            return resultJson;

        }
        resultJson.put("errcode",res.getIntValue("errcode"));
        resultJson.put("errmsg",res.getString("errmsg"));
        log.info("result={}",result);
        return resultJson;

    }

    @RequestMapping(value = "/session")
    public String setSession(String openid,String sessionKey){
        String userSession = openid+"_"+sessionKey;
        String userSessionId = String.valueOf(System.currentTimeMillis());
        UserKeyPrefix key = new UserKeyPrefix("user",60);

        redisService.set(key,userSessionId,userSession);
        return userSessionId;
    }

    @RequestMapping(value = "/userInfo")
    public BaseRes hello(String userSession, String userinfo) {
        JSONObject jsonObject = new JSONObject();
        String openId = userSession.split("_")[0];
        JSONObject userInfo = (JSONObject) JSONObject.parse(userinfo);
        String nicName = userInfo.getString("nickName");
        String gender = userInfo.getIntValue("gender") == 1 ? "man" : "woman";
        String city = userInfo.getString("city");
        String country = userInfo.getString("country");
        return BaseResUtil.success();
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
