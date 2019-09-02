package com.jiang.generator.enums;

/**
 * @author jiang
 * @date 2018/12/13
 * @time 15:37
 **/
public enum Config {
    /**
     * service 实现类 default com.jiang.web.service.DefaultService
     */
    SERVICE_IMPL("service-impl"),
    /**
     * service包的根路径 默认值 src/main/java
     */
    SERVICE_PROJECT("service-project"),
    /**
     * service的包 com.jiang.service
     */
    SERVICE_PACKAGE("service-package"),
    /**
     * mapper的父接口
     */
    CLIENT_ROOT_INTERFACE("jiang-define-root-interface"),
    /**
     * controller的包 default com.jiang.web.controller
     */
    CONTROLLER_PACKAGE("controller-package"),
    /**
     * controller的包的根路径 default  src/main/java
     */
    CONTROLLER_PROJECT("controller-project"),
    /**
     * controller default true
     */
    CONTROLLER("controller"),
    /**
     * rest controller default true
     */
    REST_CONTROLLER("rest-controller");

    public String getProperty() {
        return property;
    }

    private final String property;

    Config(String property) {
        this.property = property;
    }
}
