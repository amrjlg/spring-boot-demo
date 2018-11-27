package com.jiang.demo.web.controller.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Jiang
 * @date 2018/11/27
 * @time 14:58
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/put.json")
    public boolean put(@RequestParam("key") String key, @RequestParam("value") String value) {
        redisTemplate.opsForValue().set(key, value, 60, TimeUnit.SECONDS);
        return true;
    }

    @GetMapping("/get.json")
    public String get(@RequestParam("key") String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
