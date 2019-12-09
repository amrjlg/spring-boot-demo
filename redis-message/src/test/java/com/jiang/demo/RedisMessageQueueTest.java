package com.jiang.demo;

import com.jiang.demo.queue.RedisMessagePublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author jiang
 * @date 2019/12/4
 * @time 14:25
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisMessageQueueTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisMessagePublisher messagePublisher;

    @Autowired
    private RedisConnectionFactory factory;

    @Test
    public void redis() {
        Set<String> keys = redisTemplate.keys("*");
        System.out.println(keys.size());
    }

    @Test
    public void publish() {
        System.out.println("------------------");
        messagePublisher.sendMessage("发送一条消息");

    }

    @Test
    public void jedisFactory() {
//        String key = "org:class:*";
//        ScanOptions options = ScanOptions.scanOptions().match(key).count(100).build();
//        RedisConnection connection = factory.getConnection();
//        long begin = System.currentTimeMillis();
//        Cursor<byte[]> scan = connection.scan(options);
//        long end = System.currentTimeMillis();
//
//        System.out.println(String.format("scaned %s use %d " ,key, end -begin));
//        Set<String> keys = redisTemplate.keys(key);
//        System.out.println(String.format("scaned %d use %d " ,keys.size() , System.currentTimeMillis() - end));
//
//        BoundValueOperations<String, Object> stringObjectBoundValueOperations = redisTemplate.boundValueOps(key);
//        stringObjectBoundValueOperations.
//
//
//        connection.close();
    }
}
