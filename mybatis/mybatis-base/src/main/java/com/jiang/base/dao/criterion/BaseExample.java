package com.jiang.base.dao.criterion;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis generator Example抽象
 *
 * @param <T> 条件 Criteria
 * @author jiang
 * @date 2019-8-30
 * @time 15:38:00
 */
public class BaseExample<T> {

    protected String orderByClause;


    protected boolean distinct;


    protected List<T> oredCriteria;


    protected BaseExample() {
        oredCriteria = new ArrayList<T>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }


    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<T> getOredCriteria() {
        return oredCriteria;
    }

    public void or(T criteria) {
        oredCriteria.add(criteria);
    }

    public T or() {
        T criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public T createCriteria() {
        T criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected T createCriteriaInternal() {
        Type superClass = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];

        T criteria = null;
        try {
            criteria = (T) ((Class) type).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return criteria;
    }

    /**
     * @mbg.generated do_not_delete_during_merge 2019-08-30 11:06:36
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }
}