package com.example.demo1.model.config;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "conf_symptom_zy_baidubaike_title")
public class conf_symptom_zy_baidubaike_title {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 255)
    private String col;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = true)
    private LocalDateTime update_time;

    public conf_symptom_zy_baidubaike_title(String title,String col){
        this.setTitle(title);
        this.setCol(col);
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
