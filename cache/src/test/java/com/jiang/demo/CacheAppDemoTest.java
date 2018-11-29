package com.jiang.demo;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.junit.Test;

import java.util.Date;

/**
 * Unit test for simple CacheAppDemo.
 */
public class CacheAppDemoTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void fastJson(){
        FastJsonRedisSerializer<Date> serializer = new FastJsonRedisSerializer<>(Date.class);

        FastJsonConfig jsonConfig = new FastJsonConfig();
        jsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonConfig.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat);
        serializer.setFastJsonConfig(jsonConfig);

        byte[] serialize = serializer.serialize(new Date());
        System.out.println(new String(serialize));
    }
}
