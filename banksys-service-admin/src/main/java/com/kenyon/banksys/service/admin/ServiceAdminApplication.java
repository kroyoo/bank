package com.kenyon.banksys.service.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Kenyon
 * @title: ServiceAdminApplication
 * @projectName bank
 * @description: TODO
 * @date 9/4/20192:29 AM
 */

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.kenyon.banksys.service.admin.mapper")
public class ServiceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAdminApplication.class, args);
    }
}
