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
    SPRING_REQUEST_MAPPING("org.springframework.web.bind.annotation.RequestMapping","@RequestMapping"),
    SPRING_POST_MAPPING("org.springframework.web.bind.annotation.GetMapping","@GetMapping"),
    SPRING_GET_MAPPING("org.springframework.web.bind.annotation.PostMapping","@PostMapping"),
    SPRING_PATH_VARIABLE("org.springframework.web.bind.annotation.PathVariable","@PathVariable"),
    SPRING_REQUEST_PARAM("org.springframework.web.bind.annotation.RequestParam","@RequestParam"),





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
