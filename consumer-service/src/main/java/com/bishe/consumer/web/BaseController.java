package com.bishe.consumer.web;

import com.bishe.consumer.redis.key.prefix.UserKeyPrefix;
import com.bishe.consumer.service.RedisService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xujunjie
 */
public abstract class BaseController {

    @Autowired
    private RedisService redisService;

    public String getUser(String userSessionKey){
        return redisService.get(new UserKeyPrefix("user"), userSessionKey, String.class);
    }

    public void setCache(UserKeyPrefix key,String userSessionKey,String userSession){
        redisService.set(key, userSessionKey, userSession);
    }
}
