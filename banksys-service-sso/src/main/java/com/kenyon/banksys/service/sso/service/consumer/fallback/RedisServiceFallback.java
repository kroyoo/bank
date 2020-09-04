package com.kenyon.banksys.service.sso.service.consumer.fallback;

import com.kenyon.banksys.common.hystrix.Fallback;
import com.kenyon.banksys.service.sso.service.consumer.RedisService;
import org.springframework.stereotype.Component;

/**
 * @author Kenyon
 * @title: RedisServiceFallback
 * @projectName bank
 * @description: TODO
 * @date 9/9/20197:47 PM
 */

@Component
public class RedisServiceFallback implements RedisService{
    @Override
    public String put(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public boolean remove(String key) {
        return false;
    }
}
