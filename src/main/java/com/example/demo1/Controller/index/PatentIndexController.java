package com.example.demo1.Controller.index;

import com.example.demo1.esbuilder.InlineCode;
import com.example.demo1.esbuilder.InlineCodeBuilder;
import com.example.demo1.esbuilder.MyScriptBuilder;
import com.example.demo1.model.index.PatentIndex;
import com.example.demo1.model.patent.PartColumsPatent;
import com.example.demo1.model.patent.data_all_drug_patent;
import com.example.demo1.repository.index.PatentIndexRepository;
import com.example.demo1.repository.patent.DataAllDrugPatentRepository;
import com.example.demo1.service.index.SysVarValueService;
import com.example.demo1.util.ESUtils;
import com.example.demo1.util.HighlightUtil;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.*;

@RestController
@RequestMapping("/patentIndex")
@CrossOrigin(origins = "http://localhost:8080")
public class PatentIndexController {

    @Autowired
    PatentIndexRepository patentIndexRepository;
    @Autowired
    DataAllDrugPatentRepository dataAllDrugPatentRepository;
    @Autowired
    SysVarValueService sysVarValueService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private ESUtils esUtils;

    @GetMapping("/complexSearch")
    private Map<String,Object> searchPatent(@RequestParam(required = false,defaultValue = "*") String ym, @RequestParam (required = false,defaultValue = "*")String cf,
                                            @RequestParam(required = false,defaultValue = "*") String gnzz,@RequestParam(required = false,defaultValue = "*")String lcyy,
                                            @RequestParam(required = false,defaultValue = "*") String zysx,
                                            @PageableDefault(size = 250,page = 0)Pageable pageable){

        ym = generateSearchWords(ym);cf = generateSearchWords(cf);
        gnzz = generateSearchWords(gnzz);lcyy = generateSearchWords(lcyy);
        zysx = generateSearchWords(zysx);

        Map<String,Object> map=new LinkedHashMap<>();
        String indexName = "patent_data1";
        String regix = "[,，；; ]";
        Script myScript = new MyScriptBuilder()
                .withLang("painless")
                .withScriptType(ScriptType.INLINE)
                .withScriptCode(new InlineCodeBuilder()
                        .addBody(
                                new InlineCode().withKeyName("ym")
                                        .withField("info_ym.keyword")
                                        .withSearchWords(ym)
                                        .withRegix(regix)
                                        .withWeight(Integer.valueOf(sysVarValueService.getValue("data_patent_weight")))
                        ).addBody(
                                new InlineCode().withKeyName("cf")
                                        .withField("info_cf.keyword")
                                        .withSearchWords(cf)
                                        .withRegix(regix)
                                        .withWeight(Integer.valueOf(sysVarValueService.getValue("data_patent_weight")))
                        ).addBody(
                                new InlineCode().withKeyName("gnzz")
                                        .withField("info_gnzz.keyword")
                                        .withSearchWords(gnzz)
                                        .withRegix(regix)
                                        .withWeight(Integer.valueOf(sysVarValueService.getValue("data_patent_weight")))
                        ).addBody(
                                new InlineCode().withKeyName("lcyy")
                                        .withField("info_lcyy.keyword")
                                        .withSearchWords(lcyy)
                                        .withRegix(regix)
                                        .withWeight(Integer.valueOf(sysVarValueService.getValue("data_patent_weight")))
                        ).addBody(
                                new InlineCode().withKeyName("zysx")
                                        .withField("info_zysx.keyword")
                                        .withSearchWords(zysx)
                                        .withRegix(regix)
                                        .withWeight(Integer.valueOf(sysVarValueService.getValue("data_patent_weight")))
                        ).addBody(
                                new InlineCode().withKeyName("syjj")
                                        .withField("info_syjj.keyword")
                                        .withSearchWords(zysx)
                                        .withRegix(regix)
                                        .withWeight(Integer.valueOf(sysVarValueService.getValue("data_patent_weight")))
                        ))
                .build();

        ScriptSortBuilder scriptSortBuilder = new ScriptSortBuilder(myScript, ScriptSortBuilder.ScriptSortType.NUMBER).order(SortOrder.DESC);

        String[] fieldList = new String[]{"info_ym", "info_lcyy", "info_gnzz", "info_cf", "info_syjj", "info_zysx"};
        String pre = "<em>";
        String post = "</em>";
        Map<String, Object> fieldKeywordMap = new HashMap<>();
        fieldKeywordMap.put(fieldList[0], ym);fieldKeywordMap.put(fieldList[1], lcyy);
        fieldKeywordMap.put(fieldList[2], gnzz);fieldKeywordMap.put(fieldList[3], cf);
        fieldKeywordMap.put(fieldList[4], zysx);fieldKeywordMap.put(fieldList[5], zysx);

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        for (String s : fieldList) {
            String searchword = fieldKeywordMap.get(s).toString();
            BoolQueryBuilder bq = new BoolQueryBuilder();
            bq.should(QueryBuilders.queryStringQuery(searchword.replace("/", " ")).field(s).analyzer("comma"));

            String[] keywords = searchword.split("[/,;，； ]");
            for (String keyword : keywords) {
                bq.should(QueryBuilders.matchPhraseQuery(s + ".std", keyword));
            }
            bq.boost(Float.parseFloat(sysVarValueService.getValue(s.replace("info","data_patent"))));
            boolQueryBuilder.must(bq);
        }

        NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery().must(boolQueryBuilder))
                .withHighlightFields(new HighlightBuilder.Field("info_ym").postTags(post).preTags(pre).fragmentSize(0).numOfFragments(0),
                        new HighlightBuilder.Field("info_cf").postTags(post).preTags(pre).fragmentSize(0).numOfFragments(0),
                        new HighlightBuilder.Field("info_lcyy").postTags(post).preTags(pre).fragmentSize(0).numOfFragments(0),
                        new HighlightBuilder.Field("info_gnzz").postTags(post).preTags(pre).fragmentSize(0).numOfFragments(0),
                        new HighlightBuilder.Field("info_syjj").postTags(post).preTags(pre).fragmentSize(0).numOfFragments(0),
                        new HighlightBuilder.Field("info_zysx").postTags(post).preTags(pre).fragmentSize(0).numOfFragments(0),
                        new HighlightBuilder.Field("info_ym.std").postTags(post).preTags(pre).fragmentSize(0).numOfFragments(0),
                        new HighlightBuilder.Field("info_cf.std").postTags(post).preTags(pre).fragmentSize(0).numOfFragments(0),
                        new HighlightBuilder.Field("info_lcyy.std").postTags(post).preTags(pre).fragmentSize(0).numOfFragments(0),
                        new HighlightBuilder.Field("info_gnzz.std").postTags(post).preTags(pre).fragmentSize(0).numOfFragments(0),
                        new HighlightBuilder.Field("info_syjj.std").postTags(post).preTags(pre).fragmentSize(0).numOfFragments(0),
                        new HighlightBuilder.Field("info_zysx.std").postTags(post).preTags(pre).fragmentSize(0).numOfFragments(0))
                .withPageable(pageable)
                .withSort(scriptSortBuilder)// 自定义排序
                .withSort(SortBuilders.scoreSort().order(SortOrder.DESC)) // ES打分排序  (有两个的时候，先按照1 再按照2)
                .build();
        Iterable<PatentIndex> result=patentIndexRepository.search(searchQuery);

        String analyzer = "whitespace_td";
        List<String> ymTokens=getAnalyzeSearchTerms(indexName,ym,"ik_smart");
        List<String> cfTokens=getAnalyzeSearchTerms(indexName,cf,analyzer);
        List<String> gnzzTokens=getAnalyzeSearchTerms(indexName,gnzz,analyzer);
        List<String> lcyyTokens=getAnalyzeSearchTerms(indexName,lcyy,analyzer);
        List<String> zysxTokens=getAnalyzeSearchTerms(indexName,zysx,analyzer);


        Page<PatentIndex> resultPage=elasticsearchTemplate.queryForPage(searchQuery, PatentIndex.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                ArrayList<PatentIndex> patentList=new ArrayList<>();
                SearchHits hits = searchResponse.getHits();
                if(hits.getHits().length == 0){
                    return null;
                }
                for (SearchHit hit:hits) {
                    if(hit.getSourceAsMap()!=null){
                        PatentIndex p =new PatentIndex();
                        p.setSortValue(hit.getSortValues());
                        String id=String.valueOf(hit.getSourceAsMap().get("id"));
                        String temp_ym = String.valueOf(hit.getSourceAsMap().get("info_ym"));
                        String temp_syjj=String.valueOf(hit.getSourceAsMap().get("info_syjj"));
                        String temp_cf=String.valueOf(hit.getSourceAsMap().get("info_cf"));
                        String temp_gnzz=String.valueOf(hit.getSourceAsMap().get("info_gnzz"));
                        String temp_lcyy=String.valueOf(hit.getSourceAsMap().get("info_lcyy"));
                        String temp_zysx=String.valueOf(hit.getSourceAsMap().get("info_zysx"));
                        p.setInfo_cf(temp_cf);
                        p.setInfo_gnzz(temp_gnzz);
                        p.setInfo_lcyy(temp_lcyy);
                        p.setInfo_syjj(temp_syjj);
                        p.setInfo_zysx(temp_zysx);

                        try {
                            if(chooseHighlight(hit, "info_cf") != null) {
                                p.setInfo_cf(chooseHighlight(hit, "info_cf"));
                            }
                            if(chooseHighlight(hit, "info_gnzz") != null) {
                                p.setInfo_gnzz(chooseHighlight(hit, "info_gnzz"));
                            }
                            if(chooseHighlight(hit, "info_lcyy") != null) {
                                p.setInfo_lcyy(chooseHighlight(hit, "info_lcyy"));
                            }
                            if(chooseHighlight(hit, "info_zysx") != null){
                                p.setInfo_zysx(chooseHighlight(hit, "info_zysx"));
                            }
                            if(chooseHighlight(hit, "info_syjj") != null) {
                                p.setInfo_syjj(chooseHighlight(hit, "info_syjj"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


//                        if(cfTokens!=null) {
//                            cfTokens.forEach(cfToken -> {
//                                p.setInfo_cf(p.getInfo_cf().replace(cfToken, "<span style='color: red'>" + cfToken + "</span>"));
//                            });
//                        }
//                        if(gnzzTokens!=null) {
//                            gnzzTokens.forEach(gnzzToken -> {
//                                p.setInfo_gnzz(p.getInfo_gnzz().replace(gnzzToken,"<span style='color: red'>" + gnzzToken + "</span>"));
//                            });
//                        }
//                        if(lcyyTokens!=null) {
//                            lcyyTokens.forEach(lcyyToken -> {
//                                p.setInfo_lcyy(p.getInfo_lcyy().replace(lcyyToken,"<span style='color: red'>" + lcyyToken + "</span>"));
//                            });
//                        }
//                        if(zysxTokens!=null) {
//                            zysxTokens.forEach(zysxToken -> {
//                                p.setInfo_syjj(p.getInfo_syjj().replace(zysxToken,"<span style='color: red'>" + zysxToken + "</span>"));
//                                p.setInfo_zysx(p.getInfo_zysx().replace(zysxToken,"<span style='color: red'>" + zysxToken + "</span>"));
//                            });
//                        }

                        p.setInfo_ym(temp_ym);
                        patentList.add(p);
                    }
                }
                if(patentList.size()>0)
                    return new AggregatedPageImpl<T>((List<T>) patentList);
                return null;
            }
        });

        List<Map> list=new LinkedList<>();
        if(resultPage==null){
            map.put("info", "检索结果小于50条");
            map.put("code", "200");
            map.put("totalNum",0);
            map.put("result",list);
            return map;
        }

        List<PatentIndex> patentList=resultPage.getContent();
        if(patentList.size()<=50){
            map.put("code","201");
            map.put("info","检索结果小于50条");
        }
        else {
            map.put("info", "检索结果超过50条");
            map.put("code", "200");
        }
//        Set<String> set=new LinkedHashSet<>();
//        Set<double> scoreSet = new LinkedHashSet<>();
        patentList.forEach(ea->{
            if(list.size()<50){
                int b=0;
                for (Map tmp:list) {
                    if(tmp.get("info_ym").equals(ea.getInfo_ym())){
                        b=1;
                        break;
                    }
                }
                if(b==0){
                    Map<String,Object> map1=new LinkedHashMap();
                    data_all_drug_patent da = dataAllDrugPatentRepository.findByYm(ea.getInfo_ym());
                    PartColumsPatent p=new PartColumsPatent(da);
                    map1.put("id",da.getId());
                    map1.put("info_ym",da.getInfo_ym());
                    map1.put("status",da.getStatus());
                    map1.put("comment",da.getComment());
                    map1.put("origin_yaobw",da.getOrigin_yaobw());
                    map1.put("yaobw_id",da.getYaobw_id());
                    map1.put("origin_zyybd",da.getOrigin_zyybd());
                    map1.put("zyybd_id",da.getZyybd_id());
                    map1.put("origin_baike",da.getOrigin_baike());
                    map1.put("baike_id",da.getBaike_id());
                    map1.put("sortValues",ea.getSortValue());
//                    String ea_ym = ea.getInfo_ym();
                    if(ymTokens!=null) {
                        ymTokens.forEach(ymToken -> {
                            ea.setInfo_ym(ea.getInfo_ym().replace(ymToken,"<span style='color: red'>" + ymToken + "</span>"));
                        });
                    }
                    map1.put("content",ea);
                    list.add(map1);
                }
            }
        });


//        if(set.size()!=0) {
//            set.forEach(ea -> {
//                data_all_drug_patent da = dataAllDrugPatentRepository.findByYm(ea.getInfo_ym());
//                PartColumsPatent p = new PartColumsPatent(da);
//                p.setScore(ea.getScore());
//                list.add(p);
//            });
//        }
        map.put("totalNum",list.size());
        map.put("result",list);
        return map;
    }

    /**
     * change word a b c -> a1/a2;b1/b2;c1/c2
     */
    private String generateSearchWords(String word){
        /*String regix = "[,，;； ]";
        String[] wordList = word.split(regix);
        StringBuilder res = new StringBuilder();
        for (String s : wordList) {
            List<String> synonymList = this.getAnalyzeSearchTerms("patent_data1", word, "whitespace_td");
            assert synonymList != null;
            StringBuilder tmp = new StringBuilder();
            for (String s1 : synonymList) {
                tmp.append("/");
                tmp.append(s1);
            }
            tmp.deleteCharAt(0);
            res.append(";");
            res.append(tmp);
        }
        res.deleteCharAt(0);
        return res.toString();*/
        return word;
    }

    /**
     * 获取分词结果
     * @param indexName 索引名称
     * @param searchContent 搜索内容
     * @param method 分词方式
     * @return
     */
    private List<String> getAnalyzeSearchTerms(String indexName,String searchContent,String method){

        if(searchContent.equals("*"))
            return null;

        AnalyzeRequestBuilder analyzeRequest = new AnalyzeRequestBuilder(elasticsearchTemplate.getClient(), AnalyzeAction.INSTANCE,indexName,searchContent);
        analyzeRequest.setAnalyzer(method);
        List<AnalyzeResponse.AnalyzeToken> tokens = analyzeRequest.execute().actionGet().getTokens();

        List searchTermList = new ArrayList();

        tokens.forEach(token -> {
            searchTermList.add(token.getTerm());
        });

        return searchTermList;
    }


    private String chooseHighlight(SearchHit hit, String field) throws Exception {
        String res;
        String post = "</em>";
        String pre = "<em>";
        if(hit.getHighlightFields().get(field) != null){
            res = hit.getHighlightFields().get(field).fragments()[0].toString();
            if(hit.getHighlightFields().get(field + ".std") != null)
                res = HighlightUtil.combine(hit.getHighlightFields().get(field).fragments()[0].toString(),
                        hit.getHighlightFields().get(field + ".std").fragments()[0].toString(), pre, post);
        } else if(hit.getHighlightFields().get(field + ".std") != null) {
            res = hit.getHighlightFields().get(field + ".std").fragments()[0].toString();
        } else {
            res = null;
        }
        if(res != null) {
            res = res.replace("<em>", "<span style='color:red'>").replace("</em>", "</span>");
        }
        return res;
    }
}
