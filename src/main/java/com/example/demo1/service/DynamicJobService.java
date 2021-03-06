package com.example.demo1.service;

import com.example.demo1.repository.job.JobEntityRepository;
import com.example.demo1.model.job.DynamicJob;
import com.example.demo1.model.jobEntity.JobEntity;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicJobService {
    @Autowired
    private JobEntityRepository jobEntityRepository;

    public JobEntity getJobEntityById(Integer id){return jobEntityRepository.getById(id);}

    public JobEntity getJobEntityByName(String name){return jobEntityRepository.getByName(name);}

    public List<JobEntity> loadJobs(){return jobEntityRepository.findAll();}

    public JobEntity updateParam(int id,JobEntity job){
        job.setParameter(String.valueOf(id));
        return job;
    }

    public JobEntity updateParamBaidu(int id,String url,JobEntity job){
        job.setParameter(String.valueOf(id));
        job.setVmParam(url);
        return job;
    }

    //获取 JobDataMap
    public JobDataMap getJobDataMap(JobEntity job){
        JobDataMap map = new JobDataMap();
        map.put("name",job.getName());
        map.put("jobGroup",job.getJobGroup());
        map.put("cronExpression",job.getCron());
        map.put("parameter", job.getParameter());
        map.put("jobDescription", job.getDescription());
        map.put("vmParam", job.getVmParam());
        map.put("runPath", job.getRunPath());
        map.put("status", job.getStatus());
        map.put("startTime",job.getStartTime());
        return map;
    }

    //获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
        public JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap map){
            return JobBuilder.newJob(DynamicJob.class)
                    .withIdentity(jobKey)
                    .withDescription(description)
                    .setJobData(map)
                    .storeDurably()
                    .build();
        }

        //获取Trigger (Job的触发器,执行规则)
        public Trigger getTrigger(JobEntity job) {
            return TriggerBuilder.newTrigger()
                    .withIdentity(job.getName(), job.getJobGroup())
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                    .build();
        }

        //改写，获取jobName+jobGroup以及现在执行的任务id
        //获取JobKey,包含Name和Group
        public JobKey getJobKey(JobEntity job,int task_id) {
            return JobKey.jobKey(job.getName()+job.getJobGroup(),String.valueOf(task_id));
        }

        //获取JobKey,包含Name和Group
        public JobKey getJobKey(JobEntity job) {
            return JobKey.jobKey(job.getName(),job.getJobGroup());
        }
}
