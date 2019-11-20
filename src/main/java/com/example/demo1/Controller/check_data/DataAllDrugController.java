package com.example.demo1.Controller.check_data;


import com.example.demo1.Repository.patent.DataAllDrugRepository;
import com.example.demo1.model.patent.PartColumsPatent;
import com.example.demo1.model.patent.data_all_drug;
import com.example.demo1.service.DataAllDrugPatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/data_all_drug_patent")
@CrossOrigin
public class DataAllDrugController {

    @Autowired
    private DataAllDrugPatentService dataAllDrugPatentService;

    @Autowired
    private DataAllDrugRepository dataAllDrugReposity;

    @GetMapping("/findById")
    public Map<String , Object> findById(@RequestParam Integer id){

        Map<String, Object> map = new HashMap<>();
        Optional<data_all_drug> r = dataAllDrugReposity.findById(id);
        if(r.isPresent()){
            map.put("code", "200");
            map.put("result", r);
        }else{
            map.put("code", "404");
            map.put("msg", "没有结果");
        }
        return map;
    }


    @PostMapping("updateData")
    public Map<String, Object> update(
            @RequestParam Integer id,
            @RequestParam(required = false) String info_ym,
            @RequestParam(required = false) String info_bm,
            @RequestParam(required = false) String info_cf,
            @RequestParam(required = false) String info_zffx,
            @RequestParam(required = false) String info_gnzz,
            @RequestParam(required = false) String info_zbff,
            @RequestParam(required = false) String info_jxgg,
            @RequestParam(required = false) String info_yfyl,
            @RequestParam(required = false) String info_zlbz,
            @RequestParam(required = false) String info_syjj,
            @RequestParam(required = false) String info_zysx,
            @RequestParam(required = false) String info_xdyj,
            @RequestParam(required = false) String info_lcyy,
            @RequestParam(required = false) String info_fg,
            @RequestParam(required = false) String info_qtzj,
            @RequestParam(required = false) String info_zc,
            @RequestParam(required = false) String info_blfy,
            @RequestParam(required = false) String info_yldl,
            @RequestParam(required = false) String info_ywxhzy,
            @RequestParam(required = false) String info_fl,
            @RequestParam(required = false) String info_zxbz,
            @RequestParam(required = false) String info_zlkz,
            @RequestParam (required = false) String comment,
            @RequestParam Integer status) {

        Map<String, Object> map = new HashMap<>();

        data_all_drug dad = new data_all_drug(id,
                info_ym, info_bm, info_cf, info_zffx, info_gnzz, info_zbff, info_jxgg, info_yfyl, info_zlbz, info_syjj,
                info_zysx, info_xdyj, info_lcyy, info_fg, info_qtzj, info_zc, info_blfy, info_yldl, info_ywxhzy, info_fl,
                info_zxbz, info_zlkz, status, comment
        );

        int result=dataAllDrugReposity.updateCommentAndStatus(dad);
        if(result==1){
            map.put("code","200");
        }
        else{
            //更新异常
            map.put("code","400");
        }
        return map;
    }

    @GetMapping(path = "/getPartList")   @ResponseBody
    //@ResponseStatus(code= HttpStatus.SWITCHING_PROTOCOLS,reason = "success")
    public Map<String,Object> getPartList(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam int status,
                                          @RequestParam (name = "name", required = false, defaultValue = "") String name,
                                          @RequestParam (name = "type",required = false,defaultValue = "")String type
    )
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>();

//        int s_x_batch=Integer.parseInt(symptom_xy_batch);
//        int s_z_batch=Integer.parseInt(symptom_zy_batch);

        int yaobw=0;
        int zyybd=0;

        if(!type.equals("")){
            String[] type_list=type.split("/");
            for (int i=0;i<type_list.length;i++) {
                if (type_list[i].equals("yaobw")) {
                    yaobw = 1;
                } else if (type_list[i].equals("zyybd")) {
                    zyybd = 1;
                } else {
                }
            }
        }
//        if(symptom_xy_zgyyxxcxpt==0)s_x_batch=0;
//        if(symptom_zy_zgyyxxcxpt==0)s_z_batch=0;

        PartColumsPatent p=new PartColumsPatent(status,name,yaobw,zyybd);

        map.put("code","200");
        Map<String,Object> map2=dataAllDrugPatentService.getPartList(p,page,size);
        map.putAll(dataAllDrugPatentService.getPartList(p,page,size));

        return map;
    }


}
