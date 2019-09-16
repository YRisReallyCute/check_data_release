package com.example.demo1.model.patent;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "data_patent_zyybd_app")
public class zyybdApp_patent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // id

    @Column(nullable = false, length = 255)
    private String info_ym; // 药名

    @Column
    private String info_bm; // 别名

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_cf; // 处方

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_zffx; // 组方分析

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_gnzz; // 功能主治

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_zbff; // 制备方法

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_jxgg; // 剂型规格

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_yfyl; // 用法用量

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_zlbz; // 质量标准

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_syjj;  // 使用禁忌

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_zysx; // 注意事项

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_xdyj; // 现代研究

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_lcyy; // 临床应用

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_fg; // 方歌

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_qtzj; // 其他制剂

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_zc; // 贮藏

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_blfy; // 不良反应

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_yldl; // 药理毒理

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_ywxhzy; // 药物相互作用

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_fl; // 辅料

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_zxbz; // 执行标准

    @Column(nullable = false)
    private String origin_url; // 链接

    @Column()
    private LocalDateTime create_time; // 创建时间

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String origin_body;

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_lj;

    public zyybdApp_patent(){

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

    public String getInfo_bm() {
        return info_bm;
    }

    public void setInfo_bm(String info_bm) {
        this.info_bm = info_bm;
    }

    public String getInfo_cf() {
        return info_cf;
    }

    public void setInfo_cf(String info_cf) {
        this.info_cf = info_cf;
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

    public String getInfo_zlbz() {
        return info_zlbz;
    }

    public void setInfo_zlbz(String info_zlbz) {
        this.info_zlbz = info_zlbz;
    }

    public String getInfo_syjj() {
        return info_syjj;
    }

    public void setInfo_syjj(String info_syjj) {
        this.info_syjj = info_syjj;
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

    public String getInfo_fg() {
        return info_fg;
    }

    public void setInfo_fg(String info_fg) {
        this.info_fg = info_fg;
    }

    public String getInfo_qtzj() {
        return info_qtzj;
    }

    public void setInfo_qtzj(String info_qtzj) {
        this.info_qtzj = info_qtzj;
    }

    public String getInfo_zc() {
        return info_zc;
    }

    public void setInfo_zc(String info_zc) {
        this.info_zc = info_zc;
    }

    public String getInfo_blfy() {
        return info_blfy;
    }

    public void setInfo_blfy(String info_blfy) {
        this.info_blfy = info_blfy;
    }

    public String getInfo_yldl() {
        return info_yldl;
    }

    public void setInfo_yldl(String info_yldl) {
        this.info_yldl = info_yldl;
    }

    public String getInfo_ywxhzy() {
        return info_ywxhzy;
    }

    public void setInfo_ywxhzy(String info_ywxhzy) {
        this.info_ywxhzy = info_ywxhzy;
    }

    public String getInfo_fl() {
        return info_fl;
    }

    public void setInfo_fl(String info_fl) {
        this.info_fl = info_fl;
    }

    public String getInfo_zxbz() {
        return info_zxbz;
    }

    public void setInfo_zxbz(String info_zxbz) {
        this.info_zxbz = info_zxbz;
    }

    public String getOrigin_url() {
        return origin_url;
    }

    public void setOrigin_url(String origin_url) {
        this.origin_url = origin_url;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public String getOrigin_body() {
        return origin_body;
    }

    public void setOrigin_body(String origin_body) {
        this.origin_body = origin_body;
    }

    public String getInfo_lj() {
        return info_lj;
    }

    public void setInfo_lj(String info_lj) {
        this.info_lj = info_lj;
    }
}
