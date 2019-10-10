package com.example.demo1.Repository.config;

import com.example.demo1.model.config.conf_symptom_zy_baidubaike_title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Repository
public interface ConfSymZyBaikeTitleRepository extends JpaRepository<conf_symptom_zy_baidubaike_title,Integer> , PagingAndSortingRepository<conf_symptom_zy_baidubaike_title,Integer>,CrudRepository<conf_symptom_zy_baidubaike_title,Integer> {

    @Query(value = "INSERT IGNORE INTO data_conf_symptom_zy_baidubaike_title (title,col) VALUES (?1,?2)",nativeQuery = true)
    @Modifying
    @Transactional
    int addone(String title,String col);

    @Query(value = "delete from data_conf_symptom_zy_baidubaike_title where id=?1",nativeQuery = true)
    @Modifying
    @Transactional
    int deleteone(Integer id);
}
