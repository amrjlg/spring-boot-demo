package com.jiang;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jiang
 * @date 2019/8/30
 * @time 14:09
 */


@Slf4j
public class Mybatis {

    private SqlSessionFactory factory;
    private SqlSession sqlSession;


    @Before
    public void before() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(stream);
        sqlSession = factory.openSession();

    }




    @After
    public void close() {
        sqlSession.close();
    }

}
