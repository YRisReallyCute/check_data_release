package com.example.demo1.model.patent;

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
    private Integer origin_baike;
    private Integer baike_id;
    private double score;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public PartColumsPatent(){}

    public PartColumsPatent(Integer id, String info_ym, Integer status, String comment) {
        this.id = id;
        this.info_ym = info_ym;
        this.status = status;
        this.comment = comment;
    }

    public PartColumsPatent(Integer status, String info_ym, Integer origin_yaobw, Integer origin_zyybd, Integer origin_baike) {
        this.status = status;
        this.info_ym = info_ym;
        this.origin_yaobw = origin_yaobw;
        this.origin_zyybd = origin_zyybd;
        this.origin_baike=origin_baike;
    }

    //allController用到
    public PartColumsPatent(Integer status, String info_ym) {
        this.status = status;
        this.info_ym = info_ym;
    }


    public PartColumsPatent(data_all_drug_patent da){
        this.id=da.getId();
        this.info_ym = da.getInfo_ym();
        this.status = da.getStatus();
        this.comment = da.getComment();
        this.origin_yaobw=da.getOrigin_yaobw();
        this.origin_zyybd=da.getOrigin_zyybd();
        this.origin_baike=da.getOrigin_baike();
        this.yaobw_id=da.getYaobw_id();
        this.zyybd_id=da.getZyybd_id();
        this.baike_id=da.getBaike_id();
    }

    public Integer getOrigin_baike() {
        return origin_baike;
    }

    public void setOrigin_baike(Integer origin_baike) {
        this.origin_baike = origin_baike;
    }

    public Integer getBaike_id() {
        return baike_id;
    }

    public void setBaike_id(Integer baike_id) {
        this.baike_id = baike_id;
    }

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