package com.example.demo1.repository.config;

import com.example.demo1.model.config.conf_symptom_zy_baidubaike_title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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

    @Query(value = "select name from data_conf_symptom_zy_baidubaike_title group by name",nativeQuery = true)
    List<String> getCols();

    Page<conf_symptom_zy_baidubaike_title> findByNameLike(String name, Pageable pageable);

    void deleteById(Integer id);
}
