package com.example.demo1.repository.symptom_zy;

import com.example.demo1.model.disease_and_symptom.human_symptom_zy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HumanSymptomZyRepository extends JpaRepository<human_symptom_zy,Integer>, PagingAndSortingRepository<human_symptom_zy,Integer> {
}
