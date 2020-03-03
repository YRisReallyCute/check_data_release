package com.example.demo1.repository.herbal;

import com.example.demo1.model.herbal.HerbalDataAll;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;


@Component
public interface HerbalDataAllRepository extends JpaRepository<HerbalDataAll,Integer>, JpaSpecificationExecutor<HerbalDataAll> {

    @Transactional
    @Modifying
    @Query(value = "update data_all_drug_herbal set status=4 where id=?1",nativeQuery = true)
    void deleteById(int id);

    @Transactional
    @Query(value = "select * from data_all_drug_herbal where info_ym like CONCAT('%',?1,'%')",nativeQuery = true)
    List<HerbalDataAll> findByName(String name);

    @Transactional
    @Query(value = "select * from data_all_drug_herbal where info_ym like ?1",nativeQuery = true)
    HerbalDataAll findAllByName(String name);

    @Modifying
    @Transactional
    @Query(value = "update HerbalDataAll a set " +
            "a.info_ym = CASE WHEN :#{#u.info_ym} IS NULL THEN a.info_ym ELSE :#{#u.info_ym} END ," +
            "a.info_bm = CASE WHEN :#{#u.info_bm} IS NULL THEN a.info_bm ELSE :#{#u.info_bm} END ," +
            "a.info_cc = CASE WHEN :#{#u.info_cc} IS NULL THEN a.info_cc ELSE :#{#u.info_cc} END ," +
            "a.info_dx = CASE WHEN :#{#u.info_dx} IS NULL THEN a.info_dx ELSE :#{#u.info_dx} END ," +
            "a.info_gnzz = CASE WHEN :#{#u.info_gnzz} IS NULL THEN a.info_gnzz ELSE :#{#u.info_gnzz} END ," +
            "a.info_ff = CASE WHEN :#{#u.info_ff} IS NULL THEN a.info_ff ELSE :#{#u.info_ff} END ," +
            "a.info_gj = CASE WHEN :#{#u.info_gj} IS NULL THEN a.info_gj ELSE :#{#u.info_gj} END ," +
            "a.info_yfyl = CASE WHEN :#{#u.info_yfyl} IS NULL THEN a.info_yfyl ELSE :#{#u.info_yfyl} END ," +
            "a.info_gjls = CASE WHEN :#{#u.info_gjls} IS NULL THEN a.info_gjls ELSE :#{#u.info_gjls} END ," +
            "a.info_hxcf = CASE WHEN :#{#u.info_hxcf} IS NULL THEN a.info_hxcf ELSE :#{#u.info_hxcf} END ," +
            "a.info_ly = CASE WHEN :#{#u.info_ly} IS NULL THEN a.info_ly ELSE :#{#u.info_ly} END ," +
            "a.info_py = CASE WHEN :#{#u.info_py} IS NULL THEN a.info_py ELSE :#{#u.info_py} END ," +
            "a.info_lcyy = CASE WHEN :#{#u.info_lcyy} IS NULL THEN a.info_lcyy ELSE :#{#u.info_lcyy} END ," +
            "a.info_pz = CASE WHEN :#{#u.info_pz} IS NULL THEN a.info_pz ELSE :#{#u.info_pz} END ," +
            "a.info_sjfb = CASE WHEN :#{#u.info_sjfb} IS NULL THEN a.info_sjfb ELSE :#{#u.info_sjfb} END ," +
            "a.info_zc = CASE WHEN :#{#u.info_zc} IS NULL THEN a.info_zc ELSE :#{#u.info_zc} END ," +
            "a.info_xw = CASE WHEN :#{#u.info_xw} IS NULL THEN a.info_xw ELSE :#{#u.info_xw} END ," +
            "a.info_xz = CASE WHEN :#{#u.info_xz} IS NULL THEN a.info_xz ELSE :#{#u.info_xz} END ," +
            "a.info_ylzy = CASE WHEN :#{#u.info_ylzy} IS NULL THEN a.info_ylzy ELSE :#{#u.info_ylzy} END ," +
            "a.info_ywm = CASE WHEN :#{#u.info_ywm} IS NULL THEN a.info_ywm ELSE :#{#u.info_ywm} END ," +
            "a.info_yxt = CASE WHEN :#{#u.info_yxt} IS NULL THEN a.info_yxt ELSE :#{#u.info_yxt} END ," +
            "a.info_zy = CASE WHEN :#{#u.info_zy} IS NULL THEN a.info_zy ELSE :#{#u.info_zy} END ," +
            "a.info_bz = CASE WHEN :#{#u.info_bz} IS NULL THEN a.info_bz ELSE :#{#u.info_bz} END ," +
            "a.info_jb = CASE WHEN :#{#u.info_jb} IS NULL THEN a.info_jb ELSE :#{#u.info_jb} END ," +
            "a.info_zj = CASE WHEN :#{#u.info_zj} IS NULL THEN a.info_zj ELSE :#{#u.info_zj} END ," +
            "a.info_zp = CASE WHEN :#{#u.info_zp} IS NULL THEN a.info_zp ELSE :#{#u.info_zp} END ," +
            "a.info_qt = CASE WHEN :#{#u.info_qt} IS NULL THEN a.info_qt ELSE :#{#u.info_qt} END ," +
            "a.update_time = CASE WHEN :#{#u.update_time} IS NULL THEN a.update_time ELSE :#{#u.update_time} END ," +
            "a.info_zl = CASE WHEN :#{#u.info_zl} IS NULL THEN a.info_zl ELSE :#{#u.info_zl} END ," +
            "a.status = CASE WHEN :#{#u.status} IS NULL THEN a.status ELSE :#{#u.status} END ," +
            "a.origin_yaobw = CASE WHEN :#{#u.origin_yaobw} IS NULL THEN a.origin_yaobw ELSE :#{#u.origin_yaobw} END ," +
            "a.yaobw_id = CASE WHEN :#{#u.yaobw_id} IS NULL THEN a.yaobw_id ELSE :#{#u.yaobw_id} END ," +
            "a.origin_zyybd = CASE WHEN :#{#u.origin_zyybd} IS NULL THEN a.origin_zyybd ELSE :#{#u.origin_zyybd} END ," +
            "a.zyybd_id = CASE WHEN :#{#u.zyybd_id} IS NULL THEN a.zyybd_id ELSE :#{#u.zyybd_id} END ," +
            "a.comment = CASE WHEN :#{#u.comment} IS NULL THEN a.comment ELSE :#{#u.comment} END " +
            "where a.id = :#{#u.id}")
    int updateCommentAndStatus(@Param("u") HerbalDataAll u);
}
