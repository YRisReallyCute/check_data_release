package com.example.demo1.model.index;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Data
@Document(indexName = "patent_data1",type = "doc")
@Setting(settingPath = "static/setting.json")
public class PatentIndex {
    @Id
    private Long id;

//    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    @MultiField(mainField = @Field(analyzer = "ik_smart", searchAnalyzer = "whitespace_ty", type = FieldType.Text),
        otherFields = {@InnerField(suffix = "std", type = FieldType.Text, searchAnalyzer = "standard", analyzer = "standard"),
                @InnerField(suffix = "keyword",type = FieldType.Keyword)})
    private String info_ym;

//    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    @MultiField(mainField = @Field(analyzer = "ik_smart", searchAnalyzer = "whitespace_ty", type = FieldType.Text),
            otherFields = {@InnerField(suffix = "std", type = FieldType.Text, searchAnalyzer = "standard", analyzer = "standard"),
                    @InnerField(suffix = "keyword",type = FieldType.Keyword)})
    private String info_cf;

//    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    @MultiField(mainField = @Field(analyzer = "ik_smart", searchAnalyzer = "whitespace_ty", type = FieldType.Text),
            otherFields = {@InnerField(suffix = "std", type = FieldType.Text, searchAnalyzer = "standard", analyzer = "standard"),
                    @InnerField(suffix = "keyword",type = FieldType.Keyword)})
    private String info_gnzz;

//    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    @MultiField(mainField = @Field(analyzer = "ik_smart", searchAnalyzer = "whitespace_ty", type = FieldType.Text),
            otherFields = {@InnerField(suffix = "std", type = FieldType.Text, searchAnalyzer = "standard", analyzer = "standard"),
                    @InnerField(suffix = "keyword",type = FieldType.Keyword)})
    private String info_lcyy;

//    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")

    @MultiField(mainField = @Field(analyzer = "ik_smart", searchAnalyzer = "whitespace_ty", type = FieldType.Text),
            otherFields = {@InnerField(suffix = "std", type = FieldType.Text, searchAnalyzer = "standard", analyzer = "standard"),
                    @InnerField(suffix = "keyword",type = FieldType.Keyword)})
    private String info_syjj;

//    @Field(type = FieldType.Text,analyzer = "ik_max_word", searchAnalyzer = "ik_smart")

    @MultiField(mainField = @Field(analyzer = "ik_smart", searchAnalyzer = "whitespace_ty", type = FieldType.Text),
            otherFields = {@InnerField(suffix = "std", type = FieldType.Text, searchAnalyzer = "standard", analyzer = "standard"),
                    @InnerField(suffix = "keyword",type = FieldType.Keyword)})
    private String info_zysx;

    private double score;


}
