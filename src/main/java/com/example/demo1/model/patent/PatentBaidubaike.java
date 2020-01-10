package com.example.demo1.model.patent;

import lombok.Cleanup;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "data_herbal_zyybd_app")
@Data
@Entity
public class PatentBaidubaike {

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

    @Column
    private int status; //状态

    @Column
    private String comment; // 评论

    @Column()
    private LocalDateTime create_time; // 创建时间

    @Column()
    private LocalDateTime update_time; // 创建时间

    @Lob
    @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
    private String info_qt;
}
