package com.jiang.test;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static com.jiang.print.PrintUtils.print;

/**
 * @author Jiang
 * @date 2018/12/10
 * @time 14:01
 */
public class DateTestFor8 {

    @Test
    public void localDateTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        String toString = dateTime.toString();
        print("LocalDateTime toString",toString);
        String format = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA));
        print("LocalDateTime format of 'yyyy-MM-dd HH:mm:ss' Locale.CHINA",format);
        LocalDateTime parse = LocalDateTime.parse(format,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        print("parse of pattern",parse.toString());
        LocalDateTime parse1 = LocalDateTime.parse(toString);
        print("parse",parse1.toString());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.of("CTT",ZoneId.SHORT_IDS));
        print("ofInstant",localDateTime.toString());
        LocalDateTime min = LocalDateTime.MIN;
        print("min",min.toString());
        print("min add 9999 year",min.plusYears(9999).toString());
        LocalDateTime max = LocalDateTime.MAX;
        print("max",max.toString());
        print("max add 9999 year",max.plusYears(9999).toString());
    }
}
