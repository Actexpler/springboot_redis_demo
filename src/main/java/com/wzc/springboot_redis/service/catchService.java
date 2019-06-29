package com.wzc.springboot_redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class catchService {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    public void set(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }
    public void set(String key, Object value, long timeout) {
        set(key,value,timeout, TimeUnit.MINUTES);
    }
    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }

}
