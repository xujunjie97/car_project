package com.bishe.consumer.redis.key;


/**
 * @author xujunjie
 */
public interface KeyPrefix {

    /**
     * 过期时间设置
     *
     * @return
     */
    int expireSeconds();

    /**
     * key的前缀
     *
     * @return
     */
    String getPrefix();

}
