package com.example.demo1.Repository;

import com.example.demo1.model.SearchContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface SearchContentRepository extends JpaRepository<SearchContent,Integer>, CrudRepository<SearchContent,Integer> {
    SearchContent findBySearchContent(String searchContent);

    @Modifying@Transactional
    @Query(value = "update data_search_word_record set status=2 where search_content = ?1",nativeQuery = true)
    void updateStatus(String content);
}
