package com.jiang.demo;

import com.jiang.demo.generator.callback.GeneratorShellCallback;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.eclipse.core.callback.EclipseShellCallback;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang
 * @date 2018/12/6
 * @time 14:18
 */
public class GeneratorTest {

    final String location = "mybatis-generator-config.xml";

    @Test
    public void generator() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(getClass().getClassLoader().getResourceAsStream(location));
        GeneratorShellCallback callback = new GeneratorShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, new DefaultShellCallback(overwrite), warnings);
        myBatisGenerator.generate(null);

    }
}
