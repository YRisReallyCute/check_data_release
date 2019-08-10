package com.example.demo1.Repository;

import com.example.demo1.model.baike_symptom_xy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaikeSymptomXyRepository extends JpaRepository<baike_symptom_xy,Integer>, PagingAndSortingRepository<baike_symptom_xy,Integer> {
}
