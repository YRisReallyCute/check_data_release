package com.example.demo1.Controller.config;


import com.example.demo1.Repository.DataAllRepository;
import com.example.demo1.Repository.config.ConfOnlineCrawlerSettingsRepository;
import com.example.demo1.Repository.patent.DataAllDrugRepository;
import com.example.demo1.model.config.conf_online_crawler_settings;
import com.example.demo1.model.disease_and_symptom.data_all;
import com.example.demo1.model.patent.data_all_drug;
import com.example.demo1.service.DataAllDrugPatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
//@CrossOrigin
public class ConfSettings {
    @Autowired
    private ConfOnlineCrawlerSettingsRepository confOnlineCrawlerSettingsRepository;

    @Autowired
    private DataAllRepository dataAllRepository;

    @Autowired
    private DataAllDrugRepository dataAllDrugRepository;

    @GetMapping("/conf/save_search_content")
    private Map<String,Object> saveSearchContent(@RequestParam String chlist){
        Map<String,Object> map = new LinkedHashMap<>();
        for(int i=0;i<chlist.split("/").length;i++){

        }
        map.put("code","200");
        return map;
    }



    @GetMapping("/conf/search_content")
    private Map<String,Object> searchContent(@RequestParam String content, @RequestParam String chlist){
        Map<String,Object> map = new LinkedHashMap<>();
        List<data_all_drug> list_patent = dataAllDrugRepository.findByName(content);
        List<data_all> list_symptom_zy = dataAllRepository.findByName(content);
        int total_size = list_patent.size()+list_symptom_zy.size();
        map.put("code","200");
        map.put("totalNum",total_size);

        for(int i=0;i<chlist.split("/").length;i++){
            if(chlist.split("/")[i].equals("中医症状库")){
                map.put("symptomZyNum",list_symptom_zy.size());
                map.put("SymptomZyList",list_symptom_zy);
            }
            else if(chlist.split("/")[i].equals("中成药库")){
                map.put("patentNum",list_patent.size());
                map.put("PatentList",list_patent);
            }
            else if(chlist.split("/")[i].equals("中草药库")){

        }
            else{}
        }

        return map;
    }



    @GetMapping("/conf/crawler_info_findall")
    private Map<String,Object> getSetInfo(){
        Map<String,Object> map = new LinkedHashMap<>();
        List<conf_online_crawler_settings> list=confOnlineCrawlerSettingsRepository.findSymptomZy();
        int baike,disease_zy,disease_xy,symptom_zy,symptom_xy,zy,xy,human;
        baike=dataAllRepository.baike_num();
        disease_xy=dataAllRepository.disease_xy_num();
        disease_zy=dataAllRepository.disease_zy_num();
        symptom_xy=dataAllRepository.symptom_xy_num();
        symptom_zy=dataAllRepository.symptom_zy_num();
        zy=dataAllRepository.zy_num();
        xy=dataAllRepository.xy_num();
        human=dataAllRepository.human_num();

        for(int i=0;i<list.size();i++){
            if(list.get(i).getOrigin().equals("百度百科")){
                list.get(i).setTotalNum(baike);
            }
            else if(list.get(i).getOrigin().equals("中医症状")){
                list.get(i).setTotalNum(symptom_zy);
            }
            else if(list.get(i).getOrigin().equals("西医症状")){
                list.get(i).setTotalNum(symptom_xy);
            }
            else if(list.get(i).getOrigin().equals("中医疾病")){
                list.get(i).setTotalNum(disease_zy);
            }
            else if(list.get(i).getOrigin().equals("西医疾病")){
                list.get(i).setTotalNum(symptom_xy);
            }
            else{}
        }

        map.put("code","200");
        map.put("result",list);
        return map;
    }

}
