package com.example.demo1.repository.config;

import com.example.demo1.model.config.ConfPatentTitle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ConfPatentTitleRepository extends JpaRepository<ConfPatentTitle,Integer>, PagingAndSortingRepository<ConfPatentTitle,Integer>, CrudRepository<ConfPatentTitle,Integer> {

    @Query(value = "select name from data_conf_patent_zyybd_title2col group by name",nativeQuery = true)
    List<String> getCols();

    void deleteById(Integer id);

    Page<ConfPatentTitle> findByNameLike(String name, Pageable pageable);
}
