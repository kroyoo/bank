package com.kenyon.banksys.service.redis.controller;

import com.kenyon.banksys.service.redis.service.ServiceRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kenyon
 * @title: RedisController
 * @projectName bank
 * @description: TODO
 * @date 9/9/20196:55 AM
 */

@RestController
public class RedisController {

    @Autowired
    private ServiceRedis serviceRedis;

    @RequestMapping(value = "put", method = RequestMethod.POST)
    public String put(String key, String value, long seconds) {
        serviceRedis.put(key, value, seconds);
        return "ok";
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String get(String key) {
        Object obj = serviceRedis.get(key);
        if (obj != null) {
            String json = String.valueOf(obj);
            return json;
        }
        return null;
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public boolean remove(String key) {
        return serviceRedis.remove(key);
    }
}
