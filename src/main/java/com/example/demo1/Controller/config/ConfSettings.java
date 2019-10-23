package com.example.demo1.Controller.config;


import com.example.demo1.Repository.symptom_zy.DataAllRepository;
import com.example.demo1.Repository.config.ConfOnlineCrawlerSettingsRepository;
import com.example.demo1.Repository.patent.DataAllDrugRepository;
import com.example.demo1.model.config.conf_online_crawler_settings;
import com.example.demo1.model.disease_and_symptom.data_all;
import com.example.demo1.model.patent.data_all_drug;
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

//    定义job名称与id对应关系
    public int zyBaikeId = 1;



}
