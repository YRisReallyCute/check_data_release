package com.example.demo1.model.job;

import lombok.Data;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import javax.persistence.*;
import javax.print.DocFlavor;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "data_job_word_search_task")
@Data
@Accessors(chain = true)
public class SearchContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String word;
    private int job_id;
    private int status;
    private int dataAllId;
    private String originUrl;
    private Date startTime;
    private Date endTime;
    private String jobGroup;
    private String taskLog;

    public SearchContent(String content, int job_id, int status,String originUrl,Date dateTime,String jobGroup){
        this.word=content;
        this.job_id=job_id;
        this.status=status;
        this.startTime=dateTime;
        this.originUrl=originUrl;
        this.jobGroup=jobGroup;
    }

    public SearchContent(String content, int job_id, int status,String originUrl,Date dateTime,String jobGroup,int dataAllId){
        this.word=content;
        this.job_id=job_id;
        this.status=status;
        this.startTime=dateTime;
        this.originUrl=originUrl;
        this.jobGroup=jobGroup;
        this.dataAllId=dataAllId;
    }

    public SearchContent(){}
}
