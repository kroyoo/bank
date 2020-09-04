package com.kenyon.banksys.web.admin;

import com.kenyon.banksys.web.admin.service.fallback.AdminServiceFallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Kenyon
 * @title: WebAdminApplication
 * @projectName bank
 * @description: TODO
 * @date 9/4/20194:05 AM
 */

@SpringBootApplication(scanBasePackages = "com.kenyon.banksys")
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = AdminServiceFallback.class)
public class WebAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class, args);
    }
}
