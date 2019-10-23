package com.example.demo1.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.print.DocFlavor;

@Entity
@Table(name = "data_search_word_record")
@Data
@Accessors(chain = true)
public class SearchContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String searchContent;
    private int symptomZy;
    private int patent;
    private int herbal;
    private int status;

    public SearchContent(String content,int zy,int patent,int herbal, int status){
        this.searchContent=content;
        this.symptomZy=zy;
        this.patent=patent;
        this.herbal=herbal;
        this.status=status;
    }
}
