package com.bishe.hunter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.bishe.hunter.dao")
public class HunterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HunterApplication.class, args);

	}

}
