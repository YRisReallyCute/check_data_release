package com.example.demo1.model.herbal;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "data_all_drug_herbal")
public class HerbalDataAll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // id

    @Column
    private String info_ym;
    @Column private String info_bm;
    @Column private String info_cc;
    @Column private String info_dx;
    @Column private String info_ff;
    @Column private String info_gj;
    @Column private String info_gjls;
    @Column private String info_gnzz;
    @Column private String info_hxcf;
    @Column private String info_lcyy;
    @Column private String info_ly;
    @Column private String info_py;
    @Column private String info_pz;
    @Column private String info_sjfb;
    @Column private String info_xw;
    @Column private String info_xz;
    @Column private String info_yfyl;
    @Column private String info_ylzy;
    @Column private String info_ywm;
    @Column private String info_yxt;
    @Column private String info_zc;
    @Column private String info_zl;
    @Column private String info_zp;
    @Column private String info_zy;
    @Column private String info_bz;
    @Column private String info_jb;
    @Column private String info_zj;
    @Column private String info_qt;

    @Column private Date create_time;
    @Column private Date update_time;

    @Column private int status;

    @Column private String comment;

    @Column private int origin_yaobw;
    @Column private int yaobw_id;
    @Column private int origin_zyybd;
    @Column private int zyybd_id;
    @Column private int origin_baike;
    @Column private int baike_id;

    public HerbalDataAll(){}

    public HerbalDataAll(Integer id,String info_ym,String info_bm,String info_cc,String info_dx,String info_ff,
                         String info_gj,String info_gjls,String info_gnzz,String info_hxcf,String info_lcyy,
                         String info_ly,String info_py,String info_pz,String info_sjfb,String info_xw,String info_xz,
                         String info_yfyl,String info_ylzy,String info_ywm,String info_yxt,String info_zc,String info_zl,
                         String info_zp,String info_zy,String info_bz,String info_jb,String info_zj,String info_qt,
                         Integer status, String comment) {
        this.id = id;
        this.info_ym = info_ym;
        this.info_bm = info_bm;
        this.info_cc = info_cc;
        this.info_dx = info_dx;
        this.info_gnzz = info_gnzz;
        this.info_ff = info_ff;
        this.info_gj = info_gj;
        this.info_gjls = info_gjls;
        this.info_gnzz = info_gnzz;
        this.info_hxcf = info_hxcf;
        this.info_lcyy = info_lcyy;
        this.info_ly = info_ly;
        this.info_py = info_py;
        this.info_pz = info_pz;
        this.info_sjfb = info_sjfb;
        this.info_zc = info_zc;
        this.info_xw = info_xw;
        this.info_xz = info_xz;
        this.info_yfyl = info_yfyl;
        this.info_ylzy = info_ylzy;
        this.info_ywm = info_ywm;
        this.info_yxt = info_yxt;
        this.info_zl = info_zl;
        this.info_zp = info_zp;
        this.info_zy = info_zy;
        this.info_bz = info_bz;
        this.info_jb = info_jb;
        this.info_zj = info_zj;
        this.info_qt = info_qt;
        this.status = status;
        this.comment = comment;
    }
}
