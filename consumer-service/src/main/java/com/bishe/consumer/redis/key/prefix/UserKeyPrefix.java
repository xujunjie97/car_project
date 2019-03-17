package com.bishe.consumer.redis.key.prefix;


import com.bishe.consumer.redis.key.BasePrefix;


public class UserKeyPrefix extends BasePrefix {

    public UserKeyPrefix(String prefix) {
        super(prefix);
    }

    public UserKeyPrefix(String prefix, Integer expireSeconds) {
        super(expireSeconds, prefix);
    }

    public static UserKeyPrefix getById = new UserKeyPrefix("id");

    public static UserKeyPrefix getByName = new UserKeyPrefix("name");

    /**
     * token的保存时间为2天
     */
    private static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public static UserKeyPrefix token = new UserKeyPrefix("token", TOKEN_EXPIRE);

    public static UserKeyPrefix test = new UserKeyPrefix("test", 60);


}
