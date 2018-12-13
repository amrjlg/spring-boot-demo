package com.jiang.cache.web.controller.json;

import com.jiang.cache.dao.FileRepository;
import com.jiang.cache.web.entity.YuansujuFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Jiang
 * @date 2018/11/27
 * @time 14:58
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private FileRepository fileRepository;

    @PostConstruct
    public void init(){
        valueOperations = redisTemplate.opsForValue();
    }

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    ValueOperations<String,Object> valueOperations;

    @GetMapping("/put.json")
    public boolean put(@RequestParam("key") String key, @RequestParam("value") String value) {
        valueOperations.set(key, value, 60, TimeUnit.SECONDS);
        return true;
    }

    @GetMapping("/get.json")
    public Object get(@RequestParam("key") String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/date.json")
    public Date date() {
        valueOperations.set("time",new Date(),60,TimeUnit.SECONDS);
        Object time = get("time");
        return (Date) time;
    }

    @GetMapping("/file/{id}.json")
    public YuansujuFile file(@PathVariable("id")String id ){
        YuansujuFile file = (YuansujuFile) valueOperations.get(id);
        if(null == file){
            Optional<YuansujuFile> fileOptional = fileRepository.findById(Long.valueOf(id));
            if(fileOptional.isPresent()){
                file=fileOptional.get();
                valueOperations.set(id,file);
            }
        }
        return  file;
    }
}
