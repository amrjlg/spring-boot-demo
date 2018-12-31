package com.jiang.mybatis;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author jiang
 * @date 2018/12/26
 * @time 16:26
 **/
public class Mybatis3Test {

    @Test
    public void mybatis() throws IOException {
        DataSource ds = new UnpooledDataSource("com.mysql.jdbc.Driver","jdbc:mysql://39.106.145.166:3306/yuansuju?useSSL=false","root","123");
        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        Environment env = new Environment("default", jdbcTransactionFactory,ds);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(env);

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(configuration);
        SqlSession sqlSession = sessionFactory.openSession();

    }
}
