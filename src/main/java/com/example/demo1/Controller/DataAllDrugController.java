package com.example.demo1.Controller;


import com.example.demo1.Repository.DataAllDrugRepository;
import com.example.demo1.model.data_all_drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/data_all_drug_patent")
@CrossOrigin
public class DataAllDrugController {

    @Autowired
    private DataAllDrugRepository dataAllDrugReposity;

    @GetMapping("/findById")
    public Map<String , Object> findById(@RequestParam Integer id){

        Map<String, Object> map = new HashMap<>();
        Optional<data_all_drug> r = dataAllDrugReposity.findById(id);
        if(r.isPresent()){
            map.put("code", 200);
            map.put("result", r);
        }else{
            map.put("code", 404);
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
            @RequestParam (required = false) String comment,
            @RequestParam Integer status) {

        Map<String, Object> map = new HashMap<>();

        data_all_drug dad = new data_all_drug(id,
                info_ym, info_bm, info_cf, info_zffx, info_gnzz, info_zbff, info_jxgg, info_yfyl, info_zlbz, info_syjj,
                info_zysx, info_xdyj, info_lcyy, info_fg, info_qtzj, info_zc, info_blfy, info_yldl, info_ywxhzy, info_fl,
                info_zxbz, status, comment
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
}
