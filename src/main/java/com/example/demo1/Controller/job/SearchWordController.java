package com.example.demo1.Controller.job;

import com.example.demo1.model.patent.data_all_drug_patent;
import com.example.demo1.repository.job.JobEntityRepository;
import com.example.demo1.repository.job.SearchContentRepository;
import com.example.demo1.repository.patent.DataAllDrugPatentRepository;
import com.example.demo1.repository.patent.PatentBaidubaikeRepository;
import com.example.demo1.repository.patent.YaobwDrugPatentRepository;
import com.example.demo1.repository.patent.ZyybdAppDrugRepository;
import com.example.demo1.repository.symptom_zy.*;
import com.example.demo1.model.disease_and_symptom.data_all_symptom_disease_zxy;
import com.example.demo1.model.disease_and_symptom.symptom_zy;
import com.example.demo1.model.job.SearchContent;
import com.example.demo1.model.jobEntity.JobEntity;
import com.example.demo1.service.DynamicJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
    @Autowired
    private DataAllDrugPatentRepository dataAllDrugPatentRepository;
    @Autowired
    private YaobwDrugPatentRepository yaobwDrugPatentRepository;
    @Autowired
    private ZyybdAppDrugRepository zyybdAppDrugRepository;
    @Autowired
    private PatentBaidubaikeRepository patentBaidubaikeRepository;

    public int DZY=1,DXY=2,SZY=3,SXY=4;

    public int dirtyStatus=4;

    private String diseaseUrl,patentUrl,herbalUrl;
    private String baseBaikeUrl="https://baike.baidu.com/item/";


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

    @GetMapping("/conf/refresh_task")
    private Map<String,Object> refreshTask(@RequestParam String word,@RequestParam int jobId,@RequestParam int status,@RequestParam int originId,@RequestParam int type,@RequestParam(required = true,defaultValue = "null") String refreshUrl){
        word=word.trim();
        Map<String,Object> map=new LinkedHashMap<>();
        Date dt=new Date();
        try {
            if(jobId==1){
                //百度百科
                SearchContent searchContent = new SearchContent(word, jobId, status, "",dt,type2ch(type));
                searchContentRepository.saveAndFlush(searchContent);

                if(dataAllSymptomDiseaseZxyRepository.findByNameIncludeDirty(word).size()==0){
                    //没有这条数据，要根据当前状态提前创建
                    data_all_symptom_disease_zxy da=new data_all_symptom_disease_zxy(word,type,4);
                    dataAllSymptomDiseaseZxyRepository.saveAndFlush(da);
                }

                String res= assiginBaiduTask(jobId,searchContent.getId(),refreshUrl);
                log.info(res);

                map.put("id",searchContent.getId());
                map.put("code","200");
                return map;
            }
            else if(jobId>1 && jobId<6){
                String url="";
                if(jobId==2){
                    //中医症状
                    url=symptomZyRepository.findById(originId).getOrigin_url();
                }
                else if(jobId==3){
                    url=symptomXyRepository.findById(originId).getOrigin_url();
                }
                else if(jobId==4){
                    url=diseaseZyRepository.findById(originId).getOrigin_url();
                }
                else{
                    url=diseaseXyRepository.findById(originId).getOrigin_url();
                }

                SearchContent searchContent = new SearchContent(word, jobId, status,url,dt,type2ch(type));
                searchContentRepository.saveAndFlush(searchContent);

                String res= assiginTask(jobId,searchContent.getId());
                log.info(res);
                map.put("id",searchContent.getId());
                map.put("code","200");
                return map;
            }
            else if(jobId==7 | jobId==6){
                String url="";
                if(jobId==7){
                    //中医药宝典
                    if(zyybdAppDrugRepository.findById(originId).isPresent()){
                        url=zyybdAppDrugRepository.findById(originId).get().getOrigin_url();
                    }
                }
                else{
                    //药标网
                    if(yaobwDrugPatentRepository.findById(originId).isPresent()){
                        url=yaobwDrugPatentRepository.findById(originId).get().getOrigin_url();
                    }
                }
                SearchContent searchContent = new SearchContent(word, jobId, status,url,dt,type2ch(type));
                searchContentRepository.saveAndFlush(searchContent);

                String res= assiginTask(jobId,searchContent.getId());
                log.info(res);
                map.put("id",searchContent.getId());
                map.put("code","200");
                return map;
            }
        }
        catch (JobExecutionException e){
            System.out.println("JobExecutionException");
        }
        catch (SchedulerException e){
            System.out.println("SchedulerException");
        }
        return map;
    }

    class MyHandler implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("doing handler invoke...");
            return null;
        }
    }

    /**
     *
     * @param content，搜索词
     * @param chlist，搜索范围
     * @return
     */
    @GetMapping("/conf/search_content")
    private Map<String,Object> searchSaveContent(@RequestParam String content, @RequestParam String chlist){
        content=content.trim();
        Map<String,Object> map = new LinkedHashMap<>();
        String idList="";
        int status=0;
        Date dt;
        SearchContent searchContent;
        String code="200";
        String word="word",jobId="jobId",searchStatus="status",type="type",item="item",originId="originId";

        List<Map<String,Object>> list=new LinkedList<>();
        for(int i=0;i<chlist.split("/").length;i++){

            String cur_chlist=chlist.split("/")[i];
            if((cur_chlist.equals("中医病")) | (cur_chlist.equals("西医病")) | (cur_chlist.equals("中医证型")) | (cur_chlist.equals("症状"))) {
                String jobIdStr = "";
                List<data_all_symptom_disease_zxy> list1 = new LinkedList<>();
                jobIdStr = typeCH2En(chlist.split("/")[i]);
                int new_content=1;//是否需要为当前搜索内容精准创建词条

                try {
                    Class<?> clazz = Class.forName("com.example.demo1.repository.symptom_zy.DataAllSymptomDiseaseZxyRepository");
                    Method findLikeName = clazz.getDeclaredMethod(type2findby(chlist.split("/")[i]), String.class);
                    findLikeName.setAccessible(true);
                    list1 = (List<data_all_symptom_disease_zxy>) findLikeName.invoke(dataAllSymptomDiseaseZxyRepository, content);

                    Class<?> clazz1 = Class.forName("com.example.demo1.model.disease_and_symptom.data_all_symptom_disease_zxy");
                    Method getOrigin = clazz1.getDeclaredMethod(type2getOriginMethod(chlist.split("/")[i]));
                    getOrigin.setAccessible(true);
                    Method getOriginId = clazz1.getDeclaredMethod(type2getOriginIdMethod(chlist.split("/")[i]));
                    getOriginId.setAccessible(true);

                    if (list1.size() > 0) {
                        for (data_all_symptom_disease_zxy each_da : list1) {
                            String current_word = each_da.getInfo_mc();
                            if(current_word.equals(content)){
                                new_content=0;
                            }
                            int current_type = each_da.getType();
                            int current_origin = (int) getOrigin.invoke(each_da);

                            //中西医来源
                            if (current_origin == 1) {
                                //已经找到
                                status = 10;
                                int current_origin_id = (int) getOriginId.invoke(each_da);
                                Map<String, Object> map1 = new LinkedHashMap<>();
                                map1.put(word, current_word);
                                map1.put(jobId, returnJobId(jobIdStr));
                                map1.put(type, current_type);
                                map1.put(searchStatus, status);
                                map1.put(item, each_da);
                                map1.put(originId, current_origin_id);
                                list.add(map1);
                            } else {
                                //没有找到，中西医的没有找到，不能更新也不能插入
                                status = 30;
                                Map<String, Object> map1 = new LinkedHashMap<>();
                                map1.put(word, current_word);
                                map1.put(jobId, returnJobId(jobIdStr));
                                map1.put(type, current_type);
                                map1.put(searchStatus, status);
                                map1.put(item, null);
                                map1.put(originId, 0);
                                list.add(map1);
                            }

                            //百度百科来源
                            if (each_da.getOrigin_baike() == 1) {
                                //已经找到
                                status = 10;
                                //读取url
                                diseaseUrl=userRepository.findById(each_da.getBaike_id()).orElse(null).getOrigin_url();
                                Map<String, Object> map1 = new LinkedHashMap<>();
                                map1.put(word, current_word);
                                map1.put(jobId, returnJobId("baidubaike"));
                                map1.put(type, current_type);
                                map1.put(searchStatus, status);
                                map1.put(item, each_da);
                                map1.put(originId, 0);
                                map1.put("url",diseaseUrl);
                                list.add(map1);
                            } else {
                                //没有找到
                                status = 20;
                                diseaseUrl=baseBaikeUrl+current_word;
                                Map<String, Object> map1 = new LinkedHashMap<>();
                                map1.put(word, current_word);
                                map1.put(jobId, returnJobId("baidubaike"));
                                map1.put(type, current_type);
                                map1.put(searchStatus, status);
                                map1.put(item, null);
                                map1.put(originId, 0);
                                map1.put("url",diseaseUrl);
                                list.add(map1);
                            }
                        }
                    }
                    else {
                        //没有找到，中西医的没有找到，不能更新也不能插入
                        status = 30;
                        Map<String, Object> map1 = new LinkedHashMap<>();
                        map1.put(word, content);
                        map1.put(jobId, returnJobId(jobIdStr));
                        map1.put(type, typeChinese2num(cur_chlist));
                        map1.put(searchStatus, status);
                        map1.put(item, null);
                        map1.put(originId, 0);
                        list.add(map1);

                        //百度百科
                        new_content=0;
                        status = 20;
                        diseaseUrl=baseBaikeUrl+content;
                        Map<String, Object> map2 = new LinkedHashMap<>();
                        map2.put(word, content);
                        map2.put(jobId, returnJobId("baidubaike"));
                        map2.put(type, typeChinese2num(cur_chlist));
                        map2.put(searchStatus, status);
                        map2.put(item, null);
                        map2.put(originId, 0);
                        map2.put("url",diseaseUrl);
                        list.add(map2);
                    }

                    if(new_content==1){
                        //百度百科
                        status = 20;
                        diseaseUrl=baseBaikeUrl+content;
                        Map<String, Object> map2 = new LinkedHashMap<>();
                        map2.put(word, content);
                        map2.put(jobId, returnJobId("baidubaike"));
                        map2.put(type, typeChinese2num(cur_chlist));
                        map2.put(searchStatus, status);
                        map2.put(item, null);
                        map2.put(originId, 0);
                        map2.put("url",diseaseUrl);
                        list.add(map2);
                    }

                } catch (Exception e1) {
                    System.out.println("ClassNotFoundException");
                }
            }
            else if(cur_chlist.equals("中成药")){
                int patent_type=5;
                int zyybd_job_id=7;
                int yaobw_job_id=6;
                int patent_baike_id=10;
                int new_content=1;//是否需要为当前搜索内容精准创建词条

                List<data_all_drug_patent> list1=dataAllDrugPatentRepository.findByName(content);
                if(list1.size()>0){
                    map.put("中成药","200");
                    String cur_word="";
                    int cur_id=0;
                    for(data_all_drug_patent da :list1) {
                        cur_word=da.getInfo_ym();
                        if(cur_word.equals(content)){
                            new_content=0;
                        }
                        //中医药宝典来源
                        if (da.getOrigin_zyybd()==1){
                            status=10;
                            cur_id=da.getZyybd_id();

                            Map<String,Object> map1=new LinkedHashMap<>();
                            map1.put(word, cur_word);
                            map1.put(jobId, zyybd_job_id);
                            map1.put(type, patent_type);
                            map1.put(searchStatus, status);
                            map1.put(item, da);
                            map1.put(originId, cur_id);
                            list.add(map1);
                        }
                        else{
                            status=30;
                            Map<String,Object> map1=new LinkedHashMap<>();
                            map1.put(word, cur_word);
                            map1.put(jobId, zyybd_job_id);
                            map1.put(type, patent_type);
                            map1.put(searchStatus, status);
                            map1.put(item, null);
                            map1.put(originId, 0);
                            list.add(map1);
                        }

                        //药标网来源
                        if(da.getOrigin_yaobw()==1){
                            status=10;
                            cur_id=da.getYaobw_id();

                            Map<String,Object> map1=new LinkedHashMap<>();
                            map1.put(word, cur_word);
                            map1.put(jobId, yaobw_job_id);
                            map1.put(type, patent_type);
                            map1.put(searchStatus, status);
                            map1.put(item, da);
                            map1.put(originId, cur_id);
                            list.add(map1);
                        }
                        else{
                            status=30;

                            Map<String,Object> map1=new LinkedHashMap<>();
                            map1.put(word, cur_word);
                            map1.put(jobId, yaobw_job_id);
                            map1.put(type, patent_type);
                            map1.put(searchStatus, status);
                            map1.put(item, null);
                            map1.put(originId, 0);
                            list.add(map1);
                        }

                        //百度百科来源
                        if (da.getOrigin_baike() == 1) {
                            //已经找到
                            status = 10;
                            cur_id=da.getBaike_id();
                            patentUrl= patentBaidubaikeRepository.findById(da.getBaike_id()).orElse(null).getOrigin_url();
                            Map<String, Object> map1 = new LinkedHashMap<>();
                            map1.put(word, cur_word);
                            map1.put(jobId, patent_baike_id);
                            map1.put(type, patent_type);
                            map1.put(searchStatus, status);
                            map1.put(item, da);
                            map1.put(originId, cur_id);
                            map1.put("url",patentUrl);
                            list.add(map1);
                        } else {
                            //没有找到
                            status = 20;
                            patentUrl=baseBaikeUrl+cur_word;
                            Map<String, Object> map1 = new LinkedHashMap<>();
                            map1.put(word, cur_word);
                            map1.put(jobId, patent_baike_id);
                            map1.put(type, patent_type);
                            map1.put(searchStatus, status);
                            map1.put(item, null);
                            map1.put(originId, 0);
                            map1.put("url",diseaseUrl);
                            list.add(map1);
                        }
                    }
                }
                else{
                    //中医药宝典
                    status=30;
                    Map<String,Object> map1=new LinkedHashMap<>();
                    map1.put(word, content);
                    map1.put(jobId, zyybd_job_id);
                    map1.put(type, patent_type);
                    map1.put(searchStatus, status);
                    map1.put(item, null);
                    map1.put(originId, 0);
                    list.add(map1);

                    //药标网
                    status=30;
                    Map<String,Object> map2=new LinkedHashMap<>();
                    map2.put(word, content);
                    map2.put(jobId, yaobw_job_id);
                    map2.put(type, patent_type);
                    map2.put(searchStatus, status);
                    map2.put(item, null);
                    map2.put(originId, 0);
                    list.add(map2);

                    //百度百科
                    new_content=0;
                    status = 20;
                    patentUrl=baseBaikeUrl+content;
                    Map<String, Object> map3 = new LinkedHashMap<>();
                    map3.put(word, content);
                    map3.put(jobId, patent_baike_id);
                    map3.put(type, patent_type);
                    map3.put(searchStatus, status);
                    map3.put(item, null);
                    map3.put(originId, 0);
                    map3.put("url",diseaseUrl);
                    list.add(map3);
                }

                if(new_content==1){
                    //百度百科
                    status = 20;
                    patentUrl=baseBaikeUrl+content;
                    Map<String, Object> map3 = new LinkedHashMap<>();
                    map3.put(word, content);
                    map3.put(jobId, returnJobId("patentbaike"));
                    map3.put(type,  typeChinese2num(cur_chlist));
                    map3.put(searchStatus, status);
                    map3.put(item, null);
                    map3.put(originId, 0);
                    map3.put("url",diseaseUrl);
                    list.add(map3);
                }
            }
            else if(cur_chlist.equals("中草药")){

            }
            else{}
        }

        map.put("result",list);
        return map;
    }

    @GetMapping("/conf/add_search_task")
    private Map<String,Object>addTask(@RequestParam String content,@RequestParam String chlist){
        content=content.trim();
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

    /**
     *
     * @param id
     * @param task_id
     * @return
     * @throws SchedulerException
     */
    private String assiginTask(int id,int task_id) throws SchedulerException {
        String result;
        JobEntity entity = jobService.getJobEntityById(id);
        //设置上下文参数
        entity=jobService.updateParam(task_id,entity);
        if(Objects.isNull(entity)) return "error: id is not exits ";
        synchronized (log){
            JobKey jobKey = jobService.getJobKey(entity,task_id);
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

    /**
     *
     * @param id
     * @param task_id
     * @return
     * @throws SchedulerException
     */
    private String assiginBaiduTask(int id,int task_id,String url) throws SchedulerException {
        String result;
        JobEntity entity = jobService.getJobEntityById(id);
        //设置上下文参数
        entity=jobService.updateParamBaidu(task_id,url,entity);
        if(Objects.isNull(entity)) return "error: id is not exits ";
        synchronized (log){
            JobKey jobKey = jobService.getJobKey(entity,task_id);
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
        map.put("patentbaike",10);
        map.put("patentYaobiao",6);
        map.put("patentBaodian",7);
        map.put("",0);
        return map.get(s);
    }

    public String type2ch(int type){
        Map<Integer,String> map = new LinkedHashMap<>();
        map.put(2,"西医病");
        map.put(3,"中医证型");
        map.put(4,"症状");
        map.put(1,"中医病");
        map.put(5,"中成药");
        map.put(6,"中成药");
        return map.get(type);
    }

    public String typeCH2En(String s){
        Map<String,String> map = new LinkedHashMap<>();
        map.put("中医证型","symptomZy");
        map.put("症状","symptomXy");
        map.put("中医病","diseaseZy");
        map.put("西医病","diseaseXy");
        return map.get(s);
    }

    public String type2findby(String s){
        Map<String,String> map = new LinkedHashMap<>();
        map.put("中医证型","findLikeNameSymptomZy");
        map.put("症状","findLikeNameSymptomXy");
        map.put("中医病","findLikeNameDiseaseZy");
        map.put("西医病","findLikeNameDiseaseXy");
        return map.get(s);
    }

    public String type2getOriginMethod(String s){
        Map<String,String> map = new LinkedHashMap<>();
        map.put("中医证型","getOrigin_symptom_zy");
        map.put("症状","getOrigin_symptom_xy");
        map.put("中医病","getOrigin_disease_zy");
        map.put("西医病","getOrigin_disease_xy");
        return map.get(s);
    }

    public String type2getOriginIdMethod(String s){
        Map<String,String> map = new LinkedHashMap<>();
        map.put("中医证型","getSymptom_zy_id");
        map.put("症状","getSymptom_xy_id");
        map.put("中医病","getDisease_zy_id");
        map.put("西医病","getDisease_xy_id");
        return map.get(s);
    }

    public String type2repositoryName(String s){
        Map<String,String> map = new LinkedHashMap<>();
        map.put("中医证型","SymptomZyRepository");
        map.put("症状","SymptomXyRepository");
        map.put("中医病","DiseaseZyRepository");
        map.put("西医病","DiseaseXyRepository");
        return map.get(s);
    }

//    public String type2repositoryObject(String s){
//        Map<String,String> map = new LinkedHashMap<>();
//        map.put("中医证型","symptomZyRepository");
//        map.put("症状","symptomXyRepository");
//        map.put("中医病","diseaseZyRepository");
//        map.put("西医病","diseaseXyRepository");
//        return map.get(s);
//    }

    public String jobId2type(int id){
        Map<Integer,String> map = new LinkedHashMap<>();
        map.put(2,"中医证型");
        map.put(3,"症状");
        map.put(4,"中医病");
        map.put(5,"西医病");
        return map.get(id);
    }

    public int typeChinese2num(String s){
        Map<String,Integer> map = new LinkedHashMap<>();
        map.put("中医证型",3);
        map.put("症状",4);
        map.put("中医病",1);
        map.put("西医病",2);
        map.put("中成药",5);
        return map.get(s);
    }

    public JpaRepository<?,Integer> type2repositoryObject(String s){
        Map<String,JpaRepository<?,Integer>> map = new LinkedHashMap<>();
        map.put("中医证型",symptomZyRepository);
        map.put("症状",symptomXyRepository);
        map.put("中医病",diseaseZyRepository);
        map.put("西医病",diseaseXyRepository);
        return map.get(s);
    }
}







//    String tmp = "com.example.demo1.repository.symptom_zy." + type2repositoryName(jobId2type(jobId));
//    Class<?> clazzRepository = Class.forName(tmp);
//    Method findById = clazzRepository.getDeclaredMethod("findById", int.class);
//                findById.setAccessible(true);
//
//                        Map<String,String> map1 = new LinkedHashMap<>();
//        map1.put("中医证型","symptom_zy");
//        map1.put("症状","symptom_xy");
//        map1.put("中医病","disease_zy");
//        map1.put("西医病","disease_xy");
//        String tmp1="com.example.demo1.model.disease_and_symptom."+map1.get(jobId2type(jobId));
//        Class<?> clazz = Class.forName(tmp1);
//        Method getOriginUrl=clazz.getDeclaredMethod("getOrigin_url");
//        getOriginUrl.setAccessible(true);
//
//        MyHandler myHandler=new MyHandler();
//        Proxy.newProxyInstance(SearchWordController.class.getClassLoader(),new Class[]{clazzRepository},myHandler);
//
//        System.out.println(findById.invoke(Proxy.newProxyInstance(SearchWordController.class.getClassLoader(),new Class[]{clazzRepository},myHandler), originId));
//        String url = (String) getOriginUrl.invoke(findById.invoke(Proxy.newProxyInstance(SearchWordController.class.getClassLoader(),new Class[]{clazzRepository},myHandler), originId));
//
//


