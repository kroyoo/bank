package com.kenyon.banksys.service.redis.service;

/**
 * @author Kenyon
 * @title: ServiceRedis
 * @projectName bank
 * @description: TODO
 * @date 9/9/20196:44 AM
 */
public interface ServiceRedis {

    public void put(String key, Object value, long seconds);

    public Object get(String key);

    public boolean remove(String key);
}
