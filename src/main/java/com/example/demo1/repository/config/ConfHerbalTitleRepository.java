package com.example.demo1.repository.config;

import com.example.demo1.model.config.ConfHerbalTitle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConfHerbalTitleRepository extends JpaRepository<ConfHerbalTitle,Integer>, PagingAndSortingRepository<ConfHerbalTitle,Integer>, CrudRepository<ConfHerbalTitle,Integer> {

    @Query(value = "select name from data_conf_herbal_title2col group by name",nativeQuery = true)
    List<String> getCols();

    void deleteById(Integer id);

    Page<ConfHerbalTitle> findByNameLike(String name, Pageable pageable);
}
