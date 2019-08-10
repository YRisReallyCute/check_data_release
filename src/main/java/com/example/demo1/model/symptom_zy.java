package com.example.demo1.model;

import com.example.demo1.functions.ReplaceLabels;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "data_symptom_zy_zgyyxxcxpt")
public class symptom_zy {
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
    private LocalDateTime check_data_time;

    public LocalDateTime getCheck_data_time() {
        return check_data_time;
    }

    public void setCheck_data_time(LocalDateTime check_data_time) {
        this.check_data_time = check_data_time;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    @Column(nullable = true)
    private LocalDateTime update_time;

    @Column(nullable = true)
    private Integer batch;

    @Column(nullable = true)
    private Integer status;

    @Column(nullable = true,length = 255)
    private String origin_url;

    @Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String comment;

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

    public void replaceStr(ReplaceLabels replaceLabels){
        String str1_0=replaceLabels.getStr1_0();
        String str1_1=replaceLabels.getStr1_1();
        String str2_0=replaceLabels.getStr2_0();
        String str2_1=replaceLabels.getStr2_1();

        setInfo_mcjs(getInfo_mcjs().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_bm(getInfo_bm().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_bybj(getInfo_bybj().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_bzsz(getInfo_bzsz().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_dfrq(getInfo_dfrq().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_fbbw(getInfo_fbbw().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_fj(getInfo_fj().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_fk(getInfo_fk().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_hl(getInfo_hl().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_jbzd(getInfo_jbzd().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_lcbx(getInfo_lcbx().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_qt(getInfo_qt().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_tnlf(getInfo_tnlf().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_wfwz(getInfo_wfwz().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_yfbj(getInfo_yfbj().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_yh(getInfo_yh().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_yslf(getInfo_yslf().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_ywmc(getInfo_ywmc().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_zjlf(getInfo_zjlf().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));
        setInfo_xybm(getInfo_xybm().replaceAll("<h1>",str1_0).replaceAll("</h1>",str1_1).replaceAll("<h2>",str2_0).replaceAll("</h2>",str2_1));

    }
}
