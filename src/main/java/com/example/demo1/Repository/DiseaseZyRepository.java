package com.example.demo1.Repository;

import com.example.demo1.model.disease_zy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DiseaseZyRepository extends JpaRepository<disease_zy,Integer>, PagingAndSortingRepository<disease_zy,Integer> {
}
