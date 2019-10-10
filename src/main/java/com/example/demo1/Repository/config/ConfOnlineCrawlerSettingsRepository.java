package com.example.demo1.Repository.config;

import com.example.demo1.model.config.conf_online_crawler_settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ConfOnlineCrawlerSettingsRepository extends JpaRepository<conf_online_crawler_settings,Integer>, CrudRepository<conf_online_crawler_settings,Integer> {


}
