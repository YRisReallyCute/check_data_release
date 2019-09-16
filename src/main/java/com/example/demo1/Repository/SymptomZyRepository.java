package com.example.demo1.Repository;

import com.example.demo1.model.disease_and_symptom.symptom_zy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SymptomZyRepository extends JpaRepository<symptom_zy,Integer>, PagingAndSortingRepository<symptom_zy,Integer> {
}
