package com.example.demo1.Controller.config;


import com.example.demo1.repository.symptom_zy.DataAllRepository;
import com.example.demo1.repository.config.ConfOnlineCrawlerSettingsRepository;
import com.example.demo1.repository.patent.DataAllDrugPatentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
//@CrossOrigin
public class ConfSettings {
    @Autowired
    private ConfOnlineCrawlerSettingsRepository confOnlineCrawlerSettingsRepository;

    @Autowired
    private DataAllRepository dataAllRepository;

    @Autowired
    private DataAllDrugPatentRepository dataAllDrugPatentRepository;

//    定义job名称与id对应关系
    public int zyBaikeId = 1;



}
