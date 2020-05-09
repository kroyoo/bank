package com.kenyon.banksys.service.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.xml.ws.Service;

/**
 * @author Kenyon
 * @title: ServiceRedisApplication
 * @projectName bank
 * @description: TODO
 * @date 9/9/20196:25 AM
 */
@SpringBootApplication
@EnableEurekaClient
public class ServiceRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRedisApplication.class, args);
    }
}
