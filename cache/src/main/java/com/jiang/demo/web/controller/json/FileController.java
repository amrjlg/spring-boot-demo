package com.jiang.demo.web.controller.json;

import com.jiang.demo.dao.FileRepository;
import com.jiang.demo.web.entity.YuansujuFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiang
 * @date 2018/11/27
 * @time 15:57
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileRepository fileRepository;


    @Cacheable(key = "#id", cacheManager = "cacheManager", cacheNames = "file")
    @GetMapping("info.json")
    public YuansujuFile info(@RequestParam("id") Long id) {
        return fileRepository.findById(id).orElse(null);
    }
}
