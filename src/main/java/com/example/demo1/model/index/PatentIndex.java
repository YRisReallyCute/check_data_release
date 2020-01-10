package com.example.demo1.model.index;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "patent_data",type = "doc",shards = 1,replicas = 0)
public class PatentIndex {
    @Id
    private Long id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String info_ym;

    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String info_cf;

    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String info_gnzz;

    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String info_lcyy;

    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String info_syjj;

    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String info_zysx;

}
