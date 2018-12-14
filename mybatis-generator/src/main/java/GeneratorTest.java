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
 * @time 10:40
 **/
public class GeneratorTest {
    private static final Logger logger = LoggerFactory.getLogger(GeneratorTest.class);
    private static final String location = "mybatis-generator-config.xml";

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(GeneratorTest.class.getClassLoader().getResourceAsStream(location));
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, new DefaultShellCallback(overwrite), warnings);
        logger.info("generate is begin !");
        myBatisGenerator.generate(null);
        logger.info("generate is end !");
    }
}
