package com.example.demo1.model.config;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "data_conf_symptom_zy_baidubaike_title")
public class conf_symptom_zy_baidubaike_title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 255)
    private String col;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private LocalDateTime update_time;

    public conf_symptom_zy_baidubaike_title(){

    }

    public conf_symptom_zy_baidubaike_title(String title,String col,String name){
        this.setTitle(title);
        this.setCol(col);
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
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
