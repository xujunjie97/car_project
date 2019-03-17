//package com.bishe.consumer.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//
//import java.net.UnknownHostException;
//
//@Configuration
//public class RedisConfiguration {
//
//
//
////    @Bean
////    public RedisTemplate<Object, Object> myRedisTemplate(
////            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
////        RedisTemplate<Object, Object> template = new RedisTemplate<>();
////        template.setConnectionFactory(redisConnectionFactory);
////        Jackson2JsonRedisSerializer<Object> jr = new Jackson2JsonRedisSerializer<>(Object.class);
////        template.setDefaultSerializer(jr);
////        return template;
////    }
//
////    自定义序列化方法
////    @Bean
////    public RedisCacheManager empRedisCacheManager(RedisTemplate<Object, Employee> template){
////       RedisCacheManager cacheManager = new RedisCacheManager(template);
////
////    }
//
//
//
//}
