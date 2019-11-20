package com.example.demo1.Controller.job;

import com.example.demo1.Repository.job.JobEntityRepository;
import com.example.demo1.Repository.job.SearchContentRepository;
import com.example.demo1.Repository.symptom_zy.*;
import com.example.demo1.model.disease_and_symptom.data_all;
import com.example.demo1.model.disease_and_symptom.data_all_symptom_disease_zxy;
import com.example.demo1.model.disease_and_symptom.symptom_zy;
import com.example.demo1.model.job.SearchContent;
import com.example.demo1.model.jobEntity.JobEntity;
import com.example.demo1.service.DynamicJobService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@Slf4j
public class SearchWordController {
    @Autowired
    private JobEntityRepository jobEntityRepository;
    @Autowired
    private DataAllRepository dataAllRepository;

    @Autowired
    private DataAllSymptomDiseaseZxyRepository dataAllSymptomDiseaseZxyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SymptomZyRepository symptomZyRepository;
    @Autowired
    private SymptomXyRepository symptomXyRepository;
    @Autowired
    private DiseaseZyRepository diseaseZyRepository;
    @Autowired
    private DiseaseXyRepository diseaseXyRepository;
    @Autowired
    private SearchContentRepository searchContentRepository;
    @Autowired
    private DynamicJobService jobService;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public int DZY=1,DXY=2,SZY=3,SXY=4;

    public int dirtyStatus=4;


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

    @GetMapping("/conf/refresh_task_idlist")
    private Map<String,Object> refreshById(@RequestParam String idList){
        Map<String,Object> map=new LinkedHashMap<>();
        String code="200";
        for(String id:idList.split("/")){
            Optional<SearchContent> searchContent=searchContentRepository.findById(Integer.parseInt(id));
            int job_id;
            if(searchContent.isPresent()){
                job_id=searchContent.get().getJob_id();
                try {
                    String res = assiginTask(job_id, Integer.parseInt(id));
                }catch (SchedulerException e){
                    code="401";
                    System.out.println("Schedule error");
                }
            }
            else{
                code="400";
            }
        }
        map.put("code",code);
        return map;
    }

    @GetMapping("/conf/search_content")
    private Map<String, Object> searchContent(@RequestParam String content,@RequestParam String chlist){

        Map<String,Object> map=new LinkedHashMap<>();
        String idList="";
        int status=0;
        Date dt;
        SearchContent searchContent;

        List<data_all_symptom_disease_zxy> list=new LinkedList<>();

        for(int i=0;i<chlist.split("/").length;i++){
            if(chlist.split("/")[i].equals("中医病")){
                list=dataAllSymptomDiseaseZxyRepository.findLikeNameDiseaseZy(content);
                if(list.size()>0){
                    for(data_all_symptom_disease_zxy each_da:list){
                        //初始化相关信息
                        String current_word = each_da.getInfo_mc();
                        int dataAllId = each_da.getId();
                        dt = new Date();
                        //有对应来源进行更新
                        if(each_da.getOrigin_disease_zy()==1) {
                            status = 10;
                            String url = diseaseZyRepository.getByInfo_mc(current_word).get(0).getOrigin_url();
                            //根据当前词条创建一条任务
                            searchContent = new SearchContent(current_word, returnJobId("diseaseZy"), status, url, dt, chlist.split("/")[i], dataAllId);
                            searchContentRepository.saveAndFlush(searchContent);
                            idList += String.valueOf(searchContent.getId()) + "/";
                        }
                        //根据百度百科来源情况创建任务
                        if(each_da.getOrigin_baike()==1){
                            status=10;//等待更新
                        }
                        else{
                            status=20;//等待插入
                        }
                        searchContent=new SearchContent(current_word,returnJobId("baidubaike"),status,"",dt,chlist.split("/")[i],dataAllId);
                        searchContentRepository.saveAndFlush(searchContent);
                        idList+=String.valueOf(searchContent.getId())+"/";
                    }

                }
                else{
                    status=20;//等待插入
                    dt=new Date();
                    int type=DZY;
                    //先创建一条总表的脏数据
                    data_all_symptom_disease_zxy da=new data_all_symptom_disease_zxy(content,type,dirtyStatus,0,0,0,0,0);
                    dataAllSymptomDiseaseZxyRepository.saveAndFlush(da);

                    searchContent=new SearchContent(content,returnJobId("baidubaike"),status,"",dt,chlist.split("/")[i]);
                    searchContentRepository.saveAndFlush(searchContent);
                    idList+=String.valueOf(searchContent.getId())+"/";
                }
            }
            else if(chlist.split("/")[i].equals("西医病")){
                list=dataAllSymptomDiseaseZxyRepository.findLikeNameDiseaseXy(content);
                if(list.size()>0){
                    for(data_all_symptom_disease_zxy each_da:list){
                        //初始化
                        String current_word = each_da.getInfo_mc();
                        int dataAllId = each_da.getId();
                        dt = new Date();
                        //根据来源更新
                        if(each_da.getOrigin_disease_xy()==1) {
                            status = 10;
                            String url = diseaseXyRepository.getByInfo_mc(current_word).get(0).getOrigin_url();
                            //根据当前词条创建一条任务
                            searchContent = new SearchContent(current_word, returnJobId("diseaseXy"), status, url, dt, chlist.split("/")[i], dataAllId);
                            searchContentRepository.saveAndFlush(searchContent);
                            idList += String.valueOf(searchContent.getId()) + "/";
                        }
                        //根据百度百科来源情况创建任务
                        if(each_da.getOrigin_baike()==1){
                            status=10;//等待更新
                        }
                        else{
                            status=20;//等待插入
                        }
                        searchContent=new SearchContent(current_word,returnJobId("baidubaike"),status,"",dt,chlist.split("/")[i],dataAllId);
                        searchContentRepository.saveAndFlush(searchContent);
                        idList+=String.valueOf(searchContent.getId())+"/";
                    }
                }
                else{
                    status=20;//等待插入
                    dt=new Date();
                    int type=DXY;
                    //先创建一条总表的脏数据
                    data_all_symptom_disease_zxy da=new data_all_symptom_disease_zxy(content,type,dirtyStatus,0,0,0,0,0);
                    dataAllSymptomDiseaseZxyRepository.saveAndFlush(da);

                    searchContent=new SearchContent(content,returnJobId("baidubaike"),status,"",dt,chlist.split("/")[i]);
                    searchContentRepository.saveAndFlush(searchContent);
                    idList+=String.valueOf(searchContent.getId())+"/";
                }
            }
            else if(chlist.split("/")[i].equals("中医证型")){
                list=dataAllSymptomDiseaseZxyRepository.findLikeNameSymptomZy(content);
                if(list.size()>0){
                    for(data_all_symptom_disease_zxy each_da:list){
                        //初始化
                        String current_word=each_da.getInfo_mc();
                        int dataAllId=each_da.getId();
                        dt = new Date();
                        if(each_da.getOrigin_symptom_zy()==1) {
                            status = 10;
                            List<symptom_zy> tmp = symptomZyRepository.getByInfo_mc(current_word);
                            symptom_zy tmp_zy = tmp.get(0);
                            String url = symptomZyRepository.getByInfo_mc(current_word).get(0).getOrigin_url();
                            //根据当前词条创建一条任务
                            searchContent = new SearchContent(current_word, returnJobId("symptomZy"), status, url, dt, chlist.split("/")[i], dataAllId);
                            searchContentRepository.saveAndFlush(searchContent);
                            idList += String.valueOf(searchContent.getId()) + "/";
                        }
                        //根据百度百科来源情况创建任务
                        if(each_da.getOrigin_baike()==1){
                            status=10;//等待更新
                        }
                        else{
                            status=20;//等待插入
                        }
                        searchContent=new SearchContent(current_word,returnJobId("baidubaike"),status,"",dt,chlist.split("/")[i],dataAllId);
                        searchContentRepository.saveAndFlush(searchContent);
                        idList+=String.valueOf(searchContent.getId())+"/";
                    }
                }
                else{
                    status=20;//等待插入
                    dt=new Date();
                    int type=SZY;
                    //先创建一条总表的脏数据
                    data_all_symptom_disease_zxy da=new data_all_symptom_disease_zxy(content,type,dirtyStatus,0,0,0,0,0);
                    dataAllSymptomDiseaseZxyRepository.saveAndFlush(da);

                    searchContent=new SearchContent(content,returnJobId("baidubaike"),status,"",dt,chlist.split("/")[i]);
                    searchContentRepository.saveAndFlush(searchContent);
                    idList+=String.valueOf(searchContent.getId())+"/";
                }
            }
            else if(chlist.split("/")[i].equals("症状")){
                list=dataAllSymptomDiseaseZxyRepository.findLikeNameSymptomXy(content);
                if(list.size()>0){
                    for(data_all_symptom_disease_zxy each_da:list){
                        //初始化
                        String current_word=each_da.getInfo_mc();
                        int dataAllId=each_da.getId();
                        dt = new Date();
                        if(each_da.getOrigin_symptom_xy()==1) {
                            status = 10;
                            String url = symptomXyRepository.getByInfo_mc(current_word).get(0).getOrigin_url();
                            //根据当前词条创建一条任务
                            searchContent = new SearchContent(current_word, returnJobId("symptomXy"), status, url, dt, chlist.split("/")[i], dataAllId);
                            searchContentRepository.saveAndFlush(searchContent);
                            idList += String.valueOf(searchContent.getId()) + "/";
                        }
                        //根据百度百科来源情况创建任务
                        if(each_da.getOrigin_baike()==1){
                            status=10;//等待更新
                        }
                        else{
                            status=20;//等待插入
                        }
                        searchContent=new SearchContent(current_word,returnJobId("baidubaike"),status,"",dt,chlist.split("/")[i],dataAllId);
                        searchContentRepository.saveAndFlush(searchContent);
                        idList+=String.valueOf(searchContent.getId())+"/";
                    }
                }
                else{
                    status=20;//等待插入
                    dt=new Date();
                    int type=SXY;
                    //先创建一条总表的脏数据
                    data_all_symptom_disease_zxy da=new data_all_symptom_disease_zxy(content,type,dirtyStatus,0,0,0,0,0);
                    dataAllSymptomDiseaseZxyRepository.saveAndFlush(da);

                    searchContent=new SearchContent(content,returnJobId("baidubaike"),status,"",dt,chlist.split("/")[i]);
                    searchContentRepository.saveAndFlush(searchContent);
                    idList+=String.valueOf(searchContent.getId())+"/";
                }
            }
            else{}
        }

//        List<data_all_symptom_disease_zxy>list1=dataAllSymptomDiseaseZxyRepository.findByName(content);
//        Object list1=dataAllSymptomDiseaseZxyRepository.findByName1(content);
        map.put("idList",idList);
        return map;
    }


    /**
     *
     * @param content，搜索词
     * @param chlist，搜索范围
     * @return
     */
    @GetMapping("/conf/search_save_content")
    private Map<String,Object> searchSaveContent(@RequestParam String content, @RequestParam String chlist){
        Map<String,Object> map = new LinkedHashMap<>();
        String idList="";
        int status=0;
        Date dt;
        SearchContent searchContent;
        String code="200";

        List<String> cl= Arrays.asList(chlist.split("/"));
        for(int i=0;i<chlist.split("/").length;i++){
            if(cl.get(i).equals("中医病")){
                List<data_all_symptom_disease_zxy> list=dataAllSymptomDiseaseZxyRepository.findInDiseaseZy(content);
                if(list.size()>0){
                    //找到了
                    code="200";
                    status=10;
                    dt=new Date();
                    String url=diseaseZyRepository.getByInfo_mc(content).get(0).getOrigin_url();
                    searchContent=new SearchContent(content,returnJobId("diseaseZy"),status,url,dt,chlist.split("/")[i]);
                    searchContentRepository.saveAndFlush(searchContent);
                    idList+=String.valueOf(searchContent.getId())+"/";

                    //进行更新
                    try{assiginTask(returnJobId("diseaseZy"),searchContent.getId());}catch (SchedulerException e){
                        System.out.println("Schedule error!");
                    }

                    //判断是否有百度百科来源，有的话一并更新
                    if(list.get(0).getOrigin_baike()==1){
//                        url=userRepository.findByName(content).get(0).getOrigin_url();
                        searchContent=new SearchContent(content,returnJobId("baiduBaike"),status,"",dt,chlist.split("/")[i]);
                        searchContentRepository.saveAndFlush(searchContent);
                        idList+=String.valueOf(searchContent.getId())+"/";

                        try{
                            String res= assiginTask(returnJobId("baiduBaike"),searchContent.getId());
                            log.info(res);
                        }catch (SchedulerException e){
                            System.out.println("Schedule error!");
                        }
                    }
                }
                else{
                    code="404";
                }
            }
            else if(cl.get(i).equals("西医病")){
                List<data_all_symptom_disease_zxy> list=dataAllSymptomDiseaseZxyRepository.findInDiseaseXy(content);
                if(list.size()>0){
                    //找到了
                    code="200";
                    status=10;
                    dt=new Date();
                    String url=diseaseXyRepository.getByInfo_mc(content).get(0).getOrigin_url();
                    searchContent=new SearchContent(content,returnJobId("diseaseXy"),status,url,dt,chlist.split("/")[i]);
                    searchContentRepository.saveAndFlush(searchContent);
                    idList+=String.valueOf(searchContent.getId())+"/";

                    //进行更新
                    try{assiginTask(returnJobId("diseaseXy"),searchContent.getId());}catch (SchedulerException e){
                        System.out.println("Schedule error!");
                    }

                    //判断是否有百度百科来源，有的话一并更新
                    if(list.get(0).getOrigin_baike()==1){
//                        url=userRepository.findByName(content).get(0).getOrigin_url();
                        searchContent=new SearchContent(content,returnJobId("baiduBaike"),status,"",dt,chlist.split("/")[i]);
                        searchContentRepository.saveAndFlush(searchContent);
                        idList+=String.valueOf(searchContent.getId())+"/";

                        try{
                            String res= assiginTask(returnJobId("baiduBaike"),searchContent.getId());
                            log.info(res);
                        }catch (SchedulerException e){
                            System.out.println("Schedule error!");
                        }
                    }
                }
                else{
                    code="404";
                }
            }
            else if(cl.get(i).equals("中医证型")){
                List<data_all_symptom_disease_zxy> list=dataAllSymptomDiseaseZxyRepository.findInSymptomZy(content);
                if(list.size()>0){
                    //找到了
                    code="200";
                    status=10;
                    dt=new Date();
//                    String url=symptomZyRepository.getByInfo_mc(content).get(0).getOrigin_url();
                    searchContent=new SearchContent(content,returnJobId("symptomZy"),status,"",dt,chlist.split("/")[i]);
                    searchContentRepository.saveAndFlush(searchContent);
                    idList+=String.valueOf(searchContent.getId())+"/";

                    //进行更新
                    try{assiginTask(returnJobId("symptomZy"),searchContent.getId());}catch (SchedulerException e){
                        System.out.println("Schedule error!");
                    }

                    //判断是否有百度百科来源，有的话一并更新
                    if(list.get(0).getOrigin_baike()==1){
//                        url=userRepository.findByName(content).get(0).getOrigin_url();
                        searchContent=new SearchContent(content,returnJobId("baiduBaike"),status,"",dt,chlist.split("/")[i]);
                        searchContentRepository.saveAndFlush(searchContent);
                        idList+=String.valueOf(searchContent.getId())+"/";

                        try{
                            String res= assiginTask(returnJobId("baiduBaike"),searchContent.getId());
                            log.info(res);
                        }catch (SchedulerException e){
                            System.out.println("Schedule error!");
                        }
                    }
                }
                else{
                    code="404";
                }
            }
            else if(cl.get(i).equals("症状")){
                List<data_all_symptom_disease_zxy> list=dataAllSymptomDiseaseZxyRepository.findInSymptomXy(content);
                if(list.size()>0){
                    //找到了
                    code="200";
                    status=10;
                    dt=new Date();
                    String url=symptomXyRepository.getByInfo_mc(content).get(0).getOrigin_url();
                    searchContent=new SearchContent(content,returnJobId("symptomXy"),status,url,dt,chlist.split("/")[i]);
                    searchContentRepository.saveAndFlush(searchContent);
                    idList+=String.valueOf(searchContent.getId())+"/";

                    //进行更新
                    try{assiginTask(returnJobId("symptomXy"),searchContent.getId());}catch (SchedulerException e){
                        System.out.println("Schedule error!");
                    }

                    //判断是否有百度百科来源，有的话一并更新
                    if(list.get(0).getOrigin_baike()==1){
                        url=userRepository.findByName(content).get(0).getOrigin_url();
                        searchContent=new SearchContent(content,returnJobId("baiduBaike"),status,url,dt,chlist.split("/")[i]);
                        searchContentRepository.saveAndFlush(searchContent);
                        idList+=String.valueOf(searchContent.getId())+"/";

                        try{
                            String res= assiginTask(returnJobId("baiduBaike"),searchContent.getId());
                            log.info(res);
                        }catch (SchedulerException e){
                            System.out.println("Schedule error!");
                        }
                    }
                }
                else{
                    code="404";
                }

            }
            else if(cl.get(i).equals("中成药")){
            }
            else{
                System.out.println(cl.get(i));
            }
        }

//
        map.put("idlist",idList);
        map.put("code",code);
        return map;
    }

    @GetMapping("/conf/add_search_task")
    private Map<String,Object>addTask(@RequestParam String content,@RequestParam String chlist){
        Map<String,Object> map=new LinkedHashMap<>();
        int type,status=4;
        String idList="";
        for(int i=0;i<chlist.split("/").length;i++){
            if(chlist.split("/")[i].equals("中医病")){
                type=DZY;
            }
            else if(chlist.split("/")[i].equals("西医病")){
                type=DXY;
            }
            else if(chlist.split("/")[i].equals("中医证型")){
                type=SZY;
            }
            else if(chlist.split("/")[i].equals("症状")){
                type=SXY;
            }
            else{type=0;}

            if(type!=0){
                //没找到的词汇，首先向总表中插入一条数据
                data_all_symptom_disease_zxy da=new data_all_symptom_disease_zxy(content,type,status,0,0,0,0,0);
                dataAllSymptomDiseaseZxyRepository.saveAndFlush(da);

                //再添加一条百度百科搜索任务
                Date dt=new Date();
                status=20;
                SearchContent searchContent=new SearchContent(content,returnJobId("baiduBaike"),status,"",dt,chlist.split("/")[i]);
                searchContentRepository.saveAndFlush(searchContent);
                idList+=String.valueOf(searchContent.getId())+"/";

                try{
                    String res= assiginTask(returnJobId("baiduBaike"),searchContent.getId());
                    log.info(res);
                }catch (SchedulerException e){
                    System.out.println("Schedule error!");
                }

                map.put("code","200");
                map.put("idlist",idList);
            }
            else{
                map.put("code","400");
                map.put("idlist",idList);
            }
        }
        return map;
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




    public String generateCron(String time, String num, String gap){
        //second
        String cron="0 ";
        //minute & hour
        cron+=time.split(":")[1]+" "+time.split(":")[0]+" ";
        //day of month
        if(gap.equals("DAY")){
            cron+="0/"+num+" * * *";
        }
        else if(gap.equals("WEEK")){
            //转化为天数
            int temp=Integer.parseInt(num)*7;
            cron+="0/"+String.valueOf(temp)+" * * *";
        }
        else if(gap.equals("MONTH")){
            cron+="* "+"1/"+num+" * *";
        }
        //出现错误，设为不执行
        else{
            cron="* * * * * * 2099";
        }
        return cron;
    }



    public Integer returnJobId(String s){
        Map<String,Integer> map = new LinkedHashMap<>();
        map.put("baiduBaike",1);
        map.put("baidubaike",1);
        map.put("symptomZy",2);
        map.put("symptomXy",3);
        map.put("diseaseZy",4);
        map.put("diseaseXy",5);
        map.put("patentYaobiao",6);
        map.put("patentBaodian",7);
        return map.get(s);
    }
}













//for(int i=0;i<chlist.split("/").length;i++){
//            if(chlist.split("/")[i].equals("病症证型")|chlist.split("/")[i].equals("西医病名")){
//                List<data_all> list=dataAllRepository.findByName(content);
//                if(list.size()>0){
//                    //找到该词，进行更新
//                    for(data_all temp : list){
//                        int status=0;
//                        //百度百科
//                        if(temp.getOrigin_baike()==1){
//                            status=10;
//                        }
//                        else if(temp.getOrigin_baike()==0){
//                            status=20;
//                        }
//                        Date dt=new Date();
//                        SearchContent searchContent=new SearchContent(content,returnJobId("baikeZy"),status,"",dt,chlist.split("/")[i]);
//                        searchContentRepository.saveAndFlush(searchContent);
//                        idList+=String.valueOf(searchContent.getId())+"/";
//
//                        try{
//                            String res= assiginTask(returnJobId("baikeZy"),searchContent.getId());
//                            log.info(res);
//                        }catch (SchedulerException e){
//                            System.out.println("Schedule error!");
//                        }
//                        //中医症状
//                        if(temp.getOrigin_symptom_zy()==1){
//                            status=10;
//                            dt=new Date();
//                            String url=symptomZyRepository.getByInfo_mc(content).get(0).getOrigin_url();
//                            searchContent=new SearchContent(content,returnJobId("symptomZy"),status,url,dt,chlist.split("/")[i]);
//                            searchContentRepository.saveAndFlush(searchContent);
//                            idList+=String.valueOf(searchContent.getId())+"/";
//
//                            try{assiginTask(returnJobId("symptomZy"),searchContent.getId());}catch (SchedulerException e){
//                                System.out.println("Schedule error!");
//                            }
//                        }
//                        else if(temp.getOrigin_symptom_zy()==0){
//                            status=20;
//                        }
//                        //西医症状
//                        if(temp.getOrigin_symptom_xy()==1){
//                            status=10;
//                            dt=new Date();;
//                            String url=symptomXyRepository.getByInfo_mc(content).get(0).getOrigin_url();
//                            searchContent=new SearchContent(content,returnJobId("symptomXy"),status,url,dt,chlist.split("/")[i]);
//                            searchContentRepository.saveAndFlush(searchContent);
//                            idList+=String.valueOf(searchContent.getId())+"/";
//
//                            try{assiginTask(returnJobId("symptomXy"),searchContent.getId());}catch (SchedulerException e){
//                                System.out.println("Schedule error!");
//                            }
//                        }
//                        else if(temp.getOrigin_symptom_xy()==0){
//                            status=20;
//                        }
//                        //中医疾病
//                        if(temp.getOrigin_disease_zy()==1){
//                            status=10;
//                            dt=new Date();
//                            String url=diseaseZyRepository.getByInfo_mc(content).get(0).getOrigin_url();
//                            searchContent=new SearchContent(content,returnJobId("diseaseZy"),status,url,dt,chlist.split("/")[i]);
//                            searchContentRepository.saveAndFlush(searchContent);
//                            idList+=String.valueOf(searchContent.getId())+"/";
//
//                            try{assiginTask(returnJobId("diseaseZy"),searchContent.getId());}catch (SchedulerException e){
//                                System.out.println("Schedule error!");
//                            }
//                        }
//                        else if(temp.getOrigin_disease_zy()==0){
//                            status=20;
//                        }
//                        //西医疾病
//                        if(temp.getOrigin_disease_xy()==1){
//                            status=10;
//                            dt=new Date();
//                            String url=diseaseXyRepository.getByInfo_mc(content).get(0).getOrigin_url();
//                            searchContent=new SearchContent(content,returnJobId("diseaseXy"),status,url,dt,chlist.split("/")[i]);
//                            searchContentRepository.saveAndFlush(searchContent);
//                            idList+=String.valueOf(searchContent.getId())+"/";
//
//
//                            try{assiginTask(returnJobId("diseaseXy"),searchContent.getId());}catch (SchedulerException e){
//                                System.out.println("Schedule error!");
//                            }
//                        }
//                        else if(temp.getOrigin_disease_xy()==0){
//                            status=20;
//                        }
//                    }
//                }
//                else{
//                    map.put("code","400");
//                    return map;
////                    int status=20;
////                    //百度百科
////                    LocalDateTime dt=new Date();
////                    SearchContent searchContent=new SearchContent(content,returnJobId("baikeZy"),status,"",dt);
////                    searchContentRepository.saveAndFlush(searchContent);
////                    try{
////                        String res= assiginTask(returnJobId("baikeZy"),searchContent.getId());
////                        log.info(res);
////                    }catch (SchedulerException e){
////                        System.out.println("Schedule error!");
////                    }
//                }
//            }
//            else if(chlist.split("/")[i].equals("中药方剂")){
//            }
//            else if(chlist.split("/")[i].equals("中草药库")){
//            }
//            else{
//                map.put("error","list wrong!");
//                map.put("code","401");
//                return map;
//            }
//        }