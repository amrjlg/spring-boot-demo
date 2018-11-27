package com.jiang.demo.dao;

import com.jiang.demo.web.entity.YuansujuFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jiang
 * @date 2018/11/27
 * @time 15:56
 */
public interface FileRepository extends JpaRepository<YuansujuFile,Long> {
}
