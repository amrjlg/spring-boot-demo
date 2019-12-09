package com.jiang.demo.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisMessageListener implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessageListener.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        LOGGER.info("从消息通道={}监听到消息", new String(pattern));
        LOGGER.info("从消息通道={}监听到消息", new String(message.getChannel()));
        LOGGER.info("元消息={}", new String(message.getBody()));
        // 新建一个用于反序列化的对象，注意这里的对象要和前面配置的一样
        // 因为我前面设置的默认序列化方式为GenericJackson2JsonRedisSerializer
        // 所以这里的实现方式为GenericJackson2JsonRedisSerializer
        RedisSerializer serializer = new GenericJackson2JsonRedisSerializer();


        LOGGER.info("反序列化后的消息={}", serializer.deserialize(message.getBody()));
    }

    /**
     * 将自定义的 RedisListener  添加到 RedisMessageListenerContainer 中
     * @param factory
     * @param redisMessageListener
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory, RedisMessageListener redisMessageListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(redisMessageListener, new PatternTopic("message-channel"));
        return container;
    }
}