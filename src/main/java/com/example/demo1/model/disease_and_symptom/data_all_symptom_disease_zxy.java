package com.example.demo1.model.disease_and_symptom;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="data_all_symptom_disease_zxy")
@Data
public class data_all_symptom_disease_zxy {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String info_mc;

    @Column
    private Integer type;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_mcjs;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "text")
    private String info_bm;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_ywmc;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_fk;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_dfrq;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_fbbw;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_xybm;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_bybj;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_lcbx;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_jbzd;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_bzsz;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_fj;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_zjlf;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_yfbj;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_yslf;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_tnlf;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_wfwz;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_hl;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_yh;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_qt;

    @Column(nullable = true)
    private LocalDateTime create_time;

    @Column(nullable = true)
    private LocalDateTime update_time;

    @Column(nullable = true)
    private LocalDateTime check_data_time;

    @Column(nullable = true)
    private Integer status;

    @Column(nullable = true,length = 255)
    private String origin_url;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String comment;

    @Column(nullable = true)
    private Integer origin_baike;

    @Column(nullable = true)
    private Integer baike_id;

    @Column(nullable = true)
    private Integer baike_auth;

    @Column(nullable = true)
    private Integer origin_disease_zy;

    @Column(nullable = true)
    private Integer disease_zy_id;

    @Column(nullable = true)
    private Integer origin_disease_xy;

    @Column(nullable = true)
    private Integer disease_xy_id;

    @Column(nullable = true)
    private Integer origin_symptom_zy;

    @Column(nullable = true)
    private Integer symptom_zy_id;

    @Column(nullable = true)
    private Integer symptom_zy_batch;

    @Column(nullable = true)
    private Integer origin_symptom_xy;

    @Column(nullable = true)
    private Integer symptom_xy_id;

    @Column(nullable = true)
    private Integer symptom_xy_batch;

    @Column(nullable = true)
    private Integer origin_zy;

    @Column(nullable = true)
    private Integer zy_id;

    @Column(nullable = true)
    private Integer origin_xy;

    @Column(nullable = true)
    private Integer xy_id;

    @Column(nullable = true)
    private Integer origin_human;

    @Column(nullable = true)
    private Integer human_id;

    public data_all_symptom_disease_zxy(){}

    public data_all_symptom_disease_zxy(String info_mc,int type,int status,int origin_baike,int origin_disease_xy,int origin_disease_zy,int origin_symptom_xy,int origin_symptom_zy){
        this.info_mc=info_mc;
        this.type=type;
        this.status=status;
        this.origin_baike=origin_baike;
        this.origin_disease_xy=origin_disease_xy;
        this.origin_disease_zy=origin_disease_zy;
        this.origin_symptom_xy=origin_symptom_xy;
        this.origin_symptom_zy=origin_symptom_zy;
    }

    public data_all_symptom_disease_zxy(Integer id,
                                        Integer type
            , String info_mc
            , String info_mcjs
            , String info_bm
            , String info_ywmc
            , String info_fk
            , String info_dfrq
            , String info_fbbw
            , String info_xybm
            , String info_bybj
            , String info_lcbx
            , String info_jbzd
            , String info_bzsz
            , String info_fj
            , String info_zjlf
            , String info_yfbj
            , String info_yslf
            , String info_tnlf
            , String info_wfwz
            , String info_hl
            , String info_yh
            , String info_qt
            , Integer status, String comment) {
        this.id=id;
        this.type=type;
        this.info_mc=info_mc;
        this.info_mcjs=info_mcjs;
        this.info_bm=info_bm;
        this.info_ywmc=info_ywmc;
        this.info_fk=info_fk;
        this.info_dfrq=info_dfrq;
        this.info_fbbw=info_fbbw;
        this.info_xybm=info_xybm;
        this.info_bybj=info_bybj;
        this.info_lcbx=info_lcbx;
        this.info_jbzd=info_jbzd;
        this.info_bzsz=info_bzsz;
        this.info_fj=info_fj;
        this.info_zjlf=info_zjlf;
        this.info_yfbj=info_yfbj;
        this.info_yslf=info_yslf;
        this.info_tnlf=info_tnlf;
        this.info_wfwz=info_wfwz;
        this.info_hl=info_hl;
        this.info_yh=info_yh;
        this.info_qt=info_qt;
        this.status=status;
        this.comment=comment;
    }

}

