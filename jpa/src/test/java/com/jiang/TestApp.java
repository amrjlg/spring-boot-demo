package com.jiang;

import com.jiang.jpa.UserJpa;
import com.jiang.jpa.UserTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Jiang
 * @date 2018/12/5
 * @time 12:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestApp {
    @Autowired
    private UserJpa userJpa;
    @Test
    public void user(){
        List<UserTest> userJpaAll = userJpa.findAll();
        userJpaAll.forEach(System.out::println);
    }
}
