package com.example.demo1.esbuilder;

import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InlineCode {
    /**
     * 用户输入的查询字段名
     */
    private String keyName;

    /**
     * 查询词分词后的数组
     */
    private String arrayName;

    /**
     * 查询词
     */
    private String searchWords;

    /**
     * split 分词
     */
    private String regex;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 分词器
     */
    private String analyzer;

    /**
     * 分词器所在索引
     */
    private String index;

    /**
     * 索引字段
     */
    private String field;

    public InlineCode withKeyName(String keyName) {
        this.keyName = keyName;
        this.arrayName = keyName + "Val";
        return this;
    }

    public InlineCode withWeight(Integer weight){
        this.weight = weight;
        return this;
    }

    public InlineCode withField(String field){
        this.field = field;
        return this;
    }

    public InlineCode withSearchWords(String searchWords) {
        this.searchWords = searchWords;
        return this;
    }

    public InlineCode withRegix(String regex) {
        this.regex = regex;
        return this;
    }

    public InlineCode withAnalyzer(String analyzer, String index){
        this.analyzer = analyzer;
        this.index = index;
        return this;
    }

    public String[] searchWordsArray() {

        if(this.analyzer!= null) {
            if(this.searchWords == null || this.searchWords.equals(""))
                return new String[]{};
            ElasticsearchTemplate elasticsearchTemplate = BeanUtil.getBean(ElasticsearchTemplate.class);
            AnalyzeRequestBuilder analyzeRequestBuilder =
                    new AnalyzeRequestBuilder(elasticsearchTemplate.getClient(), AnalyzeAction.INSTANCE,
                            this.index, this.searchWords);
            analyzeRequestBuilder.setAnalyzer(this.analyzer);
            List<AnalyzeResponse.AnalyzeToken> tokenList = analyzeRequestBuilder.execute().actionGet().getTokens();

            if(tokenList == null || tokenList.size() == 0)
                return new String[]{};
            List<String> res = new ArrayList<>();
            tokenList.forEach((term)->{
                res.add(term.getTerm());
            });
            String [] s = new String[]{};
            return res.toArray(s);
        }

        return this.searchWords.split(regex);
    }

    public HashMap<String, Object> param(){
        HashMap<String, Object> map = new HashMap<>();
        map.put(keyName, searchWordsArray().length);
        map.put(arrayName, searchWordsArray());
        return map;
    }

    public String build(){
        return
                String.format(
                        "for(int i = 0; i < params.%s; i++){" +
                                "if(doc['%s'].value!=null&&" +
                                "doc['%s'].value.contains(params['%s'][i])){" +
                                "total+=%d;" +
                                "}" +
                                "}", this.keyName, this.field, this.field, this.arrayName, this.weight);
    }

}