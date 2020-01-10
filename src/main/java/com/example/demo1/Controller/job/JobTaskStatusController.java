package com.example.demo1.Controller.job;

import com.example.demo1.repository.job.SearchContentRepository;
import com.example.demo1.model.job.SearchContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@Slf4j
public class JobTaskStatusController {

    @Autowired
    SearchContentRepository searchContentRepository;


//    @GetMapping("/conf/task_status")
//    private Map<String,Object> taskStatus(@RequestParam String idlist){
//        Map<String,Object> map=new LinkedHashMap<>();
//        List<Optional<SearchContent>> list=new LinkedList<>();
//        for(int i=0;i<idlist.split("/").length;i++){
//            list.add(searchContentRepository.findById(Integer.parseInt(idlist.split("/")[i])));
//        }
//        map.put("code","200");
//        map.put("result",list);
//        return map;
//    }

    @GetMapping("/conf/task_status")
    private Map<String,Object> taskStatus(@RequestParam String id){
        Map<String,Object> map=new LinkedHashMap<>();
        List<Optional<SearchContent>> list=new LinkedList<>();
        int status=0;
        if(searchContentRepository.findById(Integer.parseInt(id)).isPresent()){
            status=searchContentRepository.findById(Integer.parseInt(id)).get().getStatus();
        }

        map.put("code","200");
        map.put("status",status);
        return map;
    }

    @GetMapping("/conf/task_status_findby_name")
    private Map<String,Object> taskStatusFindByName(@RequestParam String name,@RequestParam int page,@RequestParam int size){
        Map<String,Object> map=new LinkedHashMap<>();
        List<SearchContent> list = searchContentRepository.findByWord(name);
        List<SearchContent> list1=new LinkedList<>();
        for(int i=page*size;(i<page*size+size)&&(i<list.size());i++){
            list1.add(list.get(i));
        }
        map.put("code","200");
        map.put("result",list1);
        map.put("totalNum",list.size());
        return map;
    }

    @GetMapping("/conf/task_status_findall")
    private Map<String,Object> taskStatusFindall(@RequestParam Integer page,@RequestParam Integer size){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        PageRequest pageRequest=new PageRequest(page,size,sort);
        Map<String,Object> map=new LinkedHashMap<>();
        List<SearchContent> list=searchContentRepository.findAll();
        int totalNum=list.size();
        Page<SearchContent> page1 = searchContentRepository.findAll(pageRequest);
        map.put("code","200");
        map.put("totalNum",totalNum);
        map.put("result",page1);
        return map;
    }
}
