package com.example.demo1.Controller.check_data;


import com.example.demo1.model.herbal.HerbalDataAll;
import com.example.demo1.model.herbal.PartColumsHerbal;
import com.example.demo1.repository.herbal.HerbalDataAllRepository;
import com.example.demo1.repository.patent.DataAllDrugPatentRepository;
import com.example.demo1.model.patent.PartColumsPatent;
import com.example.demo1.model.patent.data_all_drug_patent;
import com.example.demo1.service.DataAllDrugHerbalService;
import com.example.demo1.service.DataAllDrugPatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/data_all_drug_herbal")
@CrossOrigin
public class DataAllHerbalController {

    @Autowired
    private DataAllDrugHerbalService dataAllDrugHerbalService;

    @Autowired
    private HerbalDataAllRepository herbalDataAllRepository;

    @GetMapping("/findById")
    public Map<String , Object> findById(@RequestParam Integer id){

        Map<String, Object> map = new HashMap<>();
        Optional<HerbalDataAll> r = herbalDataAllRepository.findById(id);
        if(r.isPresent()){
            map.put("code", "200");
            map.put("result", r);
        }else{
            map.put("code", "404");
            map.put("msg", "没有结果");
        }
        return map;
    }


    @PostMapping("/updateData")
    public Map<String, Object> update(
            @RequestParam Integer id,
            @RequestParam(required = false) String info_ym,
            @RequestParam(required = false) String info_bm,
            @RequestParam(required = false) String info_cc,
            @RequestParam(required = false) String info_dx,
            @RequestParam(required = false) String info_ff,
            @RequestParam(required = false) String info_gj,
            @RequestParam(required = false) String info_gjls,
            @RequestParam(required = false) String info_gnzz,
            @RequestParam(required = false) String info_hxcf,
            @RequestParam(required = false) String info_lcyy,
            @RequestParam(required = false) String info_ly,
            @RequestParam(required = false) String info_py,
            @RequestParam(required = false) String info_pz,
            @RequestParam(required = false) String info_sjfb,
            @RequestParam(required = false) String info_xw,
            @RequestParam(required = false) String info_xz,
            @RequestParam(required = false) String info_yfyl,
            @RequestParam(required = false) String info_ylzy,
            @RequestParam(required = false) String info_ywm,
            @RequestParam(required = false) String info_yxt,
            @RequestParam(required = false) String info_zc,
            @RequestParam(required = false) String info_zl,
            @RequestParam(required = false) String info_zp,
            @RequestParam(required = false) String info_zy,
            @RequestParam(required = false) String info_bz,
            @RequestParam(required = false) String info_jb,
            @RequestParam(required = false) String info_zj,
            @RequestParam(required = false) String info_qt,
            @RequestParam (required = false) String comment,
            @RequestParam Integer status) {

        Map<String, Object> map = new HashMap<>();

        HerbalDataAll dad = new HerbalDataAll(id,
                info_ym,
                info_bm,
                info_cc,
                info_dx,
                info_ff,
                info_gj,
                info_gjls,
                info_gnzz,
                info_hxcf,
                info_lcyy,
                info_ly,
                info_py,
                info_pz,
                info_sjfb,
                info_xw,
                info_xz,
                info_yfyl,
                info_ylzy,
                info_ywm,
                info_yxt,
                info_zc,
                info_zl,
                info_zp,
                info_zy,
                info_bz,
                info_jb,
                info_zj,
                info_qt,status, comment
        );

        int result=herbalDataAllRepository.updateCommentAndStatus(dad);
        if(result==1){
            map.put("code","200");
        }
        else{
            //更新异常
            map.put("code","400");
        }
        return map;
    }

    //TODO:DEBUG,findPartList

    @GetMapping(path = "/getPartList")   @ResponseBody
    //@ResponseStatus(code= HttpStatus.SWITCHING_PROTOCOLS,reason = "success")
    public Map<String,Object> getPartList(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam (name = "status",required = false,defaultValue = "-1") int status,
                                          @RequestParam (name = "name", required = false, defaultValue = "") String name,
                                          @RequestParam (name = "type",required = false,defaultValue = "")String type
    )
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>();

//        int s_x_batch=Integer.parseInt(symptom_xy_batch);
//        int s_z_batch=Integer.parseInt(symptom_zy_batch);

        int yaobw=0;
        int zyybd=0;
        int baike=0;

        if(!type.equals("")){
            String[] type_list=type.split("/");
            for (int i=0;i<type_list.length;i++) {
                if (type_list[i].equals("yaobw")) {
                    yaobw = 1;
                } else if (type_list[i].equals("zyybd")) {
                    zyybd = 1;
                }else if (type_list[i].equals("baike")) {
                    baike = 1;
                }
                else {
                }
            }
        }
//        if(symptom_xy_zgyyxxcxpt==0)s_x_batch=0;
//        if(symptom_zy_zgyyxxcxpt==0)s_z_batch=0;

        PartColumsHerbal p=new PartColumsHerbal(status,name,yaobw,zyybd,baike);

        map.put("code","200");
        Map<String,Object> map2=dataAllDrugHerbalService.getPartList(p,page,size);
        map.putAll(map2);

        return map;
    }

    @GetMapping("/deleteById")
    private Map<String,Object> deleteById(@RequestParam int id){
        Map<String, Object> map=new LinkedHashMap<>();
        herbalDataAllRepository.deleteById(id);
        map.put("code","200");
        return map;
    }

}
