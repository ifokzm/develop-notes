package com.github.sailboat.notes.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisManager {

    private RedisTemplate<String, String> redisTemplate;

    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String val){
        redisTemplate.opsForValue().set(key, val);
    }

}
