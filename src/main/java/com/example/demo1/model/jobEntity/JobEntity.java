package com.example.demo1.model.jobEntity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "data_job_entity")
@Data
@Accessors(chain = true)
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String jobGroup;
    private String cron;
    private String parameter;
    private String description;
    private String vmParam;
    private String runPath;
    private String startTime;
    private String status;
    private int totalNum;
}
