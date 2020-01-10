package com.example.demo1.model.index;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "disease_xy",type = "doc",shards = 1,replicas = 0)
public class DiseaseXyIndex {
    @Id
    private Long id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String info_mc;

    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String info_lcbx;

    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String info_jbzd;
}
