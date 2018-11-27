package com.jiang.demo.web.conventer;

import com.jiang.demo.enums.FIleStatusEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * @author Jiang
 * @date 2018/11/27
 * @time 16:22
 */
public class FileStatusConverterForSpring implements Converter<String, FIleStatusEnum> {
    @Override
    public FIleStatusEnum convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        try {
            return FIleStatusEnum.valueOf(source);
        } catch (Exception e) {
            throw new RuntimeException("file status must be DELETE or NORMAL !");
        }

    }
}
