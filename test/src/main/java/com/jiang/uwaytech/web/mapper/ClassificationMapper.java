package com.jiang.uwaytech.web.mapper;

import com.jiang.uwaytech.web.model.Classification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang
 * @date 2018/12/31
 * @time 17:23
 **/
public interface ClassificationMapper {
    List<Classification> classificationsByParentId(@Param("pid") int pid);
}
