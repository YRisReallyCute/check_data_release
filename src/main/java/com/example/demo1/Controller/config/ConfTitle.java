package com.example.demo1.Controller.config;

import com.example.demo1.Repository.config.ConfSymZyBaikeTitleRepository;
import com.example.demo1.model.config.conf_symptom_zy_baidubaike_title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConfTitle {
    @Autowired
    private ConfSymZyBaikeTitleRepository confSymZyBaikeTitleRepository;

    @GetMapping("/conf/baike_title_findall")
    private Map<String,Object> baikeTitleFindAll(){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        List<conf_symptom_zy_baidubaike_title> list=confSymZyBaikeTitleRepository.findAll();
        map.put("code","200");
        map.put("result",list);
        return map;
    }

    @GetMapping("/conf/baike_title_add")
    private Map<String,Object> baikeTitleAdd(@RequestParam String title,@RequestParam   String col){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        conf_symptom_zy_baidubaike_title title2col=new conf_symptom_zy_baidubaike_title(title,col);
        confSymZyBaikeTitleRepository.saveAndFlush(title2col);
        map.put("code","200");
        return map;
    }
}
