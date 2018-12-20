package com.jiang.iotwaste.base.result;

import lombok.Data;

import java.util.List;

/**
 * @author jiang
 * @date 2018/12/20
 * @time 14:47
 **/
@Data
public class ListResult<Type> extends Result {
    private List<Type> result;

    public ListResult(List<Type> models) {
        result = models;
    }
}
