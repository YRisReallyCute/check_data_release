package com.example.demo1.Repository;

import com.example.demo1.model.disease_and_symptom.symptom_xy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SymptomXyRepository extends JpaRepository<symptom_xy,Integer>, PagingAndSortingRepository<symptom_xy,Integer> {
}
