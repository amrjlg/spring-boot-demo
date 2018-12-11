package com.jiang.print;

/**
 * @author Jiang
 * @date 2018/12/10
 * @time 14:04
 */
public abstract class PrintUtils {
    public static void print(String str){
        System.out.println(str);
    }

    public static void print(CharSequence format,CharSequence str){
        print(String.format(format+":%s",str));
    }
}
