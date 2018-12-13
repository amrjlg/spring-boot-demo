package com.jiang.web.controller;

import com.jiang.dao.model.YuansujuFile;
import com.jiang.web.service.YuansujuFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiang
 * @date 2018/12/13
 * @time 17:02
 **/
@RestController
@RequestMapping("/file")
public class FileContorller {
    @Autowired
    YuansujuFileService fileService;

    @GetMapping("info")
    public YuansujuFile fileInfo(@RequestParam("id")Long id){
        YuansujuFile file = fileService.selectByPrimaryKey(id);
        return file;
    }
}
