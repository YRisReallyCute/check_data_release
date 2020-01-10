package com.example.demo1.model.config;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "data_conf_herbal_title2col")
@Data
public class ConfHerbalTitle {
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

    public ConfHerbalTitle(){

    }
    public ConfHerbalTitle(String title,String col,String name){
        this.setTitle(title);
        this.setCol(col);
        this.setName(name);
    }
}
