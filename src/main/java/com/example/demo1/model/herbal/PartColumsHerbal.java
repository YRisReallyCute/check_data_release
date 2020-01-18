package com.example.demo1.model.herbal;

import lombok.Data;

@Data
public class PartColumsHerbal {
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


    public PartColumsHerbal(){}

    public PartColumsHerbal(Integer id, String info_ym, Integer status, String comment) {
        this.id = id;
        this.info_ym = info_ym;
        this.status = status;
        this.comment = comment;
    }

    public PartColumsHerbal(Integer status, String info_ym, Integer origin_yaobw, Integer origin_zyybd, Integer origin_baike) {
        this.status = status;
        this.info_ym = info_ym;
        this.origin_yaobw = origin_yaobw;
        this.origin_zyybd = origin_zyybd;
        this.origin_baike=origin_baike;
    }

    //allController用到
    public PartColumsHerbal(Integer status, String info_ym) {
        this.status = status;
        this.info_ym = info_ym;
    }


    public PartColumsHerbal(HerbalDataAll da){
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

}
