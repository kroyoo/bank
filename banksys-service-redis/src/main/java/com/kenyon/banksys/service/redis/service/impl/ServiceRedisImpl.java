package com.kenyon.banksys.service.redis.service.impl;

import com.kenyon.banksys.service.redis.service.ServiceRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
/**
 * @author Kenyon
 * @title: ServiceRedisImpl
 * @projectName bank
 * @description: TODO
 * @date 9/9/20196:46 AM
 */
@Service
public class ServiceRedisImpl implements ServiceRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void put(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean remove(String key) {
        return redisTemplate.delete(key);
    }


}
