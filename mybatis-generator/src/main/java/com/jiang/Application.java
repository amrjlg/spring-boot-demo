package com.jiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jiang
 * @date 2018/12/13
 * @time 17:00
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.jiang.dao.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
