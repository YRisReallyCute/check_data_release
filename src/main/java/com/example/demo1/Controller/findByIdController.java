package com.example.demo1.Controller;


import com.example.demo1.Repository.*;
import com.example.demo1.functions.ReplaceLabels;
import com.example.demo1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class findByIdController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    XyRepository xyRepository;

    @Autowired
    ZyRepository zyRepository;

    @Autowired
    DiseaseXyRepository diseaseXyRepository;

    @Autowired
    DiseaseZyRepository diseaseZyRepository;

    @Autowired
    SymptomXyRepository symptomXyRepository;

    @Autowired
    SymptomZyRepository symptomZyRepository;

    @Autowired
    BaikeDiseaseXyRepository baikeDiseaseXyRepository;

    @Autowired
    BaikeDiseaseZyRepository baikeDiseaseZyRepository;

    @Autowired
    BaikeSymptomXyRepository baikeSymptomXyRepository;

    @Autowired
    ReplaceLabels replaceLabels;


    /** 1
     * model:   disease_xy
     * repository:  DiseaseXyRepository
     * table:   data_disease_xy_zgyyxxcxpt
     */
    @GetMapping(path = "/disease_xy_zgyyxxcxpt/findById")
    //Optional可以避免null exception
    public Map<String,Object> diseaseXyZgyyxxcxpt(@RequestParam Integer id)
    {
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        Optional<disease_xy> list= diseaseXyRepository.findById(id);

        //替换标签<h1>,<h2>
        list.get().replaceStr(replaceLabels);

//        Optional<disease_xy> list2=Optional.of(list.get());

        if (!list.isPresent()){
            map.put("code","404");
        }
        else {
            map.put("code", "200");
            map.put("result", list);
        }
        return map;
    }


    /** 2
     * model:   disease_zy
     * repository:  DiseaseZyRepository
     * table:   data_disease_zy_zgyyxxcxpt
     */
    @GetMapping(path = "/disease_zy_zgyyxxcxpt/findById")
    //Optional可以避免null exception
    public Map<String,Object> diseaseZyZgyyxxcxpt(@RequestParam Integer id)
    {
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        Optional<disease_zy> list= diseaseZyRepository.findById(id);

        //替换标签<h1>,<h2>
        list.get().replaceStr(replaceLabels);

        if (!list.isPresent()){
            map.put("code","404");
        }
        else {
            map.put("code", "200");
            map.put("result", list);
        }
        return map;
    }


    /** 3
     * model:   zgyyxxcxpt_xy
     * repository:  XyRepository
     * table:   data_disease_xy_zgyyxxcxpt_old
     */
    @GetMapping(path = "/disease_xy_zgyyxxcxpt_old/findById")
    //Optional可以避免null exception
    public Map<String,Object> diseaseXyZgyyxxcxptOld(@RequestParam Integer id)
    {
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        Optional<zgyyxxcxpt_xy> list= xyRepository.findById(id);

        //替换标签<h1>,<h2>
        list.get().replaceStr(replaceLabels);

//        Optional<disease_xy> list2=Optional.of(list.get());

        if (!list.isPresent()){
            map.put("code","404");
        }
        else {
            map.put("code", "200");
            map.put("result", list);
        }
        return map;
    }


    /** 4
     * model:   zgyyxxcxpt_zy
     * repository:  ZyRepository
     * table:   data_disease_zy_zgyyxxcxpt_old
     */
    @GetMapping(path = "/disease_zy_zgyyxxcxpt_old/findById")
    //Optional可以避免null exception
    public Map<String,Object> diseaseZyZgyyxxcxptOld(@RequestParam Integer id)
    {
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        Optional<zgyyxxcxpt_zy> list= zyRepository.findById(id);

        //替换标签<h1>,<h2>
        list.get().replaceStr(replaceLabels);

        if (!list.isPresent()){
            map.put("code","404");
        }
        else {
            map.put("code", "200");
            map.put("result", list);
        }
        return map;
    }


    /** 5
     * model:   symptom_xy
     * repository:  SymptomXyRepository
     * table:   data_symptom_xy_zgyyxxcxpt
     */
    @GetMapping(path = "/symptom_xy_zgyyxxcxpt/findById")
    //Optional可以避免null exception
    public Map<String,Object> symptomXyZgyyxxcxpt(@RequestParam Integer id)
    {
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        Optional<symptom_xy> list= symptomXyRepository.findById(id);

        //替换标签<h1>,<h2>
        list.get().replaceStr(replaceLabels);

        if (!list.isPresent()){
            map.put("code","404");
        }
        else {
            map.put("code", "200");
            map.put("result", list);
        }
        return map;
    }


    /** 6
     * model:   symptom_zy
     * repository:  SymptomZyRepository
     * table:   data_symptom_zy_zgyyxxcxpt
     */
    @GetMapping(path = "/symptom_zy_zgyyxxcxpt/findById")
    //Optional可以避免null exception
    public Map<String,Object> symptomZyZgyyxxcxpt(@RequestParam Integer id)
    {
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        Optional<symptom_zy> list= symptomZyRepository.findById(id);

        //替换标签<h1>,<h2>
        list.get().replaceStr(replaceLabels);

        if (!list.isPresent()){
            map.put("code","404");
        }
        else {
            map.put("code", "200");
            map.put("result", list);
        }
        return map;
    }


    /** 7
     * model:   baike_disease_xy
     * repository:  BaidubaikeDiseaseXyRepository
     * table:   data_disease_xy_baidubaike
     */
    @GetMapping(path = "/disease_xy_baidubaike/findById")
    //Optional可以避免null exception
    public Map<String,Object> diseaseXyBaidubaike(@RequestParam Integer id)
    {
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        Optional<baike_disease_xy> list= baikeDiseaseXyRepository.findById(id);

        //替换标签<h1>,<h2>
        list.get().replaceStr(replaceLabels);

//        Optional<disease_xy> list2=Optional.of(list.get());

        if (!list.isPresent()){
            map.put("code","404");
        }
        else {
            map.put("code", "200");
            map.put("result", list);
        }
        return map;
    }


    /** 8
     * model:   baike_disease_zy
     * repository:  BaidubaikeDiseaseZyRepository
     * table:   data_disease_zy_baidubaike
     */
    @GetMapping(path = "/disease_zy_baidubaike/findById")
    //Optional可以避免null exception
    public Map<String,Object> diseaseZyBaidubaike(@RequestParam Integer id)
    {
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        Optional<baike_disease_zy> list= baikeDiseaseZyRepository.findById(id);

        //替换标签<h1>,<h2>
        list.get().replaceStr(replaceLabels);

        if (!list.isPresent()){
            map.put("code","404");
        }
        else {
            map.put("code", "200");
            map.put("result", list);
        }
        return map;
    }


    /** 9
     * model:   baike_symptom_xy
     * repository:  BaidubaikeSymptomXyRepository
     * table:   data_symptom_xy_baidubaike
     */
    @GetMapping(path = "/symptom_xy_baidubaike/findById")
    //Optional可以避免null exception
    public Map<String,Object> symptomXyBaidubaike(@RequestParam Integer id)
    {
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        Optional<symptom_xy> list= symptomXyRepository.findById(id);

        //替换标签<h1>,<h2>
        list.get().replaceStr(replaceLabels);

        if (!list.isPresent()){
            map.put("code","404");
        }
        else {
            map.put("code", "200");
            map.put("result", list);
        }
        return map;
    }


    /** 10
     * model:   User
     * repository:  UserRepository
     * table:   data_symptom_zy_baidubaike
     */
    @GetMapping(path = "/symptom_zy_baidubaike/findById")
    //Optional可以避免null exception
    public Map<String,Object> symptomZyBaidubaike(@RequestParam Integer id)
    {
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        Optional<User> list= userRepository.findById(id);

        //替换标签<h1>,<h2>
        list.get().replaceStr(replaceLabels);

        if (!list.isPresent()){
            map.put("code","404");
        }
        else {
            map.put("code", "200");
            map.put("result", list);
        }
        return map;
    }

}