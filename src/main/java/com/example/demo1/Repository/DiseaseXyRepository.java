package com.example.demo1.Repository;

import com.example.demo1.model.disease_and_symptom.disease_xy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DiseaseXyRepository extends JpaRepository<disease_xy,Integer>, PagingAndSortingRepository<disease_xy,Integer> {
}
