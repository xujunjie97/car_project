package com.bishe.consumer.service;

import com.alibaba.fastjson.JSON;

import com.bishe.consumer.redis.key.KeyPrefix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Objects;


@Service
@Slf4j
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String valueStr = bean2Str(value);
            if (Objects.isNull(valueStr) || valueStr.length() <= 0) {
                return false;
            }

            int expires = prefix.expireSeconds();
            if (expires <= 0) {
                // 永不过期
                jedis.set(getRealKey(prefix, key), valueStr);
            } else {
                jedis.setex(getRealKey(prefix, key), expires, valueStr);
            }
            return true;
        } finally {
            returnToPoll(jedis);
        }
    }

    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.get(getRealKey(prefix, key));
            log.info("real key = {}", getRealKey(prefix, key));
            T result = str2Bean(value, clazz);
            log.info("result = {}", result);
            return result;
        } finally {
            returnToPoll(jedis);
        }
    }

    public Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(getRealKey(prefix, key));
        } finally {
            returnToPoll(jedis);
        }
    }

    public Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 如果值类型有误 则设置为0 然后再减1
            return jedis.decr(getRealKey(prefix, key));
        } finally {
            returnToPoll(jedis);
        }
    }

    public boolean exist(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Boolean isExist = jedis.exists(getRealKey(prefix, key));
            return isExist;
        } finally {
            returnToPoll(jedis);
        }
    }

    public static <T> String bean2Str(T value) {
        if (Objects.isNull(value)) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return value + "";
        } else if (clazz == String.class) {
            return (String) value;
        }
        // TODO: 2018/1/29  其他类型的判断
        return JSON.toJSONString(value);
    }

    public static <T> T str2Bean(String value, Class<T> clazz) {
        if (Objects.isNull(clazz) || Objects.isNull(value)) {
            return null;
        }

        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(value);
        } else if (clazz == String.class) {
            return (T) value;
        }
        // TODO: 2018/1/29  其他类型的校验
        return JSON.parseObject(value, clazz);
    }

    private void returnToPoll(Jedis jedis) {
        if (Objects.nonNull(jedis)) {
            // 返回给redis连接池
            jedis.close();
        }
    }

    private String getRealKey(KeyPrefix prefix, String key) {
        return prefix.getPrefix() + "." + key;
    }

    public static void main(String[] args) {
        Integer integer = str2Bean("1", Integer.class);
        System.out.println(integer);
    }

}
