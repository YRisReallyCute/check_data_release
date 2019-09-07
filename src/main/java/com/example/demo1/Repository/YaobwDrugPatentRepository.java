package com.example.demo1.Repository;

import com.example.demo1.model.yaobw_drug_patent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface YaobwDrugPatentRepository extends JpaRepository<yaobw_drug_patent, Integer>, CrudRepository<yaobw_drug_patent, Integer> {
}
