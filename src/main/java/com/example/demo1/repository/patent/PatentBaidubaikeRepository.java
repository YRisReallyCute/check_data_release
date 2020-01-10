package com.example.demo1.repository.patent;

import com.example.demo1.model.patent.PatentBaidubaike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface PatentBaidubaikeRepository extends JpaRepository<PatentBaidubaike, Integer>, CrudRepository<PatentBaidubaike, Integer> {

}
