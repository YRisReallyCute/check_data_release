package com.example.demo1.Repository.config;

import com.example.demo1.model.config.conf_online_crawler_settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ConfOnlineCrawlerSettingsRepository extends JpaRepository<conf_online_crawler_settings,Integer>, CrudRepository<conf_online_crawler_settings,Integer> {

    @Query(value = "select * from data_online_crawler_setting where type='symptom_zy'",nativeQuery = true)
    @Transactional
    List<conf_online_crawler_settings> findSymptomZy();

}
