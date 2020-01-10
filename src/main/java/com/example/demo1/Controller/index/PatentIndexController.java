package com.example.demo1.Controller.index;

import com.example.demo1.model.index.PatentIndex;
import com.example.demo1.model.patent.PartColumsPatent;
import com.example.demo1.model.patent.data_all_drug_patent;
import com.example.demo1.repository.index.PatentIndexRepository;
import com.example.demo1.repository.patent.DataAllDrugPatentRepository;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/complexSearch")
    private Map<String,Object> searchPatent(@RequestParam(required = false,defaultValue = "*") String ym, @RequestParam (required = false,defaultValue = "*")String cf,
                                            @RequestParam(required = false,defaultValue = "*") String gnzz,@RequestParam(required = false,defaultValue = "*")String lcyy,
                                            @RequestParam(required = false,defaultValue = "*") String syjj,@RequestParam(required = false,defaultValue = "*") String zysx,
                                            @PageableDefault(size = 250,page = 0)Pageable pageable){
        Map<String,Object> map=new LinkedHashMap<>();
        NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
                .withQuery(
                        QueryBuilders.boolQuery()
                                .must(QueryBuilders.queryStringQuery(ym).field("info_ym"))
//                                .must(QueryBuilders.matchQuery("info_ym",ym))
                                .must(QueryBuilders.queryStringQuery(cf).field("info_cf"))
                                .must(QueryBuilders.queryStringQuery(gnzz).field("info_gnzz"))
                                .must(QueryBuilders.queryStringQuery(lcyy).field("info_lcyy"))
                                .must(QueryBuilders.queryStringQuery(syjj).field("info_syjj"))
                                .must(QueryBuilders.queryStringQuery(zysx).field("info_zysx"))
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
        Set<String> set=new LinkedHashSet<>();
        result.forEach(ea->{
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
