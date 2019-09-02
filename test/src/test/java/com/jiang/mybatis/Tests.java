package com.jiang.mybatis;


import com.jiang.uwaytech.web.mapper.ClassificationMapper;
import com.jiang.uwaytech.web.model.Classification;
import com.jiang.uwaytech.web.model.ClassificationChildren;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author jiang
 * @date 2018/12/25
 * @time 9:26
 **/
public class Tests {
    private static final String location = "mybatis-generator-config.xml";

    @Test
    public void mybatis() throws IOException {
        DataSource ds = new UnpooledDataSource("com.mysql.jdbc.Driver","jdbc:mysql://39.106.145.166:3306/test?useSSL=false","root","123");
//        DataSource ds = new UnpooledDataSource("com.mysql.jdbc.Driver","jdbc:mysql://git.yuansuju.com:3306/yuansuju?useSSL=false","root","Xihe123456");
        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        Environment env = new Environment("default", jdbcTransactionFactory,ds);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(env);

        String resource = "mybatis/ClassificationMapper.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //映射器比较复杂，调用XMLMapperBuilder
        //注意在for循环里每个mapper都重新new一个XMLMapperBuilder，来解析
        XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
        mapperParser.parse();
        configuration.getTypeAliasRegistry().registerAliases("com.jiang.iotwaste.web.model");

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(configuration);
        SqlSession sqlSession = sessionFactory.openSession();
        ClassificationMapper mapper = sqlSession.getMapper(ClassificationMapper.class);
        List<Classification> classifications = mapper.classificationsByParentId(300);
        classifications.forEach(classification -> {
            System.out.println("parent");
            System.out.println(classification.getId() +"\t"+ classification.getName());
            List<ClassificationChildren> children = classification.getChildren();
            System.out.println("children");
            if (children!= null){
                children.forEach(child->{
                    System.out.println(child.getChildId() +"\t" + child.getChildName());
                });
            }
        });
    }

}
