package com.example.demo1.Controller.check_data;

import com.example.demo1.model.PartColums;
import com.example.demo1.model.disease_and_symptom.data_all_symptom_disease_zxy;
import com.example.demo1.model.herbal.HerbalDataAll;
import com.example.demo1.model.herbal.PartColumsHerbal;
import com.example.demo1.model.patent.PartColumsPatent;
import com.example.demo1.model.patent.data_all_drug_patent;
import com.example.demo1.repository.herbal.HerbalDataAllRepository;
import com.example.demo1.repository.patent.DataAllDrugPatentRepository;
import com.example.demo1.repository.symptom_zy.DataAllSymptomDiseaseZxyRepository;
import com.example.demo1.service.DataAllDrugHerbalService;
import com.example.demo1.service.DataAllDrugPatentService;
import com.example.demo1.service.DataAllZxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/data_all")
@CrossOrigin
public class AllController {
    @Autowired
    private DataAllSymptomDiseaseZxyRepository dataAllSymptomDiseaseZxyRepository;
    @Autowired
    private DataAllDrugPatentRepository dataAllDrugPatentRepository;
    @Autowired
    private HerbalDataAllRepository herbalDataAllRepository;

    @Autowired
    private DataAllZxyService dataAllZxyService;
    @Autowired
    private DataAllDrugHerbalService dataAllDrugHerbalService;
    @Autowired
    private DataAllDrugPatentService dataAllDrugPatentService;

    @GetMapping("/findByName")
    private Map<String,Object> searchAll(@RequestParam String name,@RequestParam(required = true,defaultValue = "-1") String type,
                                         @RequestParam(required = true,defaultValue = "-1") int status){
        Map<String,Object> map=new LinkedHashMap<>();
        List<Map<String,Object>> result=new LinkedList<>();
        List<data_all_symptom_disease_zxy> list1=new LinkedList<>();
        List<data_all_drug_patent>list2=new LinkedList<>();
        List<HerbalDataAll>list3=new LinkedList<>();

        switch (type) {
            case "中病": {
                PartColums p=new PartColums(1,status,name);
                list1.addAll(dataAllZxyService.getAllByName(p));
                break;
            }
            case "西病": {
                PartColums p=new PartColums(2,status,name);
                list1.addAll(dataAllZxyService.getAllByName(p));
                break;
            }
            case "症状": {
                PartColums p=new PartColums(3,status,name);
                list1.addAll(dataAllZxyService.getAllByName(p));
                break;
            }
            case "证型": {
                PartColums p=new PartColums(4,status,name);
                list1.addAll(dataAllZxyService.getAllByName(p));
                break;
            }
            case "成药": {
                PartColumsPatent p =new PartColumsPatent(status,name);
                list2.addAll(dataAllDrugPatentService.getAllByName(p));
                break;
            }
            case "草药": {
                PartColumsHerbal p=new PartColumsHerbal(status,name);
                list3.addAll(dataAllDrugHerbalService.getAllByName(p));
                break;
            }
            case"-1":{
                PartColums p=new PartColums(Integer.parseInt(type),status,name);
                list1.addAll(dataAllZxyService.getAllByName(p));

                PartColumsPatent p2=new PartColumsPatent(status,name);
                list2.addAll(dataAllDrugPatentService.getAllByName(p2));

                PartColumsHerbal p3=new PartColumsHerbal(status,name);
                list3.addAll(dataAllDrugHerbalService.getAllByName(p3));
                break;
            }
        }
//        List<data_all_symptom_disease_zxy> list1=dataAllSymptomDiseaseZxyRepository.findByName(name);
        list1.forEach(each->{
            Map<String,Object> mp=new LinkedHashMap<>();
            mp.put("id",each.getId());
            mp.put("name",each.getInfo_mc());
            mp.put("type",type2ch(each.getType()));
            mp.put("status",each.getStatus());
            result.add(mp);
        });

//        List<data_all_drug_patent>list2=dataAllDrugPatentRepository.findByName(name);
        list2.forEach(each->{
            Map<String,Object> mp=new LinkedHashMap<>();
            mp.put("id",each.getId());
            mp.put("name",each.getInfo_ym());
            mp.put("type","中成药");
            mp.put("status",each.getStatus());
            result.add(mp);
        });

//        List<HerbalDataAll>list3=herbalDataAllRepository.findByName(name);
        list2.forEach(each->{
            Map<String,Object> mp=new LinkedHashMap<>();
            mp.put("id",each.getId());
            mp.put("name",each.getInfo_ym());
            mp.put("type","中草药");
            mp.put("status",each.getStatus());
            result.add(mp);
        });

        map.put("code","200");
        map.put("result",result);
        return map;
    }

    String type2ch(int type){
        Map<Integer,String> map=new LinkedHashMap<>();
        map.put(1,"中医病");
        map.put(2,"西医病");
        map.put(3,"中医证型");
        map.put(4,"症状");
        return map.get(type);
    }
}
