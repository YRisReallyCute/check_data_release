package com.example.demo1.Controller.check_data;

import com.example.demo1.Repository.symptom_zy.DataAllSymptomDiseaseZxyRepository;
import com.example.demo1.model.PartColums;
import com.example.demo1.model.disease_and_symptom.data_all;
import com.example.demo1.model.disease_and_symptom.data_all_symptom_disease_zxy;
import com.example.demo1.service.DataAllZxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/data_all_zxy")
@CrossOrigin
public class DataAllZxyController {
    @Autowired
    DataAllSymptomDiseaseZxyRepository dataAllSymptomDiseaseZxyRepository;

    @Autowired
    DataAllZxyService dataAllZxyService;


    @GetMapping(path = "/getPartList")   @ResponseBody
    //@ResponseStatus(code= HttpStatus.SWITCHING_PROTOCOLS,reason = "success")
    public Map<String,Object> getPartList(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam int status,
                                          @RequestParam int type,
                                          @RequestParam (name = "name", required = false, defaultValue = "") String name,
                                          @RequestParam (name = "origin_type",required = false,defaultValue = "")String origin_type,
                                          @RequestParam (name = "symptom_zy_batch",required = false,defaultValue = "0")String symptom_zy_batch,
                                          @RequestParam(name = "symptom_xy_batch",required = false,defaultValue = "0")String symptom_xy_batch
    )
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>();

        int s_x_batch=Integer.parseInt(symptom_xy_batch);
        int s_z_batch=Integer.parseInt(symptom_zy_batch);

        int disease_zy_baidubaike=0,disease_zy_zgyyxxcxpt=0,disease_zy_zgyyxxcxpt_old=0,
                disease_xy_baidubaike=0,disease_xy_zgyyxxcxpt=0,disease_xy_zgyyxxcxpt_old=0,
                symptom_zy_baidubaike=0,symptom_zy_zgyyxxcxpt=0,
                symptom_xy_baidubaike=0,symptom_xy_zgyyxxcxpt=0,symptom_human=0;

        if(!origin_type.equals("")){
            String[] type_list=origin_type.split("/");
            for (int i=0;i<type_list.length;i++) {
                if (type_list[i].equals("baidubaike")) {
                    symptom_zy_baidubaike = 1;
                } else if (type_list[i].equals("diseaseZy")) {
                    disease_zy_zgyyxxcxpt = 1;
                } else if (type_list[i].equals("diseaseXy")) {
                    disease_xy_zgyyxxcxpt = 1;
                } else if (type_list[i].equals("symptomXy")) {
                    symptom_xy_zgyyxxcxpt = 1;
                } else if (type_list[i].equals("symptomZy")) {
                    symptom_zy_zgyyxxcxpt = 1;
                } else if (type_list[i].equals("diseaseZyOld")) {
                    disease_zy_zgyyxxcxpt_old = 1;
                } else if (type_list[i].equals("diseaseXyOld")) {
                    disease_xy_zgyyxxcxpt_old = 1;
                }else if (type_list[i].equals("human")) {
                    symptom_human = 1;
                } else {
                }
            }
        }
//        if(symptom_xy_zgyyxxcxpt==0)s_x_batch=0;
//        if(symptom_zy_zgyyxxcxpt==0)s_z_batch=0;

        PartColums p=new PartColums(type,status,s_x_batch,s_z_batch,name,symptom_zy_baidubaike,disease_xy_zgyyxxcxpt,disease_zy_zgyyxxcxpt,symptom_xy_zgyyxxcxpt,symptom_zy_zgyyxxcxpt,disease_xy_zgyyxxcxpt_old,disease_zy_zgyyxxcxpt_old,symptom_human);

        map.put("code","200");
        map.putAll(dataAllZxyService.getPartList(p,page,size));

        return map;
    }


    @GetMapping(path = "/findById")
    public Map<String,Object> findById(@RequestParam Integer id){
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        Optional<data_all_symptom_disease_zxy> list= dataAllSymptomDiseaseZxyRepository.findById(id);

        //替换标签<h1>,<h2>
        //list.get().replaceStr(replaceLabels);

        if (!list.isPresent()){
            map.put("code","404");
        }
        else {
            map.put("code", "200");
            map.put("result", list);
        }
        return map;
    }


    @PostMapping("/update")
    public Map<String,Object> updataDataAllZxy(@RequestParam Integer id
                                               ,@RequestParam(required = false)Integer type
            , @RequestParam (required = false)String info_mc
            , @RequestParam (required = false)String info_mcjs
            , @RequestParam (required = false)String info_bm
            , @RequestParam (required = false)String info_ywmc
            , @RequestParam (required = false)String info_fk
            , @RequestParam (required = false)String info_dfrq
            , @RequestParam (required = false)String info_fbbw
            , @RequestParam (required = false)String info_xybm
            , @RequestParam (required = false)String info_bybj
            , @RequestParam (required = false)String info_lcbx
            , @RequestParam (required = false)String info_jbzd
            , @RequestParam (required = false)String info_bzsz
            , @RequestParam (required = false)String info_fj
            , @RequestParam (required = false)String info_zjlf
            , @RequestParam (required = false)String info_yfbj
            , @RequestParam (required = false)String info_yslf
            , @RequestParam (required = false)String info_tnlf
            , @RequestParam (required = false)String info_wfwz
            , @RequestParam (required = false)String info_hl
            , @RequestParam (required = false)String info_yh
            , @RequestParam (required = false)String info_qt
            , @RequestParam Integer status
            , @RequestParam String comment
    ){
        Map<String,Object> map = new LinkedHashMap<String,Object>();

        data_all_symptom_disease_zxy all=new data_all_symptom_disease_zxy(
                id
                ,type
                ,info_mc
                ,info_mcjs
                ,info_bm
                ,info_ywmc
                ,info_fk
                ,info_dfrq
                ,info_fbbw
                ,info_xybm
                ,info_bybj
                ,info_lcbx
                ,info_jbzd
                ,info_bzsz
                ,info_fj
                ,info_zjlf
                ,info_yfbj
                ,info_yslf
                ,info_tnlf
                ,info_wfwz
                ,info_hl
                ,info_yh
                ,info_qt
                ,status
                ,comment
        );
        int result=dataAllSymptomDiseaseZxyRepository.updateCommentAndStatus(all);
        if(result==1){
            map.put("code","200");
        }
        else{
            //更新异常
            map.put("code","400");
        }
        return map;

    }
}
