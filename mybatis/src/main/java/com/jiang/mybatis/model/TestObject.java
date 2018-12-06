package com.jiang.mybatis.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Jiang
 * @date 2018/12/4
 * @time 12:17
 */
@Data
public class TestObject {
    private long seckillId;
    private String name;
    private int number;
    private Date startTime;
    private Date endTime;
    private Date createTime;
}
