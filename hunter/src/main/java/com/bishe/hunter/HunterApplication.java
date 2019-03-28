package com.bishe.hunter;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.bishe.hunter.dao")
@EnableDistributedTransaction
public class HunterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HunterApplication.class, args);

	}

}
