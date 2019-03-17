package com.bishe.consumer.redis.key.prefix;


import com.bishe.consumer.redis.key.BasePrefix;


public class AccessKey extends BasePrefix {

    public AccessKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }

}
