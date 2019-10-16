package com.example.demo1.model.config;

import javax.persistence.*;

@Entity
@Table(name = "data_online_crawler_setting")
public class conf_online_crawler_settings {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String type;

    @Column
    private String origin;

    @Column
    private String isAuto;

    @Column
    private Integer totalNum;

    @Column
    private String log;

    conf_online_crawler_settings(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(String isAuto) {
        this.isAuto = isAuto;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
