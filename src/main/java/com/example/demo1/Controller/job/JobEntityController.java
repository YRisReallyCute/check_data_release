package com.example.demo1.Controller.job;

import com.example.demo1.Repository.SearchContentRepository;
import com.example.demo1.Repository.job.JobEntityRepository;
import com.example.demo1.Repository.patent.DataAllDrugRepository;
import com.example.demo1.Repository.symptom_zy.DataAllRepository;
import com.example.demo1.model.SearchContent;
import com.example.demo1.model.disease_and_symptom.data_all;
import com.example.demo1.model.jobEntity.JobEntity;
import com.example.demo1.model.patent.data_all_drug;
import com.example.demo1.service.DynamicJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@Slf4j
public class JobEntityController {
    @Autowired
    private JobEntityRepository jobEntityRepository;

    @Autowired
    private DataAllRepository dataAllRepository;

    @Autowired
    private DataAllDrugRepository dataAllDrugRepository;

    @Autowired
    private SearchContentRepository searchContentRepository;

    @Autowired
    private DynamicJobService jobService;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    //    定义job名称与id对应关系
    public int zyBaikeId = 1;

    @GetMapping("/conf/crawler_info_findall")
    private Map<String,Object> getSetInfo(){
        Map<String,Object> map = new LinkedHashMap<>();
        List<JobEntity> list=jobEntityRepository.getSymptomZyGroup();
        int baike=0,disease_zy=0,disease_xy=0,symptom_zy=0,symptom_xy=0,zy=0,xy=0,human=0;
        baike=dataAllRepository.baike_num();
        disease_xy=dataAllRepository.disease_xy_num();
        disease_zy=dataAllRepository.disease_zy_num();
        symptom_xy=dataAllRepository.symptom_xy_num();
        symptom_zy=dataAllRepository.symptom_zy_num();
        zy=dataAllRepository.zy_num();
        xy=dataAllRepository.xy_num();
        human=dataAllRepository.human_num();

        for(int i=0;i<list.size();i++){
            if(list.get(i).getName().equals("百度百科")){
                list.get(i).setTotalNum(baike);
            }
            else if(list.get(i).getName().equals("中医症状")){
                list.get(i).setTotalNum(symptom_zy);
            }
            else if(list.get(i).getName().equals("西医症状")){
                list.get(i).setTotalNum(symptom_xy);
            }
            else if(list.get(i).getName().equals("中医疾病")){
                list.get(i).setTotalNum(disease_zy);
            }
            else if(list.get(i).getName().equals("西医疾病")){
                list.get(i).setTotalNum(symptom_xy);
            }
            else{}
        }

        map.put("code","200");
        map.put("result",list);
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

        int add_zy=0,add_patent=0,add_herbal=0;
        for(int i=0;i<chlist.split("/").length;i++){
            if(chlist.split("/")[i].equals("中医症状库")){
                map.put("symptomZyNum",list_symptom_zy.size());
                map.put("SymptomZyList",list_symptom_zy);
                if(list_symptom_zy.size()==0){add_zy=1;}
            }
            else if(chlist.split("/")[i].equals("中成药库")){
                map.put("patentNum",list_patent.size());
                map.put("PatentList",list_patent);
                if(list_patent.size()==0){add_patent=1;}
            }
            else if(chlist.split("/")[i].equals("中草药库")){
                System.out.println("中草药库内容检索");
            }
            else{}
        }
        //没有找到
        if(Objects.isNull(searchContentRepository.findBySearchContent(content))) {
            SearchContent s;
            if (add_patent + add_zy + add_herbal != 0) {
                s = new SearchContent(content, add_zy, add_patent, add_herbal, 2);
            } else {
                s = new SearchContent(content, add_zy, add_patent, add_herbal, 1);
            }
            searchContentRepository.saveAndFlush(s);
        }
        else{
            searchContentRepository.updateStatus(content);
        }
        return map;
    }

    @GetMapping("/conf/save_search_content")
    private Map<String,Object> saveSearchContent(@RequestParam String content,@RequestParam String chlist){
        Map<String,Object> map = new LinkedHashMap<>();

        String scheduler_result="";
        for(int i=0;i<chlist.split("/").length;i++){
            if(chlist.split("/")[i].equals("中医症状库")){
                jobService.updateParam(zyBaikeId,"search");
                try{
                    scheduler_result=refreshById(zyBaikeId);
                }catch (SchedulerException e){
                    log.info("error: SchedulerException! ");
                    map.put("code","400");
                    map.put("scheduler_result","error: SchedulerException! ");
                    return map;
                }
            }
            else if(chlist.split("/")[i].equals("中成药库")){
                System.out.println("中成药库内容检索");
            }
            else if(chlist.split("/")[i].equals("中草药库")){
                System.out.println("中草药库内容检索");
            }
            else{}
        }

        map.put("code","200");
        map.put("scheduler_result","job is running! ");
        return map;
    }

    public String generateCron(String time, String num, String gap){
        String cron="0 ";
        cron+=time.split(":")[1]+" "+time.split(":")[0]+" ";

        if(gap.equals("YEAR")){

        }

        return cron;
    }

    public String refreshById(int id) throws SchedulerException {
        String result;
        JobEntity entity = jobService.getJobEntityById(id);
        if(Objects.isNull(entity)) return "error: id is not exits ";
        synchronized (log){
            JobKey jobKey = jobService.getJobKey(entity);
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseJob(jobKey);
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(),jobKey.getGroup()));
            scheduler.deleteJob(jobKey);
            JobDataMap map = jobService.getJobDataMap(entity);
            JobDetail jobDetail = jobService.getJobDetail(jobKey,entity.getDescription(),map);
            scheduler.scheduleJob(jobDetail,jobService.getTrigger(entity));
            result = "Luntch job : "+entity.getName() +"\t at Path: "+entity.getRunPath()+" success !";
        }
        return result;
    }

}
