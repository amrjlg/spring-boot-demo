package com.jiang.iotwaste.base.result;

import lombok.Data;

/**
 * @author jiang
 * @date 2018/12/20
 * @time 14:46
 **/
@Data
public class ObjectResult<Type> extends Result {
    private Type result;

    public ObjectResult(Type type) {
        result = type;
    }
}
