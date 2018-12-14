package com.jiang.generator.utils;

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
}
