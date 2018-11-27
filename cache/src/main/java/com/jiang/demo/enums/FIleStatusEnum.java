package com.jiang.demo.enums;

/**
 * @author Jiang
 * @date 2018/11/27
 * @time 15:09
 */
public enum FIleStatusEnum {
    /**
     * 删除
     */
    DELETE("deleted"),
    /**
     * 正常
     */
    NORMAL("normal"),
    ;

    public final static FIleStatusEnum[] DELETE_STATUSES = FIleStatusEnum.values();
    private final String status;

    private FIleStatusEnum(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
