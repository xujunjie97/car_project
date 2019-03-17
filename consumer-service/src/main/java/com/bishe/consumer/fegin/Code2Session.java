package com.bishe.consumer.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weixin",url = "https://api.weixin.qq.com",fallback = Code2SessionFallBackFactory.class)
public interface Code2Session {

    @RequestMapping(value = "/sns/jscode2session",method = RequestMethod.GET)
    ResponseEntity<String> getOpenId(@RequestParam("appid") String appid,
                             @RequestParam("secret") String secret,
                             @RequestParam("js_code") String jsCode,
                             @RequestParam("grant_type") String grantType);
}
