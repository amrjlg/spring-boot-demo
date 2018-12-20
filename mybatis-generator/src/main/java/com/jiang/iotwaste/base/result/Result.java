package com.jiang.iotwaste.base.result;

import lombok.Data;

/**
 * @author jiang
 * @date 2018/12/20
 * @time 14:45
 **/
@Data
public class Result {
    private String msg;
    private String status;
    private long time;

    public Result() {
        msg="success";
        status="200";
        time = System.currentTimeMillis();
    }
}
