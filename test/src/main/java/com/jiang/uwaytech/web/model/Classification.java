package com.jiang.uwaytech.web.model;

import lombok.Data;

import java.util.List;

/**
 * @author jiang
 * @date 2018/12/31
 * @time 17:24
 **/
@Data
public class Classification {
    private long id;
    private String name;
    List<ClassificationChildren> children;
}
