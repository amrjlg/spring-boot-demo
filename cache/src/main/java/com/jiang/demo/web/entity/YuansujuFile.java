package com.jiang.demo.web.entity;

import com.jiang.demo.enums.FIleStatusEnum;
import com.jiang.demo.web.conventer.FileStatusEnumConverterForJpa;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 * @author Jiang
 * @date 2018/11/27
 * @time 15:02
 */
@Data
@Entity
@Table(name = "yuansuju_ht_file")
public class YuansujuFile {

    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fileId;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "type")
    private String type;

    @Column(name = "data_id")
    private String dataId;

    @Column(name = "deleted_status")
    @Convert(converter = FileStatusEnumConverterForJpa.class)
    private FIleStatusEnum deleteStatus;

    @Column(name = "row_add_time")
    private Date addTime;

}
