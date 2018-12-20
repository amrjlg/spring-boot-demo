package com.jiang.mybatis.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiang
 * @date 2018/12/18
 * @time 14:54
 **/
public class BaseExample {

    /**
     *
     * @mbg.generated do_not_delete_during_merge 2018-12-18 15:18:05
     */
    protected String orderByClause;

    /**
     *
     * @mbg.generated do_not_delete_during_merge 2018-12-18 15:18:05
     */
    protected boolean distinct;



    /**
     *
     * @mbg.generated do_not_delete_during_merge 2018-12-18 15:18:05
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     * @mbg.generated do_not_delete_during_merge 2018-12-18 15:18:05
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     * @mbg.generated do_not_delete_during_merge 2018-12-18 15:18:05
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     * @mbg.generated do_not_delete_during_merge 2018-12-18 15:18:05
     */
    public boolean isDistinct() {
        return distinct;
    }


    protected static class Criterion extends BaseCriterion{

        public Criterion(String condition) {
            super(condition);
        }

        public Criterion(String condition, Object value, String typeHandler) {
            super(condition, value, typeHandler);
        }

        public Criterion(String condition, Object value) {
            super(condition, value);
        }

        public Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super(condition, value, secondValue, typeHandler);
        }

        public Criterion(String condition, Object value, Object secondValue) {
            super(condition, value, secondValue);
        }
    }
}
