package com.jiang.demo.queue;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


/**
 * @author jiang
 * @date 2019/12/4
 * @time 14:30
 */

@Service
public class RedisMessagePublisher {
    private  final  RedisTemplate<String,Object> restTemplate;

    public RedisMessagePublisher(RedisTemplate<String, Object> restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendMessage(Object msg){
        Assert.notNull(msg,"message must be not null");
        restTemplate.convertAndSend("message-channel",msg);
    }
}
