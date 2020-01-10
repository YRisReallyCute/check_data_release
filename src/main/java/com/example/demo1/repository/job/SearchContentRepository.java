package com.example.demo1.repository.job;

import com.example.demo1.model.job.SearchContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SearchContentRepository extends JpaRepository<SearchContent,Integer>, CrudRepository<SearchContent,Integer>, PagingAndSortingRepository<SearchContent,Integer> {
//    SearchContent findBySearchContent(String searchContent);

//    @Modifying@Transactional
//    @Query(value = "update data_search_word_record set status=2 where search_content = ?1",nativeQuery = true)
//    void updateStatus(String content);
    @Query(value = "select *from data_job_word_search_task where start_time >= ?1",nativeQuery = true)
    List<SearchContent> currentWordStatus(String time);

    List<SearchContent> findByWord(String word);

    
}
