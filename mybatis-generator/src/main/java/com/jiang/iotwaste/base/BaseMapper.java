package com.jiang.iotwaste.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jiang
 * @date 2018/12/6
 * @time 14:17
 */
public interface BaseMapper<Type,  Example> {
    long countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(Object id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(Example example);

    Type selectByPrimaryKey(Object id);

    int updateByExampleSelective(@Param("record") Type record, @Param("example") Example example);

    int updateByExample(@Param("record") Type record, @Param("example") Example example);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
}
