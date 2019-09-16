package com.example.demo1.model.patent;

import com.example.demo1.model.disease_and_symptom.data_all;

public class PartColumsPatent {
//    private Integer rest_status;
    private Integer id;
    private String info_ym;
    private Integer status;
    private String comment;

    private Integer origin_yaobw;
    private Integer yaobw_id;
    private Integer origin_zyybd;
    private Integer zyybd_id;


    public PartColumsPatent(){}

    public PartColumsPatent(Integer id, String info_ym, Integer status, String comment) {
        this.id = id;
        this.info_ym = info_ym;
        this.status = status;
        this.comment = comment;
    }

    public PartColumsPatent(Integer status, String info_ym, Integer origin_yaobw, Integer origin_zyybd) {
        this.status = status;
        this.info_ym = info_ym;
        this.origin_yaobw = origin_yaobw;
        this.origin_zyybd = origin_zyybd;
    }

//    public PartColumsPatent(Integer id, String info_mc, Integer status, String comment,
//                      Integer baike, Integer baike_id, Integer baike_auth, Integer disease_xy, Integer disease_xy_id,
//                      Integer disease_zy, Integer disease_zy_id, Integer symptom_xy, Integer symptom_xy_id, Integer symptom_xy_batch,
//                      Integer symptom_zy, Integer symptom_zy_id, Integer symptom_zy_batch, Integer xy, Integer xy_id, Integer zy, Integer zy_id) {
//        this.id = id;
//        this.info_mc = info_mc;
//        this.status = status;
//        this.comment = comment;
//        this.origin_baike=baike;
//        this.baike_id=baike_id;
//        this.baike_auth=baike_auth;
//        this.origin_disease_xy=disease_xy;
//        this.disease_xy_id=disease_xy_id;
//        this.origin_disease_zy=disease_zy;
//        this.disease_zy_id=disease_zy_id;
//        this.origin_symptom_xy=symptom_xy;
//        this.symptom_xy_id=symptom_xy_id;
//        this.symptom_xy_batch=symptom_xy_batch;
//        this.origin_symptom_zy=symptom_zy;
//        this.symptom_zy_id=symptom_zy_id;
//        this.symptom_zy_batch=symptom_zy_batch;
//        this.origin_xy=xy;
//        this.xy_id=xy_id;
//        this.origin_zy=zy;
//        this.zy_id=zy_id;
//    }

    public PartColumsPatent(data_all_drug da){
        this.id=da.getId();
        this.info_ym = da.getInfo_ym();
        this.status = da.getStatus();
        this.comment = da.getComment();
        this.origin_yaobw=da.getOrigin_yaobw();
        this.origin_zyybd=da.getOrigin_zyybd();
        this.yaobw_id=da.getYaobw_id();
        this.zyybd_id=da.getZyybd_id();
    }

//    public PartColumsPatent(Integer id, String info_mc, Integer status, String comment,Integer batch,Integer origin_id,Integer baike,Integer disease_xy,Integer disease_zy,Integer symptom_xy,Integer symptom_zy,Integer xy,Integer zy) {
//        this.id = id;
//        this.info_mc = info_mc;
//        this.status = status;
//        this.comment = comment;
//        this.batch=batch;
//        this.origin_id=origin_id;
//        this.origin_baike=baike;
//        this.origin_disease_zy=disease_xy;
//        this.origin_disease_xy=disease_zy;
//        this.origin_symptom_xy=symptom_xy;
//        this.origin_symptom_zy=symptom_zy;
//        this.origin_xy=xy;
//        this.origin_zy=zy;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo_ym() {
        return info_ym;
    }

    public void setInfo_ym(String info_ym) {
        this.info_ym = info_ym;
    }

    public Integer getOrigin_yaobw() {
        return origin_yaobw;
    }

    public void setOrigin_yaobw(Integer origin_yaobw) {
        this.origin_yaobw = origin_yaobw;
    }

    public Integer getYaobw_id() {
        return yaobw_id;
    }

    public void setYaobw_id(Integer yaobw_id) {
        this.yaobw_id = yaobw_id;
    }

    public Integer getOrigin_zyybd() {
        return origin_zyybd;
    }

    public void setOrigin_zyybd(Integer origin_zyybd) {
        this.origin_zyybd = origin_zyybd;
    }

    public Integer getZyybd_id() {
        return zyybd_id;
    }

    public void setZyybd_id(Integer zyybd_id) {
        this.zyybd_id = zyybd_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}