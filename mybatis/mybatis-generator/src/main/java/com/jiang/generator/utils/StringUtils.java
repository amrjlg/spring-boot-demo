package com.jiang.generator.utils;

import com.jiang.generator.enums.ImportType;

/**
 * @author jiang
 * @date 2018/12/14
 * @time 14:11
 **/
public class StringUtils {
    public static String lowerFirst(String oldStr){
        char[]chars = oldStr.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);

    }
    public static String getJsonControllerAnnotation(String modelName){
        return ImportType.SPRING_REST_CONTROLLER.getAnnotation() + "(\"" + modelName + "Json\")";
    }

    public static String getControllerAnnotation(String modelName){
        return ImportType.SPRING_CONTROLLER.getAnnotation() + "(\"" + modelName + "Page\")";
    }
}
