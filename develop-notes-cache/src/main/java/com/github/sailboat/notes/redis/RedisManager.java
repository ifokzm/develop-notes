package com.github.sailboat.notes.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisManager {

    @Resource
    private RedisTemplate redisTemplate;

    public String get(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String val){
        redisTemplate.opsForValue().set(key, val);
    }

}
