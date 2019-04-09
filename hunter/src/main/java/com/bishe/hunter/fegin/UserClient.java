package com.bishe.hunter.fegin;

import com.bishe.hunter.utils.BaseRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "consumer-user",fallback = UserClientFallBack.class)
public interface UserClient {

    @RequestMapping(value = "/unBindByAdmin",method = RequestMethod.POST)
    BaseRes<Object> changeUserBind(String openId);

}
