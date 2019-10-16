package com.example.demo1.Repository.patent;

import com.example.demo1.model.patent.data_all_drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface DataAllDrugRepository extends JpaRepository<data_all_drug, Integer>, JpaSpecificationExecutor<data_all_drug>,CrudRepository<data_all_drug, Integer> {

    @Transactional
    @Query(value = "select * from data_all_drug_patent where info_ym like CONCAT(?1)",nativeQuery = true)
    List<data_all_drug> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "update data_all_drug a set " +
            "a.info_ym = CASE WHEN :#{#u.info_ym} IS NULL THEN a.info_ym ELSE :#{#u.info_ym} END ," +
            "a.info_bm = CASE WHEN :#{#u.info_bm} IS NULL THEN a.info_bm ELSE :#{#u.info_bm} END ," +
            "a.info_cf = CASE WHEN :#{#u.info_cf} IS NULL THEN a.info_cf ELSE :#{#u.info_cf} END ," +
            "a.info_zffx = CASE WHEN :#{#u.info_zffx} IS NULL THEN a.info_zffx ELSE :#{#u.info_zffx} END ," +
            "a.info_gnzz = CASE WHEN :#{#u.info_gnzz} IS NULL THEN a.info_gnzz ELSE :#{#u.info_gnzz} END ," +
            "a.info_zbff = CASE WHEN :#{#u.info_zbff} IS NULL THEN a.info_zbff ELSE :#{#u.info_zbff} END ," +
            "a.info_jxgg = CASE WHEN :#{#u.info_jxgg} IS NULL THEN a.info_jxgg ELSE :#{#u.info_jxgg} END ," +
            "a.info_yfyl = CASE WHEN :#{#u.info_yfyl} IS NULL THEN a.info_yfyl ELSE :#{#u.info_yfyl} END ," +
            "a.info_zlbz = CASE WHEN :#{#u.info_zlbz} IS NULL THEN a.info_zlbz ELSE :#{#u.info_zlbz} END ," +
            "a.info_syjj = CASE WHEN :#{#u.info_syjj} IS NULL THEN a.info_syjj ELSE :#{#u.info_syjj} END ," +
            "a.info_zysx = CASE WHEN :#{#u.info_zysx} IS NULL THEN a.info_zysx ELSE :#{#u.info_zysx} END ," +
            "a.info_xdyj = CASE WHEN :#{#u.info_xdyj} IS NULL THEN a.info_xdyj ELSE :#{#u.info_xdyj} END ," +
            "a.info_lcyy = CASE WHEN :#{#u.info_lcyy} IS NULL THEN a.info_lcyy ELSE :#{#u.info_lcyy} END ," +
            "a.info_fg = CASE WHEN :#{#u.info_fg} IS NULL THEN a.info_fg ELSE :#{#u.info_fg} END ," +
            "a.info_qtzj = CASE WHEN :#{#u.info_qtzj} IS NULL THEN a.info_qtzj ELSE :#{#u.info_qtzj} END ," +
            "a.info_zc = CASE WHEN :#{#u.info_zc} IS NULL THEN a.info_zc ELSE :#{#u.info_zc} END ," +
            "a.info_blfy = CASE WHEN :#{#u.info_blfy} IS NULL THEN a.info_blfy ELSE :#{#u.info_blfy} END ," +
            "a.info_yldl = CASE WHEN :#{#u.info_yldl} IS NULL THEN a.info_yldl ELSE :#{#u.info_yldl} END ," +
            "a.info_ywxhzy = CASE WHEN :#{#u.info_ywxhzy} IS NULL THEN a.info_ywxhzy ELSE :#{#u.info_ywxhzy} END ," +
            "a.info_fl = CASE WHEN :#{#u.info_fl} IS NULL THEN a.info_fl ELSE :#{#u.info_fl} END ," +
            "a.info_zxbz = CASE WHEN :#{#u.info_zxbz} IS NULL THEN a.info_zxbz ELSE :#{#u.info_zxbz} END ," +
            "a.create_time = CASE WHEN :#{#u.create_time} IS NULL THEN a.create_time ELSE :#{#u.create_time} END ," +
            "a.update_time = CASE WHEN :#{#u.update_time} IS NULL THEN a.update_time ELSE :#{#u.update_time} END ," +
            "a.check_data_time = CASE WHEN :#{#u.check_data_time} IS NULL THEN a.check_data_time ELSE :#{#u.check_data_time} END ," +
            "a.status = CASE WHEN :#{#u.status} IS NULL THEN a.status ELSE :#{#u.status} END ," +
            "a.origin_yaobw = CASE WHEN :#{#u.origin_yaobw} IS NULL THEN a.origin_yaobw ELSE :#{#u.origin_yaobw} END ," +
            "a.yaobw_id = CASE WHEN :#{#u.yaobw_id} IS NULL THEN a.yaobw_id ELSE :#{#u.yaobw_id} END ," +
            "a.origin_zyybd = CASE WHEN :#{#u.origin_zyybd} IS NULL THEN a.origin_zyybd ELSE :#{#u.origin_zyybd} END ," +
            "a.zyybd_id = CASE WHEN :#{#u.zyybd_id} IS NULL THEN a.zyybd_id ELSE :#{#u.zyybd_id} END ," +
            "a.comment = CASE WHEN :#{#u.comment} IS NULL THEN a.comment ELSE :#{#u.comment} END " +
            "where a.id = :#{#u.id}")
    int updateCommentAndStatus(@Param("u") data_all_drug u);

}
