package com.jiang.generator.enums;

/**
 * @author jiang
 * @date 2018/12/13
 * @time 15:37
 **/
public enum Config {
    SERVICE_IMPL("service-impl"),
    SERVICE_PROJECT("service-project"),
    SERVICE_PACKAGE("service-package"),
    CLIENT_ROOT_INTERFACE("jiang-define-root-interface"),
    CONTROLLER_PACKAGE("controller-package"),
    CONTROLLER_PROJECT("controller-project"),
    CONTROLLER("controller"),
    REST_CONTROLLER("rest-controller");

    public String getProperty() {
        return property;
    }

    private final String property;

    Config(String property) {
        this.property = property;
    }
}
