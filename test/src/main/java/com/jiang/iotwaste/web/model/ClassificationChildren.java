package com.jiang.iotwaste.web.model;

import lombok.Data;

/**
 * @author jiang
 * @date 2018/12/31
 * @time 17:26
 **/
@Data
public class ClassificationChildren {
    private long parentId;
    private long childId;
    private String childName;
    private long display;
}
