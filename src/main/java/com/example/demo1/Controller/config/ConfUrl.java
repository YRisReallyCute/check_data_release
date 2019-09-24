package com.example.demo1.Controller.config;

import com.example.demo1.Repository.config.ConfSymZyBaikeUrlRepository;
import com.example.demo1.model.config.conf_symptom_zy_baidubaike_url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConfUrl {
    @Autowired
    private ConfSymZyBaikeUrlRepository confSymZyBaikeUrlRepository;

    @GetMapping("/conf/baike_url_findall")
    private Map<String,Object> baikeUrlFinfAll(){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        List<conf_symptom_zy_baidubaike_url> list=confSymZyBaikeUrlRepository.findAll();
        map.put("code","200");
        map.put("result",list);
        return map;
    }
}
