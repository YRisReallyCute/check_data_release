package com.example.demo1.repository.patent;

import com.example.demo1.model.patent.zyybdApp_patent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ZyybdAppDrugRepository extends JpaRepository<zyybdApp_patent,Integer>, CrudRepository<zyybdApp_patent,Integer> {

}
