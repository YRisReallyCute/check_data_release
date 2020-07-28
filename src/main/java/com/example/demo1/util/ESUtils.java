package com.example.demo1.util;

import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.ArrayList;
import java.util.List;

public class ESUtils {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 调用指定分词器之后的分词结果
     */
    public List getAnalyzeSearchTerms(String indexName,String searchContent,String method){
        AnalyzeRequestBuilder analyzeRequest = new AnalyzeRequestBuilder(elasticsearchTemplate.getClient(), AnalyzeAction.INSTANCE,indexName,searchContent);
        analyzeRequest.setAnalyzer(method);
        List<AnalyzeResponse.AnalyzeToken> tokens = analyzeRequest.execute().actionGet().getTokens();

        List searchTermList = new ArrayList();

        tokens.forEach(token -> {
            searchTermList.add(token.getTerm());
        });

        return searchTermList;
    }

}
