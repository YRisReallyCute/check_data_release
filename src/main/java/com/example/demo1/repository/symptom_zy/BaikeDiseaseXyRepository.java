package com.example.demo1.repository.symptom_zy;

import com.example.demo1.model.disease_and_symptom.baike_disease_xy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaikeDiseaseXyRepository extends JpaRepository<baike_disease_xy,Integer>, PagingAndSortingRepository<baike_disease_xy,Integer> {
}
