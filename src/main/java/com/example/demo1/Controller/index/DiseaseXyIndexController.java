package com.example.demo1.Controller.index;

import com.example.demo1.model.PartColums;
import com.example.demo1.model.disease_and_symptom.data_all_symptom_disease_zxy;
import com.example.demo1.model.index.DiseaseXyIndex;
import com.example.demo1.model.index.DiseaseZyIndex;
import com.example.demo1.repository.index.DiseaseXyIndexRepository;
import com.example.demo1.repository.symptom_zy.DataAllSymptomDiseaseZxyRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/diseaseXyIndex")
@CrossOrigin(origins = "http://localhost:8080")
public class DiseaseXyIndexController {
    @Autowired
    private DiseaseXyIndexRepository diseaseXyIndexRepository;

    @Autowired
    private DataAllSymptomDiseaseZxyRepository dataAllSymptomDiseaseZxyRepository;

    @GetMapping("/complexSearch")
    private Map<String,Object> searchPatent(@RequestParam(required = false,defaultValue = "*") String content){
        Map<String,Object> map=new LinkedHashMap<>();
        NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
                .withQuery(
                        QueryBuilders.boolQuery().must(QueryBuilders.multiMatchQuery(content,"info_lcbx","info_jbzd"))
                ).build();
        Iterable<DiseaseXyIndex> result=diseaseXyIndexRepository.search(searchQuery);
        Set<String> set=new LinkedHashSet<>();
        result.forEach(ea->{
            if(set.size()<50){
                set.add(ea.getInfo_mc());
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

        List<PartColums> list=new LinkedList<>();
        set.forEach(ea->{
            data_all_symptom_disease_zxy da=dataAllSymptomDiseaseZxyRepository.findInDiseaseXy(ea);
            list.add(new PartColums(da));
        });
        map.put("totalNum",list.size());
        map.put("result",list);

        return map;
    }
}
