/**
 * Copyright ${license.git.copyrightYears} the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jiang;

import com.jiang.mybatis.mapper.TestMapper;
import com.jiang.mybatis.model.TestObject;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

/**
 * @author Jiang
 * @date 2018/12/3
 * @time 15:13
 */
public class App {
    SqlSessionFactory sqlSessionFactory;
    final String driver = "com.mysql.jdbc.Driver";
    final String url = "jdbc:mysql://127.0.0.1:3306/world?useUnicode=true&characterEncoding=utf8&useSSL=false";
    final String username = "root";
    final String password = "0231625530";

    @Before
    public void befor() throws IOException {
        UnpooledDataSource dataSource = new UnpooledDataSource(driver, url, username, password);
        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("default", jdbcTransactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.setUseGeneratedKeys(true);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseColumnLabel(true);
        configuration.setLogImpl(Log4jImpl.class);
        configuration.addMapper(CityMapper.class);
        loadMapperXml("mybatis", configuration);
//        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("mybatis/CityMapper.xml");
//        new XMLMapperBuilder(resourceAsStream, configuration, "mybatis/CityMapper.xml", new HashMap<>()).parse();
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = factoryBuilder.build(configuration);
    }

    @Test
    public void session() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
//        CityMapper mapper = sqlSession.getMapper(CityMapper.class);
//        List<City> cities = mapper.selectByExample(null);
//        cities.forEach(city -> System.out.println(city.getName()));

        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        List<TestObject> testObjects = mapper.selectAll();
        testObjects.forEach(System.out::println);
    }


    private void loadMapperXml(String mapperXmlFileLocation, Configuration configuration) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URI uri = classLoader.getResource(mapperXmlFileLocation).toURI();
            File file = new File(uri);
            if (file.exists() && file.isDirectory()){
                File[] files = file.listFiles();
                if (null != files){
                    for (File xml : files) {
                        String source = mapperXmlFileLocation + "/" + xml.getName();
                        new XMLMapperBuilder(classLoader.getResourceAsStream(source), configuration, source, new HashMap<>()).parse();
                    }
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void file() throws URISyntaxException {
        URI uri = getClass().getClassLoader().getResource("mybatis").toURI();
        File file = new File(uri);


    }
}
