package com.example.demo1.repository.symptom_zy;

import com.example.demo1.model.disease_and_symptom.symptom_zy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface SymptomZyRepository extends JpaRepository<symptom_zy,Integer>, PagingAndSortingRepository<symptom_zy,Integer> {

    @Transactional
    @Query(value = "select count(*) from data_symptom_zy_zgyyxxcxpt",nativeQuery = true)
    int getNum();

    @Query(value = "select * from data_symptom_zy_zgyyxxcxpt where info_mc =?1",nativeQuery = true)
    List<symptom_zy> getByInfo_mc(String info_mc);

    symptom_zy findById(int id);

}
