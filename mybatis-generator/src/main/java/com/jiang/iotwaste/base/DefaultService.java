package com.jiang.iotwaste.base;


import com.jiang.iotwaste.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author jiang
 * @date 2018/12/13
 * @time 14:43
 **/
public abstract class DefaultService<Type,Example> implements BaseService<Type,Example> {
    @Autowired
    protected BaseMapper<Type,Example> mapper;

    @Override
    public long countByExample(Example example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByExample(Example example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Type record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(Type record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<Type> selectByExample(Example example) {
        return mapper.selectByExample(example);
    }

    @Override
    public Type selectByPrimaryKey(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Type record, Example example) {
        return mapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Type record, Example example) {
        return mapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Type record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Type record) {
        return mapper.updateByPrimaryKey(record);
    }
}
