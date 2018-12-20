package com.jiang.web.service;

import java.util.List;

/**
 * @author jiang
 * @date 2018/12/13
 * @time 14:41
 **/
public interface BaseService<Type,Example> {
    long countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(Object id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(Example example);

    Type selectByPrimaryKey(Object id);

    int updateByExampleSelective(Type record,  Example example);

    int updateByExample(Type record, Example example);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
}
