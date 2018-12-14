package com.jiang;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiang
 * @date 2018/12/14
 * @time 11:34
 **/
public class TestClass {
    private static final String location = "mybatis-generator-config.xml";
    private static final Logger logger = LoggerFactory.getLogger(TestClass.class);
    @Test
    public void generator() throws Exception{
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(getClass().getClassLoader().getResourceAsStream(location));
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, new DefaultShellCallback(overwrite), warnings);
        logger.info("generate is begin !");
        myBatisGenerator.generate(null);
        logger.info("generate is end !");
    }
}
