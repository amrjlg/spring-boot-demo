package com.jiang.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jiang
 * @date 2019/12/4
 * @time 14:23
 */
@SpringBootApplication
public class RedisMessageQueue {
    public static void main(String[] args) {
        SpringApplication.run(RedisMessageQueue.class,args);
    }
}
