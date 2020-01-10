package com.example.demo1.repository.index;

import com.example.demo1.model.index.PatentIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface PatentIndexRepository extends ElasticsearchRepository<PatentIndex,Long> {

}
