package com.example.demo1.Repository.symptom_zy;

import com.example.demo1.model.disease_and_symptom.symptom_xy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface SymptomXyRepository extends JpaRepository<symptom_xy,Integer>, PagingAndSortingRepository<symptom_xy,Integer> {

    @Transactional
    @Query(value = "select count(*) from data_symptom_xy_zgyyxxcxpt",nativeQuery = true)
    int getNum();

    @Query(value = "select * from data_symptom_xy_zgyyxxcxpt where info_mc =?1",nativeQuery = true)
    List<symptom_xy> getByInfo_mc(String info_mc);

}
