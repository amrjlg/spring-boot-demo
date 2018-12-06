package com.jiang.demo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jiang
 * @date 2018/12/6
 * @time 14:17
 */
public interface BaseMapper<Type, Key, Example> {
    long countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(Key id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(Example example);

    Type selectByPrimaryKey(Key id);

    int updateByExampleSelective(@Param("record") Type record, @Param("example") Example example);

    int updateByExample(@Param("record") Type record, @Param("example") Example example);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
}
