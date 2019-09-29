package com.jiang;

import com.jiang.mapper.AdModelMapper;
import com.jiang.model.AdModel;
import com.jiang.model.AdModelCriteria;
import com.jiang.model.AdModelExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author jiang
 * @date 2019/8/30
 * @time 14:09
 */


@Slf4j
public class Mybatis {

    private SqlSessionFactory factory;
    private SqlSession sqlSession;

    private AdModelMapper mapper;
    @Before
    public void before() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(stream);
        sqlSession = factory.openSession();
        mapper = sqlSession.getMapper(AdModelMapper.class);
    }

    @Test
    public void insert() {
        AdModelExample example = new AdModelExample();

        example.or().andTypeEqualTo(1).andDigestEqualTo("1");
        example.or().andTypeEqualTo(2).andDigestEqualTo("1");

        List<AdModel> adModels = mapper.selectByExample(example);
    }


    @After
    public void close() {
        sqlSession.close();
    }

}
