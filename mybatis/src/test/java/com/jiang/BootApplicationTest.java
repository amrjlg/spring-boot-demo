package com.jiang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Jiang
 * @date 2018/12/7
 * @time 11:37
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan(basePackages = "com.jiang.demo.mapper")
public class BootApplicationTest {

}
