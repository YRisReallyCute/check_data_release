package com.example.demo1.model;

import javax.persistence.*;


@Entity
@Table(name = "data_patent_yaobw")
public class yaobw_drug_patent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String info_ym;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_cf;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_fl;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_zffx;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_gnzz;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_zbff;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_jxgg;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_yfyl;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_zlkz;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_zysx;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_xdyj;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_lcyy;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_blfy;
    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_qtzj;
    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_fg;
    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_zc;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_lj;

    @Column
    private String origin_url;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String origin_body;


    public yaobw_drug_patent() {
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

    public String getInfo_cf() {
        return info_cf;
    }

    public void setInfo_cf(String info_cf) {
        this.info_cf = info_cf;
    }

    public String getInfo_fl() {
        return info_fl;
    }

    public void setInfo_fl(String info_fl) {
        this.info_fl = info_fl;
    }

    public String getInfo_zffx() {
        return info_zffx;
    }

    public void setInfo_zffx(String info_zffx) {
        this.info_zffx = info_zffx;
    }

    public String getInfo_gnzz() {
        return info_gnzz;
    }

    public void setInfo_gnzz(String info_gnzz) {
        this.info_gnzz = info_gnzz;
    }

    public String getInfo_zbff() {
        return info_zbff;
    }

    public void setInfo_zbff(String info_zbff) {
        this.info_zbff = info_zbff;
    }

    public String getInfo_jxgg() {
        return info_jxgg;
    }

    public void setInfo_jxgg(String info_jxgg) {
        this.info_jxgg = info_jxgg;
    }

    public String getInfo_yfyl() {
        return info_yfyl;
    }

    public void setInfo_yfyl(String info_yfyl) {
        this.info_yfyl = info_yfyl;
    }

    public String getInfo_zlkz() {
        return info_zlkz;
    }

    public void setInfo_zlkz(String info_zlkz) {
        this.info_zlkz = info_zlkz;
    }

    public String getInfo_zysx() {
        return info_zysx;
    }

    public void setInfo_zysx(String info_zysx) {
        this.info_zysx = info_zysx;
    }

    public String getInfo_xdyj() {
        return info_xdyj;
    }

    public void setInfo_xdyj(String info_xdyj) {
        this.info_xdyj = info_xdyj;
    }

    public String getInfo_lcyy() {
        return info_lcyy;
    }

    public void setInfo_lcyy(String info_lcyy) {
        this.info_lcyy = info_lcyy;
    }

    public String getInfo_blfy() {
        return info_blfy;
    }

    public void setInfo_blfy(String info_blfy) {
        this.info_blfy = info_blfy;
    }

    public String getInfo_qtzj() {
        return info_qtzj;
    }

    public void setInfo_qtzj(String info_qtzj) {
        this.info_qtzj = info_qtzj;
    }

    public String getInfo_fg() {
        return info_fg;
    }

    public void setInfo_fg(String info_fg) {
        this.info_fg = info_fg;
    }

    public String getInfo_zc() {
        return info_zc;
    }

    public void setInfo_zc(String info_zc) {
        this.info_zc = info_zc;
    }

    public String getInfo_lj() {
        return info_lj;
    }

    public void setInfo_lj(String info_lj) {
        this.info_lj = info_lj;
    }

    public String getOrigin_url() {
        return origin_url;
    }

    public void setOrigin_url(String origin_url) {
        this.origin_url = origin_url;
    }

    public String getOrigin_body() {
        return origin_body;
    }

    public void setOrigin_body(String origin_body) {
        this.origin_body = origin_body;
    }


}
