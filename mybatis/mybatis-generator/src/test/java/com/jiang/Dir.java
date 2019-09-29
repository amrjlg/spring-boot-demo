package com.jiang;

import org.junit.Test;
import org.mybatis.generator.internal.util.messages.Messages;

import java.io.File;
import java.text.MessageFormat;

/**
 * @author jiang
 * @date 2019/9/17
 * @time 22:04
 */
public class Dir {
    @Test
    public void dir(){
        File file = new File("");
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        String string = Messages.getString("RuntimeError.6", "123");

        System.out.println(string);

        String format = MessageFormat.format(" --- {0}--{1} ---{2}", "param1", "param2", "param3");
        System.out.println(format);
    }
}
