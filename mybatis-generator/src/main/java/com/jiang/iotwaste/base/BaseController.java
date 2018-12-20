package com.jiang.iotwaste.base;


import com.jiang.iotwaste.base.result.ListResult;
import com.jiang.iotwaste.base.result.ObjectResult;
import com.jiang.iotwaste.base.result.Result;
import com.jiang.iotwaste.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author jiang
 * @date 2018/12/20
 * @time 13:34
 **/
public abstract class BaseController<Service extends BaseService<Model, Example>, Model, Example> {

    @Autowired
    protected Service service;

    @GetMapping("/info.json")
    protected ObjectResult<Model> info(@RequestParam("id") Object id) {
        Model selectByPrimaryKey = service.selectByPrimaryKey(id);
        return new ObjectResult<>(selectByPrimaryKey);
    }

    @PostMapping("/add.json")
    protected Result add(@RequestBody Model model) {
        int insert = service.insert(model);
        return new Result();
    }

    @PostMapping("/update.json")
    protected Result update(@RequestBody Model model) {
        int update = service.updateByPrimaryKey(model);
        return new Result();
    }

    @PostMapping("/delete.json")
    protected Result delete(@RequestParam("id") Object id) {
        int delete = service.deleteByPrimaryKey(id);
        return new Result();
    }

    @GetMapping("/list.json")
    protected ListResult<Model> list(@RequestParam("page") int page, @RequestParam("size") int size) {
        List<Model> models = service.selectByExample(null);
        return new ListResult<>(models);
    }
}
