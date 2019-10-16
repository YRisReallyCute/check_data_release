package com.example.demo1.model.config;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "data_conf_symptom_zy_baidubaike_url")
public class conf_symptom_zy_baidubaike_url {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 255)
    private String info_mc;

    @Column(nullable = false, length = 255)
    private String crawler_url;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = true)
    private LocalDateTime update_time;

    public conf_symptom_zy_baidubaike_url(){}

    public conf_symptom_zy_baidubaike_url(String name){
        this.info_mc=name;
        this.crawler_url="";
    }

    public conf_symptom_zy_baidubaike_url(conf_symptom_zy_baidubaike_url c){
       this.id=c.getId();
       this.info_mc=c.getInfo_mc();
       this.crawler_url=c.getCrawler_url();
       this.status=c.getStatus();
       this.update_time=c.getUpdate_time();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo_mc() {
        return info_mc;
    }

    public void setInfo_mc(String info_mc) {
        this.info_mc = info_mc;
    }

    public String getCrawler_url() {
        return crawler_url;
    }

    public void setCrawler_url(String crawler_url) {
        this.crawler_url = crawler_url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }
}
