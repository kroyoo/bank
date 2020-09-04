package com.kenyon.banksys.web.admin.config;

import com.kenyon.banksys.web.admin.interceptor.WebAdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Kenyon
 * @title: WebAdminInterceptorConfig
 * @projectName bank
 * @description: TODO
 * @date 9/10/20198:08 PM
 */
@Configuration
public class WebAdminInterceptorConfig implements WebMvcConfigurer {

    @Bean
    WebAdminInterceptor webAdminInterceptor() {
        return new WebAdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webAdminInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**", "/test.html", "/test", "test");
    }
}
