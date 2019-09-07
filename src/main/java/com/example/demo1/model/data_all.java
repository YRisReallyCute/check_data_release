package com.example.demo1.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="data_all_symptom_zy")
public class data_all {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 255)
    private String info_mc;

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

    public data_all(){}

    public data_all(Integer id
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

    public data_all(data_all d) {
        this.id=d.getId();
        this.info_mc=d.getInfo_mc();
        this.info_mcjs=d.getInfo_mcjs();
        this.info_bm=d.getInfo_bm();
        this.info_ywmc=d.getInfo_ywmc();
        this.info_fk=d.getInfo_fk();
        this.info_dfrq=d.getInfo_dfrq();
        this.info_fbbw=d.getInfo_fbbw();
        this.info_xybm=d.getInfo_xybm();
        this.info_bybj=d.getInfo_bybj();
        this.info_lcbx=d.getInfo_lcbx();
        this.info_jbzd=d.getInfo_jbzd();
        this.info_bzsz=d.getInfo_bzsz();
        this.info_fj=d.getInfo_fj();
        this.info_zjlf=d.getInfo_zjlf();
        this.info_yfbj=d.getInfo_yfbj();
        this.info_yslf=d.getInfo_yslf();
        this.info_tnlf=d.getInfo_tnlf();
        this.info_wfwz=d.getInfo_wfwz();
        this.info_hl=d.getInfo_hl();
        this.info_yh=d.getInfo_yh();
        this.info_qt=d.getInfo_qt();
        this.status=d.getStatus();
        this.comment=d.getComment();
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

    public String getInfo_mcjs() {
        return info_mcjs;
    }

    public void setInfo_mcjs(String info_mcjs) {
        this.info_mcjs = info_mcjs;
    }

    public String getInfo_bm() {
        return info_bm;
    }

    public void setInfo_bm(String info_bm) {
        this.info_bm = info_bm;
    }

    public String getInfo_ywmc() {
        return info_ywmc;
    }

    public void setInfo_ywmc(String info_ywmc) {
        this.info_ywmc = info_ywmc;
    }

    public String getInfo_fk() {
        return info_fk;
    }

    public void setInfo_fk(String info_fk) {
        this.info_fk = info_fk;
    }

    public String getInfo_dfrq() {
        return info_dfrq;
    }

    public void setInfo_dfrq(String info_dfrq) {
        this.info_dfrq = info_dfrq;
    }

    public String getInfo_fbbw() {
        return info_fbbw;
    }

    public void setInfo_fbbw(String info_fbbw) {
        this.info_fbbw = info_fbbw;
    }

    public String getInfo_xybm() {
        return info_xybm;
    }

    public void setInfo_xybm(String info_xybm) {
        this.info_xybm = info_xybm;
    }

    public String getInfo_bybj() {
        return info_bybj;
    }

    public void setInfo_bybj(String info_bybj) {
        this.info_bybj = info_bybj;
    }

    public String getInfo_lcbx() {
        return info_lcbx;
    }

    public void setInfo_lcbx(String info_lcbx) {
        this.info_lcbx = info_lcbx;
    }

    public String getInfo_jbzd() {
        return info_jbzd;
    }

    public void setInfo_jbzd(String info_jbzd) {
        this.info_jbzd = info_jbzd;
    }

    public String getInfo_bzsz() {
        return info_bzsz;
    }

    public void setInfo_bzsz(String info_bzsz) {
        this.info_bzsz = info_bzsz;
    }

    public String getInfo_fj() {
        return info_fj;
    }

    public void setInfo_fj(String info_fj) {
        this.info_fj = info_fj;
    }

    public String getInfo_zjlf() {
        return info_zjlf;
    }

    public void setInfo_zjlf(String info_zjlf) {
        this.info_zjlf = info_zjlf;
    }

    public String getInfo_yfbj() {
        return info_yfbj;
    }

    public void setInfo_yfbj(String info_yfbj) {
        this.info_yfbj = info_yfbj;
    }

    public String getInfo_yslf() {
        return info_yslf;
    }

    public void setInfo_yslf(String info_yslf) {
        this.info_yslf = info_yslf;
    }

    public String getInfo_tnlf() {
        return info_tnlf;
    }

    public void setInfo_tnlf(String info_tnlf) {
        this.info_tnlf = info_tnlf;
    }

    public String getInfo_wfwz() {
        return info_wfwz;
    }

    public void setInfo_wfwz(String info_wfwz) {
        this.info_wfwz = info_wfwz;
    }

    public String getInfo_hl() {
        return info_hl;
    }

    public void setInfo_hl(String info_hl) {
        this.info_hl = info_hl;
    }

    public String getInfo_yh() {
        return info_yh;
    }

    public void setInfo_yh(String info_yh) {
        this.info_yh = info_yh;
    }

    public String getInfo_qt() {
        return info_qt;
    }

    public void setInfo_qt(String info_qt) {
        this.info_qt = info_qt;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }

    public LocalDateTime getCheck_data_time() {
        return check_data_time;
    }

    public void setCheck_data_time(LocalDateTime check_data_time) {
        this.check_data_time = check_data_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrigin_url() {
        return origin_url;
    }

    public void setOrigin_url(String origin_url) {
        this.origin_url = origin_url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Integer getOrigin_disease_zy() {
        return origin_disease_zy;
    }

    public void setOrigin_disease_zy(Integer origin_disease_zy) {
        this.origin_disease_zy = origin_disease_zy;
    }

    public Integer getDisease_zy_id() {
        return disease_zy_id;
    }

    public void setDisease_zy_id(Integer disease_zy_id) {
        this.disease_zy_id = disease_zy_id;
    }

    public Integer getOrigin_disease_xy() {
        return origin_disease_xy;
    }

    public void setOrigin_disease_xy(Integer origin_disease_xy) {
        this.origin_disease_xy = origin_disease_xy;
    }

    public Integer getDisease_xy_id() {
        return disease_xy_id;
    }

    public void setDisease_xy_id(Integer disease_xy_id) {
        this.disease_xy_id = disease_xy_id;
    }

    public Integer getOrigin_symptom_zy() {
        return origin_symptom_zy;
    }

    public void setOrigin_symptom_zy(Integer origin_symptom_zy) {
        this.origin_symptom_zy = origin_symptom_zy;
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

    public Integer getOrigin_symptom_xy() {
        return origin_symptom_xy;
    }

    public void setOrigin_symptom_xy(Integer origin_symptom_xy) {
        this.origin_symptom_xy = origin_symptom_xy;
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

    public Integer getOrigin_zy() {
        return origin_zy;
    }

    public void setOrigin_zy(Integer origin_zy) {
        this.origin_zy = origin_zy;
    }

    public Integer getZy_id() {
        return zy_id;
    }

    public void setZy_id(Integer zy_id) {
        this.zy_id = zy_id;
    }

    public Integer getOrigin_xy() {
        return origin_xy;
    }

    public void setOrigin_xy(Integer origin_xy) {
        this.origin_xy = origin_xy;
    }

    public Integer getXy_id() {
        return xy_id;
    }

    public void setXy_id(Integer xy_id) {
        this.xy_id = xy_id;
    }

    public Integer getBaike_auth() {
        return baike_auth;
    }

    public void setBaike_auth(Integer baike_auth) {
        this.baike_auth = baike_auth;
    }

    public Integer getOrigin_human() {
        return origin_human;
    }

    public void setOrigin_human(Integer origin_human) {
        this.origin_human = origin_human;
    }

    public Integer getHuman_id() {
        return human_id;
    }

    public void setHuman_id(Integer human_id) {
        this.human_id = human_id;
    }
}
