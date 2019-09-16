package com.example.demo1.Repository;

import com.example.demo1.model.disease_and_symptom.baike_disease_zy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaikeDiseaseZyRepository extends JpaRepository<baike_disease_zy,Integer> , PagingAndSortingRepository<baike_disease_zy,Integer> {
}
