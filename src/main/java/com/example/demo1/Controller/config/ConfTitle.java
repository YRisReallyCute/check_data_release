package com.example.demo1.Controller.config;

import com.example.demo1.Repository.config.ConfSymZyBaikeTitleRepository;
import com.example.demo1.model.config.conf_symptom_zy_baidubaike_title;
import com.example.demo1.model.config.conf_symptom_zy_baidubaike_url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
//@CrossOrigin
public class ConfTitle {
    @Autowired
    private ConfSymZyBaikeTitleRepository confSymZyBaikeTitleRepository;

    @GetMapping("/conf/baike_title_findall")
    private Map<String,Object> baikeTitleFindAll(@RequestParam Integer page, @RequestParam Integer size){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        Sort sort=new Sort(Sort.DEFAULT_DIRECTION,"id");
        PageRequest pageRequest=new PageRequest(page,size,sort);
        Page<conf_symptom_zy_baidubaike_title> list=confSymZyBaikeTitleRepository.findAll(pageRequest);
        map.put("code","200");
        map.put("result",list);
        return map;
    }

    @RequestMapping(value = "/conf/baike_title_add", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> baikeTitle(@RequestParam String title,@RequestParam String col){
        Map<String,Object> map=new LinkedHashMap<>();
        int result = confSymZyBaikeTitleRepository.addone(title,col);
        if(result > 0){
            map.put("code","200");
        }
        else{
            map.put("code","400");
        }
        return map;
    }

    @RequestMapping(value = "/conf/baike_title_delete", method = RequestMethod.POST)
    private Map<String,Object> baikeTitleDelete(@RequestBody Map<String,ArrayList<LinkedHashMap<String,String>>> requstMap){
        Map<String,Object> map = new LinkedHashMap<>();
        int l = requstMap.get("list").size();
        for(int i=0;i<l;i++){
            int temp_id = Integer.parseInt(requstMap.get("list").get(i).get("id"));
            if(confSymZyBaikeTitleRepository.deleteone(temp_id)<=0){
                map.put("code","400");
                return map;
            }
        }
        map.put("code","200");
        return map;
    }
}
