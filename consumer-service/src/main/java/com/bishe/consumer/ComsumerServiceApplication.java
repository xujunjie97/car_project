package com.bishe.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan(basePackages = "com.bishe.comsumer.dao")
@EnableDiscoveryClient//开启发现服务功能
@EnableFeignClients(basePackages = {"com.bishe.consumer"})
@ComponentScan("com.bishe.consumer")
public class ComsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComsumerServiceApplication.class, args);
	}

    @LoadBalanced//开启负载均衡机机制
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
