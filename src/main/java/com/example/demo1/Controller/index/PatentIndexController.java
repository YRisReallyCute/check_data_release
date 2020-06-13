package com.example.demo1.Controller.index;

import com.example.demo1.model.index.PatentIndex;
import com.example.demo1.model.patent.PartColumsPatent;
import com.example.demo1.model.patent.data_all_drug_patent;
import com.example.demo1.repository.index.PatentIndexRepository;
import com.example.demo1.repository.patent.DataAllDrugPatentRepository;
import com.example.demo1.service.index.SysVarValueService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/complexSearch")
    private Map<String,Object> searchPatent(@RequestParam(required = false,defaultValue = "*") String ym, @RequestParam (required = false,defaultValue = "*")String cf,
                                            @RequestParam(required = false,defaultValue = "*") String gnzz,@RequestParam(required = false,defaultValue = "*")String lcyy,
                                            @RequestParam(required = false,defaultValue = "*") String zysx,
                                            @PageableDefault(size = 250,page = 0)Pageable pageable){
        Map<String,Object> map=new LinkedHashMap<>();
        NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
                .withQuery(
                        QueryBuilders.boolQuery()
                                .must(QueryBuilders.boolQuery()
                                        .must(QueryBuilders.boolQuery()
                                                .should(QueryBuilders.queryStringQuery(ym).field("info_ym"))
                                                .should(QueryBuilders.matchPhraseQuery("info_ym.std",ym))
                                                .boost(Float.parseFloat(sysVarValueService.getValue("data_patent_ym")))
                                        )
                                        .must(QueryBuilders.boolQuery()
                                                .should(QueryBuilders.queryStringQuery(lcyy).field("info_lcyy"))
                                                .should(QueryBuilders.matchPhraseQuery("info_lcyy.std",lcyy))
                                                .boost(Float.parseFloat(sysVarValueService.getValue("data_patent_lcyy")))
                                        )
                                        .must(QueryBuilders.boolQuery()
                                                .should(QueryBuilders.queryStringQuery(gnzz).field("info_gnzz"))
                                                .should(QueryBuilders.matchPhraseQuery("info_gnzz.std",gnzz))
                                                .boost(Float.parseFloat(sysVarValueService.getValue("data_patent_gnzz")))
                                        )
                                        .must(QueryBuilders.boolQuery()
                                                .should(QueryBuilders.queryStringQuery(cf).field("info_cf"))
                                                .should(QueryBuilders.matchPhraseQuery("info_cf.std",cf))
                                                .boost(Float.parseFloat(sysVarValueService.getValue("data_patent_cf")))
                                        )
                                        .must(QueryBuilders.boolQuery()
                                                .should(QueryBuilders.boolQuery()
                                                        .should(QueryBuilders.queryStringQuery(zysx).field("info_syjj"))
                                                        .should(QueryBuilders.matchPhraseQuery("info_syjj.std",zysx))
                                                        .boost(Float.parseFloat(sysVarValueService.getValue("data_patent_syjj")))
                                                )
                                                .should(QueryBuilders.boolQuery()
                                                        .should(QueryBuilders.queryStringQuery(zysx).field("info_zysx"))
                                                        .should(QueryBuilders.matchPhraseQuery("info_zysx.std",zysx))
                                                        .boost(Float.parseFloat(sysVarValueService.getValue("data_patent_zysx")))
                                                )
                                        )

                                )



//                                .must(QueryBuilders.queryStringQuery(ym).field("info_ym"))
////                                .must(QueryBuilders.matchQuery("info_ym",ym))
//                                .must(QueryBuilders.queryStringQuery(cf).field("info_cf"))
//                                .must(QueryBuilders.queryStringQuery(gnzz).field("info_gnzz"))
//                                .must(QueryBuilders.queryStringQuery(lcyy).field("info_lcyy"))
//                                .must(QueryBuilders.queryStringQuery(syjj).field("info_syjj"))
//                                .must(QueryBuilders.queryStringQuery(zysx).field("info_zysx"))
//
//                                .must(QueryBuilders.boolQuery()
//                                        .should(QueryBuilders.queryStringQuery(ym).field("info_ym"))
//                                        .should(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery("info_ym")))
//                                )
//                                .must(QueryBuilders.boolQuery()
//                                        .should(QueryBuilders.queryStringQuery(cf).field("info_cf"))
//                                        .should(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery("info_cf")))
//                                )
//                                .must(QueryBuilders.boolQuery()
//                                        .should(QueryBuilders.queryStringQuery(gnzz).field("info_gnzz"))
//                                        .should(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery("info_gnzz")))
//                                )
//                                .must(QueryBuilders.boolQuery()
//                                        .should(QueryBuilders.queryStringQuery(lcyy).field("info_lcyy"))
//                                        .should(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery("info_lcyy")))
//                                )
//                                .must(QueryBuilders.boolQuery()
//                                        .should(QueryBuilders.queryStringQuery(syjj).field("info_syjj"))
//                                        .should(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery("info_syjj")))
//                                )
//                                .must(QueryBuilders.boolQuery()
//                                        .should(QueryBuilders.queryStringQuery(zysx).field("info_zysx"))
//                                        .should(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery("info_zysx")))
//                                )

                )
                .withPageable(pageable)
                .build();
        Iterable<PatentIndex> result=patentIndexRepository.search(searchQuery);

        Page<PatentIndex> resultPage=elasticsearchTemplate.queryForPage(searchQuery, PatentIndex.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                ArrayList<PatentIndex> patentList=new ArrayList<>();
                SearchHits hits = searchResponse.getHits();
                for (SearchHit hit:hits) {
                    if(hits.getHits().length == 0){
                        return null;
                    }
                    else if(hit.getSourceAsMap()!=null){
                        PatentIndex p =new PatentIndex();
                        p.setScore((double)hit.getScore());
                        String temp_ym = String.valueOf(hit.getSourceAsMap().get("info_ym"));
                        p.setInfo_ym(temp_ym);
                        patentList.add(p);
                    }
                }
                if(patentList.size()>0)
                    return new AggregatedPageImpl<T>((List<T>) patentList);
                return null;
            }
        });

        List<PatentIndex> patentList=resultPage.getContent();
        Set<String> set=new LinkedHashSet<>();
        Set<double> scoreSet = new LinkedHashSet<>();
        patentList.forEach(ea->{
            if(set.size()<50){
                set.add(ea.getInfo_ym());
            }

        });
        if(set.size()==50){
            map.put("code","201");
            map.put("info","检索结果超过50条");
        }
        else {
            map.put("info", "检索结果小于50条");
            map.put("code", "200");
        }

        List<PartColumsPatent> list=new LinkedList<>();
        if(set.size()!=0) {
            set.forEach(ea -> {
                data_all_drug_patent da = dataAllDrugPatentRepository.findByYm(ea);
                list.add(new PartColumsPatent(da));
            });
        }
        map.put("totalNum",list.size());
        map.put("result",list);
        return map;
    }
}
