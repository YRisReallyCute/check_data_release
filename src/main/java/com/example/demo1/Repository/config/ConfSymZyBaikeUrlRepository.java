package com.example.demo1.Repository.config;

import com.example.demo1.model.config.conf_symptom_zy_baidubaike_url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ConfSymZyBaikeUrlRepository extends JpaRepository<conf_symptom_zy_baidubaike_url,Integer> , PagingAndSortingRepository<conf_symptom_zy_baidubaike_url,Integer>,CrudRepository<conf_symptom_zy_baidubaike_url,Integer> {

    @Query(value = "INSERT IGNORE INTO data_conf_symptom_zy_baidubaike_url (info_mc,crawler_url) VALUES (?1,?2)",nativeQuery = true)
    @Modifying
    @Transactional
    int addone(String name,String url);

    @Query(value = "update data_conf_symptom_zy_baidubaike_url set status = 1 where id = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    int update(Integer id);

}
