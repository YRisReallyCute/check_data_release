package com.example.demo1.repository.patent;

import com.example.demo1.model.patent.yaobw_drug_patent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface YaobwDrugPatentRepository extends JpaRepository<yaobw_drug_patent, Integer>, CrudRepository<yaobw_drug_patent, Integer> {


}
