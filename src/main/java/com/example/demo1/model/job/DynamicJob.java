package com.example.demo1.model.job;

import com.example.demo1.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@DisallowConcurrentExecution
@Component
@Slf4j
public class DynamicJob implements Job {
    private int param_id;

    public void setParam_id(){
        this.param_id=param_id;
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();

        String parameter = map.getString("parameter");
        String vmParam = map.getString("vmParam");
        String runPath = map.getString("runPath");

        //日志
        log.info("Running Job name : {}",map.getString("name"));
        log.info("Running Job description : {}", map.getString("jobDescription"));
        log.info("Running Job group: {} ", map.getString("group"));
        log.info("Running Job path : {} ", runPath);
        log.info(String.format("Running Job cron : %s", map.getString("cronExpression")));
        log.info("Running Job parameter : {} ", parameter);
        log.info("Running Job vmParam : {} ", vmParam);
        long startTime = System.currentTimeMillis();
        log.info("start time:{} ", startTime);

//        if(!StringUtils.isEmpty(runPath)){
//            Runtime run = Runtime.getRuntime();
//            String cmdStr = "scrapy crawl getData -a id=32";
//            try{
//                Process process = run.exec(cmdStr);
//                InputStream in = process.getInputStream();
//                InputStreamReader reader = new InputStreamReader(in);
//                BufferedReader br = new BufferedReader(reader);
//                StringBuffer sb=new StringBuffer();
//                String message;
//                while((message=br.readLine())!=null){
//                    sb.append(message);
//                }
//                System.out.println(sb);
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }

        //执行体
        if(!StringUtils.isEmpty(runPath)){
            File pyFile = new File(runPath);
            if(pyFile.exists()){
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.directory(pyFile.getParentFile());
                List<String> commands = new ArrayList<>();
                String iparam="id="+parameter;
                commands= Arrays.asList("scrapy","crawl","getData","-a",iparam);
//                String com = "scrapy crawl getData";
                processBuilder.command(commands);
                processBuilder.redirectErrorStream(true);
                processBuilder.inheritIO();

                log.info("Running job details as follows :");
                log.info("Running job commands :{} ",StringUtil.getListString(commands));

                try{
                    Process process = processBuilder.start();
                    if(process.isAlive()){
                        System.out.println("*************alive***************");
                    }
                    logProcess(process.getInputStream(),process.getErrorStream());
                }catch (IOException e){
                    throw new JobExecutionException(e);
                }
            }else throw new JobExecutionException("Python file not found >>  " + runPath);
        }
        long endTime = System.currentTimeMillis();
        log.info(">>>>>>>>>>>>> Running Job has been completed , cost time : {}ms\n ", (endTime - startTime));
    }

    //记录Job执行内容
    private void logProcess(InputStream inputStream, InputStream errorStream) throws IOException {
        String inputLine;
        String errorLine;
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
        while (Objects.nonNull(inputLine = inputReader.readLine())) log.info(inputLine);
        while (Objects.nonNull(errorLine = errorReader.readLine())) log.error(errorLine);
    }
}
