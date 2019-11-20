package com.example.demo1.Controller.job;

import com.example.demo1.model.jobEntity.JobEntity;
import com.example.demo1.service.DynamicJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@Slf4j
public class JobEntityController {
    @Autowired
    private DynamicJobService jobService;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @GetMapping("/test/job")
    private String testJob(){
        try{assiginTask(returnJobId("diseaseZy"),96);
            return "success";
        }
        catch (SchedulerException e){
            System.out.println("Schedule error!");
            return "failed";
        }
//        try{
//            String res= assiginTask(returnJobId("baikeZy"),32);
//            log.info(res);
//            return "success";
//        }catch (SchedulerException e){
//            System.out.println("Schedule error!");
//            return "failed";
//        }
    }

    private String assiginTask(int id,int job_id) throws SchedulerException {
        String result;
        JobEntity entity = jobService.getJobEntityById(id);
        //设置上下文参数
        entity=jobService.updateParam(job_id,entity);
        if(Objects.isNull(entity)) return "error: id is not exits ";
        synchronized (log){
            JobKey jobKey = jobService.getJobKey(entity);
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseJob(jobKey);
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(),jobKey.getGroup()));
            scheduler.deleteJob(jobKey);
            JobDataMap map = jobService.getJobDataMap(entity);
            JobDetail jobDetail = jobService.getJobDetail(jobKey,entity.getDescription(),map);
            Trigger trigger=jobService.getTrigger(entity);
            Trigger trigger1=TriggerBuilder.newTrigger().withPriority(1).startNow().build();
            scheduler.scheduleJob(jobDetail,trigger1);
//            scheduler.start();330
            result = "Luntch job : "+entity.getName() +"\t at Path: "+entity.getRunPath()+" success !";
        }
        return result;
    }

    public Integer returnJobId(String s){
        Map<String,Integer> map = new LinkedHashMap<>();
        map.put("baikeZy",1);
        map.put("symptomZy",2);
        map.put("symptomXy",3);
        map.put("diseaseZy",4);
        map.put("diseaseXy",5);
        map.put("patentYaobiao",6);
        map.put("patentBaodian",7);
        return map.get(s);
    }
}