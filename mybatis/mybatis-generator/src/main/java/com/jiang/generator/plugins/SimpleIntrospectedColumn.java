package com.jiang.generator.plugins;

import org.mybatis.generator.api.IntrospectedColumn;

/**
 * @author jiang
 * @date 2018/12/13
 * @time 16:28
 **/
public class SimpleIntrospectedColumn extends IntrospectedColumn {
    @Override
    public boolean isBLOBColumn() {
        return false;
    }
}
