package com.example.demo1.Repository;

import com.example.demo1.model.disease_and_symptom.disease_zy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

public interface DiseaseZyRepository extends JpaRepository<disease_zy,Integer>, PagingAndSortingRepository<disease_zy,Integer> {

    @Transactional
    @Query(value = "select count(*) from data_disease_zy_zgyyxxcxpt",nativeQuery = true)
    int getNum();
}
