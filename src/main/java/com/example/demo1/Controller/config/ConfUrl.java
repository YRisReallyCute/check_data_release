package com.example.demo1.Controller.config;

import com.example.demo1.repository.config.ConfSymZyBaikeUrlRepository;
import com.example.demo1.model.config.conf_symptom_zy_baidubaike_url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
//@CrossOrigin
public class ConfUrl {
    @Autowired
    private ConfSymZyBaikeUrlRepository confSymZyBaikeUrlRepository;

    @RequestMapping(value = "/conf/baike_url_findall", method = RequestMethod.GET)
    private Map<String,Object> baikeUrlFinfAll(@RequestParam Integer page, @RequestParam Integer size){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        Sort sort=new Sort(Sort.DEFAULT_DIRECTION,"id");
        PageRequest pageRequest=new PageRequest(page,size,sort);
        Page<conf_symptom_zy_baidubaike_url> page1= confSymZyBaikeUrlRepository.findAll(pageRequest);

        map.put("code","200");
        map.put("result",page1);
        return map;
    }

    @GetMapping("/conf/search_content_all")
    private Map<String,Object> searchContentAll(@RequestParam String content,@RequestParam Integer page,@RequestParam Integer size){
        Map<String,Object> map=new LinkedHashMap<>();
        List<conf_symptom_zy_baidubaike_url> list=confSymZyBaikeUrlRepository.findByNameAll(content);
        List<conf_symptom_zy_baidubaike_url> list2=new LinkedList<>();
        for(int i=page*size;(i<page*size+size)&&(i<list.size());i++){
            list2.add(new conf_symptom_zy_baidubaike_url(list.get(i)));
        }
        map.put("code","200");
        map.put("totalNums",list.size());
        map.put("result",list2);
        return map;
    }

    @RequestMapping(value = "/conf/baike_url_add", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> baikeUrl(@RequestParam String name){
        Map<String,Object> map=new LinkedHashMap<>();
        conf_symptom_zy_baidubaike_url new_one=new conf_symptom_zy_baidubaike_url(name);
        String tempurl="";
        int result = confSymZyBaikeUrlRepository.addone(name,tempurl);
        if(result > 0){
            map.put("code","200");
        }
        else{
            map.put("code","400");
        }
        return map;
    }

    @RequestMapping(value = "/conf/baike_url_update", method = RequestMethod.POST)
    private Map<String,Object> updateStatus(@RequestBody Map<String, ArrayList<LinkedHashMap<String,String>>> requstMap){
        Map<String,Object> map = new LinkedHashMap<>();
        int l = requstMap.get("list").size();
        for(int i=0;i<l;i++){
            int temp_id = Integer.parseInt(requstMap.get("list").get(i).get("id"));
            if(confSymZyBaikeUrlRepository.update(temp_id)<=0){
                map.put("code","400");
                return map;
            }
        }
        map.put("code","200");
        return map;
    }

    @RequestMapping(value = "/conf",method = RequestMethod.GET)
    private String testConf(){
        return "200";
    }
}
