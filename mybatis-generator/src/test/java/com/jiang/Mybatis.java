package com.jiang;

import com.jiang.mapper.AnnouncementMapper;
import com.jiang.model.Announcement;
import com.jiang.model.AnnouncementExample;
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
import java.time.LocalDateTime;
import java.util.Date;
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
    private AnnouncementMapper mapper;

    @Before
    public void before() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(stream);
        sqlSession = factory.openSession();
        mapper = sqlSession.getMapper(AnnouncementMapper.class);
    }

    @Test
    public void insert() {

        Announcement announcement = new Announcement();
        announcement.setId(1L);
        announcement.setCreateDate(new Date());
        announcement.setTitle("测试 - insert()");
        announcement.setDigest("Digest");
        announcement.setType(1);
        int insert = mapper.insert(announcement);
        log.info("mapper.insert(announcement) : {}",insert);
    }


    @After
    public void close() {
        mapper=null;
        sqlSession.close();
    }

}
