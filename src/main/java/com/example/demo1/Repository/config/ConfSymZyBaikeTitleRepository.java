package com.example.demo1.Repository.config;

import com.example.demo1.model.config.conf_symptom_zy_baidubaike_title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ConfSymZyBaikeTitleRepository extends JpaRepository<conf_symptom_zy_baidubaike_title,Integer> , CrudRepository<conf_symptom_zy_baidubaike_title,Integer> {
}
