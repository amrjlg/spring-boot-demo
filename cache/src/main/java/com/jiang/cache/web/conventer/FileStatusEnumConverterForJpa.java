package com.jiang.cache.web.conventer;

import com.jiang.cache.enums.FIleStatusEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Jiang
 * @date 2018/11/27
 * @time 16:09
 */
@Converter
public class FileStatusEnumConverterForJpa implements AttributeConverter<FIleStatusEnum, String> {


    @Override
    public String convertToDatabaseColumn(FIleStatusEnum attribute) {
        return attribute.getStatus();
    }

    @Override
    public FIleStatusEnum convertToEntityAttribute(String dbData) {
        FIleStatusEnum status = null;
        for (FIleStatusEnum deleteStatus : FIleStatusEnum.DELETE_STATUSES) {
            if (deleteStatus.getStatus().equalsIgnoreCase(dbData)) {
                status = deleteStatus;
                break;
            }
        }
        return status;
    }
}
