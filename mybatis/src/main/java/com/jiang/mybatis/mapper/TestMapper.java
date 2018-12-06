package com.jiang.mybatis.mapper;

import com.jiang.mybatis.model.TestObject;

import java.util.List;

/**
 * @author Jiang
 * @date 2018/12/4
 * @time 12:11
 */
public interface TestMapper {

    TestObject selectOne(int id);
    List<TestObject> selectAll();
}
