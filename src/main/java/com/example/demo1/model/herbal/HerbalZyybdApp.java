package com.example.demo1.model.herbal;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "data_herbal_zyybd_app")
public class HerbalZyybdApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // id

    @Column private String info_ym;
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
    @Column
    private String info_yxt;
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
    @Column private String origin_url;

    public HerbalZyybdApp(){}

}
