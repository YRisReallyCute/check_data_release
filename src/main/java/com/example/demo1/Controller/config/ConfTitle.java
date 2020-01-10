package com.example.demo1.Controller.config;

import com.example.demo1.model.config.ConfHerbalTitle;
import com.example.demo1.model.config.ConfPatentTitle;
import com.example.demo1.repository.config.ConfHerbalTitleRepository;
import com.example.demo1.repository.config.ConfPatentTitleRepository;
import com.example.demo1.repository.config.ConfSymZyBaikeTitleRepository;
import com.example.demo1.model.config.conf_symptom_zy_baidubaike_title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.lang.reflect.Method;
import java.util.*;

@RestController
//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin
public class ConfTitle {
    @Autowired
    private ConfSymZyBaikeTitleRepository confSymZyBaikeTitleRepository;
    @Autowired
    private ConfPatentTitleRepository confPatentTitleRepository;
    @Autowired
    private ConfHerbalTitleRepository confHerbalTitleRepository;

    @GetMapping("/conf/title_findByName")
    private Map<String,Object> baikeTitleFindByName(@RequestParam Integer page, @RequestParam Integer size,@RequestParam String jobGroup,@RequestParam String name){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        List<String> sortProperties=new ArrayList<>();
        sortProperties.add("col");
        sortProperties.add("title");
        Sort sort=new Sort(Sort.Direction.ASC,sortProperties);
        PageRequest pageRequest=new PageRequest(page,size,sort);
        name=name.trim();
        switch (jobGroup){
            case "zxy":{
                Page<conf_symptom_zy_baidubaike_title> list;
                if(!name.equals("")) {
                    list = confSymZyBaikeTitleRepository.findByNameLike(name, pageRequest);
                }
                else {
                    list=confSymZyBaikeTitleRepository.findAll(pageRequest);
                }
                map.put("code","200");
                map.put("result",list);
                break;
            }
            case "patent":{
                Page<ConfPatentTitle> list;
                if(!name.equals("")) {
                        list=confPatentTitleRepository.findByNameLike(name, pageRequest);
                }
                else{
                    list=confPatentTitleRepository.findAll(pageRequest);
                }
                map.put("code","200");
                map.put("result",list);
                break;
            }
            case "herbal":{
                Page<ConfHerbalTitle> list;
                if(!name.equals("")) {
                    list=confHerbalTitleRepository.findByNameLike(name, pageRequest);
                }
                else{
                    list=confHerbalTitleRepository.findAll(pageRequest);
                }
                map.put("code","200");
                map.put("result",list);
                break;
            }
            default:{
                map.put("code","400");
            }
        }

        return map;
    }

    @GetMapping("/conf/getCols")
    private Map<String,Object> getCols(@RequestParam String jobGroup){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        switch (jobGroup){
            case "zxy":{
                List<String> list=confSymZyBaikeTitleRepository.getCols();
                map.put("code","200");
                map.put("result",list);
                break;
            }
            case "patent":{
                List<String> list=confPatentTitleRepository.getCols();
                map.put("code","200");
                map.put("result",list);
                break;
            }
            case "herbal":{
                List<String> list=confHerbalTitleRepository.getCols();
                map.put("code","200");
                map.put("result",list);
                break;
            }
            default:{
                map.put("code","400");
            }
        }
        return map;
    }

    @RequestMapping(value = "/conf/baike_title_add", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> baikeTitle(@RequestParam String jobGroup, @RequestParam String title, @RequestParam String col, @RequestParam String name){
        Map<String,Object> map=new LinkedHashMap<>();
        switch (jobGroup){
            case "zxy":{
               confSymZyBaikeTitleRepository.saveAndFlush(new conf_symptom_zy_baidubaike_title(title,col,name));
               map.put("code","200");
               break;
            }
            case "patent":{
                confPatentTitleRepository.saveAndFlush(new ConfPatentTitle(title,col,name));
                map.put("code","200");
                break;
            }
            case "herbal":{
                confHerbalTitleRepository.saveAndFlush(new ConfHerbalTitle(title,col,name));
                map.put("code","200");
                break;
            }
            default:{
                map.put("code","400");
            }
        }
        return map;
    }

    @GetMapping(value = "/conf/title_delete")
    private Map<String,Object> baikeTitleDelete(@RequestParam String deleteList,@RequestParam String jobGroup){
        Map<String,Object> map = new LinkedHashMap<>();
        String repository="";
        List<String>list= Arrays.asList(deleteList.split("/"));
        switch (jobGroup) {
            case "zxy": {
                repository = "ConfSymZyBaikeTitleRepository";
                for (String each:list) {
                    if(confSymZyBaikeTitleRepository.findById(Integer.parseInt(each)).isPresent()) {
                        confSymZyBaikeTitleRepository.deleteById(Integer.parseInt(each));
                    }
                }
                break;
            }
            case "patent": {
                repository = "ConfPatentTitleRepository";
                for (String each:list) {
                    if(confPatentTitleRepository.findById(Integer.parseInt(each)).isPresent()) {
                        confPatentTitleRepository.deleteById(Integer.parseInt(each));
                    }
                }
                break;
            }
            case "herbal": {
                repository = "ConfHerbalTitleRepository";
                for (String each:list) {
                    if(confHerbalTitleRepository.findById(Integer.parseInt(each)).isPresent()) {
                        confHerbalTitleRepository.deleteById(Integer.parseInt(each));
                    }
                }
                break;
            }
            default: {
                repository = "";
            }
        }
        try {
//            Class<?> classRepo = Class.forName("com.example.demo1.repository.config." + repository);
//            Method method=classRepo.getDeclaredMethod("deleteById",Integer.class);
//            method.setAccessible(true);
//            for (String each:list) {
//                method.invoke(classRepo.newInstance(),Integer.parseInt(each));
//            }
            map.put("code","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code","200");
        }

        return map;
    }
}
