package com.jiang.generator.enums;

/**
 * @author jiang
 * @date 2018/12/14
 * @time 9:38
 **/
public enum ImportType {
    SPRING_SERVICE("org.springframework.stereotype.Service"),
    SPRING_CONTROLLER("org.springframework.stereotype.Controller"),
    SPRING_REST_CONTROLLER("org.springframework.web.bind.annotation.RestController"),
    LOMBOK_DATA("lombok.Data"),
    SWAGGER_API_MODEL_PROPERTY("io.swagger.annotations.ApiModelProperty")
    ;
    private final String type;

    public String getType() {
        return type;
    }

    ImportType(String type) {
        this.type = type;
    }}
