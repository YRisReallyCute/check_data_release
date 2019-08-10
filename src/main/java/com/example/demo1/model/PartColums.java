package com.example.demo1.model;

public class PartColums {
//    private Integer rest_status;
    private Integer id;
    private String info_mc;
    private Integer status;
    private String comment;

    private Integer origin_baike;
    private Integer baike_id;
    private Integer origin_disease_xy;
    private Integer disease_xy_id;
    private Integer origin_disease_zy;
    private Integer disease_zy_id;
    private Integer origin_symptom_xy;
    private Integer symptom_xy_id;
    private Integer symptom_xy_batch;
    private Integer origin_symptom_zy;
    private Integer symptom_zy_id;
    private Integer symptom_zy_batch;
    private Integer origin_xy;
    private Integer xy_id;
    private Integer origin_zy;
    private Integer zy_id;


    public PartColums(){}

    public PartColums(Integer id, String info_mc, Integer status, String comment) {
        this.id = id;
        this.info_mc = info_mc;
        this.status = status;
        this.comment = comment;
    }

    public PartColums(Integer status,Integer symptom_xy_batch,Integer symptom_zy_batch,String name,Integer origin_baike,Integer origin_disease_xy,Integer origin_disease_zy,
                      Integer origin_symptom_xy,Integer origin_symptom_zy,Integer origin_xy,Integer origin_zy){

        this.status=status;
        this.symptom_xy_batch=symptom_xy_batch;
        this.symptom_zy_batch=symptom_zy_batch;
        this.info_mc=name;
        this.origin_baike=origin_baike;
        this.origin_disease_xy=origin_disease_xy;
        this.origin_disease_zy=origin_disease_zy;
        this.origin_symptom_xy=origin_symptom_xy;
        this.origin_symptom_zy=origin_symptom_zy;
        this.origin_xy=origin_xy;
        this.origin_zy=origin_zy;
    }

    public PartColums(Integer id, String info_mc, Integer status, String comment,
                      Integer baike,Integer baike_id,Integer disease_xy,Integer disease_xy_id,
                      Integer disease_zy,Integer disease_zy_id,Integer symptom_xy,Integer symptom_xy_id,Integer symptom_xy_batch,
                      Integer symptom_zy,Integer symptom_zy_id,Integer symptom_zy_batch,Integer xy,Integer xy_id,Integer zy,Integer zy_id) {
        this.id = id;
        this.info_mc = info_mc;
        this.status = status;
        this.comment = comment;
        this.origin_baike=baike;
        this.baike_id=baike_id;
        this.origin_disease_xy=disease_xy;
        this.disease_xy_id=disease_xy_id;
        this.origin_disease_zy=disease_zy;
        this.disease_zy_id=disease_zy_id;
        this.origin_symptom_xy=symptom_xy;
        this.symptom_xy_id=symptom_xy_id;
        this.symptom_xy_batch=symptom_xy_batch;
        this.origin_symptom_zy=symptom_zy;
        this.symptom_zy_id=symptom_zy_id;
        this.symptom_zy_batch=symptom_zy_batch;
        this.origin_xy=xy;
        this.xy_id=xy_id;
        this.origin_zy=zy;
        this.zy_id=zy_id;
    }

    public PartColums(data_all da){
        this.id=da.getId();
        this.info_mc = da.getInfo_mc();
        this.status = da.getStatus();
        this.comment = da.getComment();
        this.origin_baike=da.getOrigin_baike();
        this.baike_id=da.getBaike_id();
        this.origin_disease_xy=da.getOrigin_disease_xy();
        this.disease_xy_id=da.getDisease_xy_id();
        this.origin_disease_zy=da.getOrigin_disease_zy();
        this.disease_zy_id=da.getDisease_zy_id();
        this.origin_symptom_xy=da.getOrigin_symptom_xy();
        this.symptom_xy_id=da.getSymptom_xy_id();
        this.symptom_xy_batch=da.getSymptom_xy_batch();
        this.origin_symptom_zy=da.getOrigin_symptom_zy();
        this.symptom_zy_id=da.getSymptom_zy_id();
        this.symptom_zy_batch=da.getSymptom_zy_batch();
        this.origin_xy=da.getOrigin_xy();
        this.xy_id=da.getXy_id();
        this.origin_zy=da.getOrigin_zy();
        this.zy_id=da.getZy_id();
    }

//    public PartColums(Integer id, String info_mc, Integer status, String comment,Integer batch,Integer origin_id,Integer baike,Integer disease_xy,Integer disease_zy,Integer symptom_xy,Integer symptom_zy,Integer xy,Integer zy) {
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


    public Integer getOrigin_baike() {
        return origin_baike;
    }

    public void setOrigin_baike(Integer origin_baike) {
        this.origin_baike = origin_baike;
    }

    public Integer getOrigin_disease_xy() {
        return origin_disease_xy;
    }

    public void setOrigin_disease_xy(Integer origin_disease_xy) {
        this.origin_disease_xy = origin_disease_xy;
    }

    public Integer getOrigin_disease_zy() {
        return origin_disease_zy;
    }

    public void setOrigin_disease_zy(Integer origin_disease_zy) {
        this.origin_disease_zy = origin_disease_zy;
    }

    public Integer getOrigin_symptom_xy() {
        return origin_symptom_xy;
    }

    public void setOrigin_symptom_xy(Integer origin_symptom_xy) {
        this.origin_symptom_xy = origin_symptom_xy;
    }

    public Integer getOrigin_symptom_zy() {
        return origin_symptom_zy;
    }

    public void setOrigin_symptom_zy(Integer origin_symptom_zy) {
        this.origin_symptom_zy = origin_symptom_zy;
    }

    public Integer getOrigin_xy() {
        return origin_xy;
    }

    public void setOrigin_xy(Integer origin_xy) {
        this.origin_xy = origin_xy;
    }

    public Integer getOrigin_zy() {
        return origin_zy;
    }

    public void setOrigin_zy(Integer origin_zy) {
        this.origin_zy = origin_zy;
    }

    public Integer getBaike_id() {
        return baike_id;
    }

    public void setBaike_id(Integer baike_id) {
        this.baike_id = baike_id;
    }

    public Integer getDisease_xy_id() {
        return disease_xy_id;
    }

    public void setDisease_xy_id(Integer disease_xy_id) {
        this.disease_xy_id = disease_xy_id;
    }

    public Integer getDisease_zy_id() {
        return disease_zy_id;
    }

    public void setDisease_zy_id(Integer disease_zy_id) {
        this.disease_zy_id = disease_zy_id;
    }

    public Integer getSymptom_xy_id() {
        return symptom_xy_id;
    }

    public void setSymptom_xy_id(Integer symptom_xy_id) {
        this.symptom_xy_id = symptom_xy_id;
    }

    public Integer getSymptom_xy_batch() {
        return symptom_xy_batch;
    }

    public void setSymptom_xy_batch(Integer symptom_xy_batch) {
        this.symptom_xy_batch = symptom_xy_batch;
    }

    public Integer getSymptom_zy_id() {
        return symptom_zy_id;
    }

    public void setSymptom_zy_id(Integer symptom_zy_id) {
        this.symptom_zy_id = symptom_zy_id;
    }

    public Integer getSymptom_zy_batch() {
        return symptom_zy_batch;
    }

    public void setSymptom_zy_batch(Integer symptom_zy_batch) {
        this.symptom_zy_batch = symptom_zy_batch;
    }

    public Integer getXy_id() {
        return xy_id;
    }

    public void setXy_id(Integer xy_id) {
        this.xy_id = xy_id;
    }

    public Integer getZy_id() {
        return zy_id;
    }

    public void setZy_id(Integer zy_id) {
        this.zy_id = zy_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo_mc() {
        return info_mc;
    }

    public void setInfo_mc(String info_mc) {
        this.info_mc = info_mc;
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