package com.example.demo1.Repository.patent;

import com.example.demo1.model.patent.zyybdApp_patent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ZyybdAppDrugRepository extends JpaRepository<zyybdApp_patent,Integer>, CrudRepository<zyybdApp_patent,Integer> {

    @Transactional
    @Query(value = "select count(*) from zyybdApp_patent",nativeQuery = true)
    int getNum();
}
