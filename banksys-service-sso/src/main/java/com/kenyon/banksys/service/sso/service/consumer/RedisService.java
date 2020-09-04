package com.kenyon.banksys.service.sso.service.consumer;

import com.kenyon.banksys.service.sso.service.consumer.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Kenyon
 * @title: RedisService
 * @projectName bank
 * @description: TODO
 * @date 9/9/20197:42 PM
 */

@FeignClient(value = "banksys-service-redis", fallback = RedisServiceFallback.class)
public interface RedisService {


    @RequestMapping(value = "put", method = RequestMethod.POST)
    public String put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value, @RequestParam(value = "seconds") long seconds);


    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "key") String key);

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public boolean remove(@RequestParam(value = "key") String key);
}
