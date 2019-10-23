package com.example.demo1.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "data_job_execute_log")
@Data
@Accessors(chain = true)
public class JobExecuteLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String jobName;
    private String log;
    private String createTime;
}
