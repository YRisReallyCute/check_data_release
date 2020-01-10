package com.example.demo1.repository.index;

import com.example.demo1.model.index.DiseaseZyIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface DiseaseZyIndexRepository extends ElasticsearchRepository<DiseaseZyIndex,Long> {
}
