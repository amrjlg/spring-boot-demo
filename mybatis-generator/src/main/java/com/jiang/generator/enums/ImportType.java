package com.jiang.generator.enums;

/**
 * @author jiang
 * @date 2018/12/14
 * @time 9:38
 **/
public enum ImportType {
    SPRING_SERVICE("org.springframework.stereotype.Service","@Service"),
    SPRING_CONTROLLER("org.springframework.stereotype.Controller","@Controller"),
    SPRING_REST_CONTROLLER("org.springframework.web.bind.annotation.RestController","@RestController"),
    SPRING_AUTOWIRED("org.springframework.beans.factory.annotation.Autowired","@Autowired"),
    LOMBOK_DATA("lombok.Data","@Data"),
    SWAGGER_API_MODEL_PROPERTY("io.swagger.annotations.ApiModelProperty","@ApiModelProperty")
    ;
    private final String type;

    private final String annotation;

    public String getAnnotation() {
        return annotation;
    }
    public String getType() {
        return type;
    }

    ImportType(String type,String annotation) {
        this.type = type;
        this.annotation=annotation;
    }}
