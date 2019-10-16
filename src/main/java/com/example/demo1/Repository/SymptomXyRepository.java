package com.example.demo1.Repository;

import com.example.demo1.model.disease_and_symptom.symptom_xy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

public interface SymptomXyRepository extends JpaRepository<symptom_xy,Integer>, PagingAndSortingRepository<symptom_xy,Integer> {

    @Transactional
    @Query(value = "select count(*) from data_symptom_xy_zgyyxxcxpt",nativeQuery = true)
    int getNum();

}
