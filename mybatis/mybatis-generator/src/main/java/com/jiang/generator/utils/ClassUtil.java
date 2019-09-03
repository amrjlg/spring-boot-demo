package com.jiang.generator.utils;

/**
 * @author jiang
 * @date 2018/12/14
 * @time 9:35
 **/
public class ClassUtil {
    public static Class getClassForName(String name){
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
