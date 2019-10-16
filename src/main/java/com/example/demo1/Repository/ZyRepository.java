package com.example.demo1.Repository;

import com.example.demo1.model.PartColums;
import com.example.demo1.model.zgyyxxcxpt_zy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ZyRepository extends JpaRepository<zgyyxxcxpt_zy,Integer>, PagingAndSortingRepository<zgyyxxcxpt_zy,Integer> {
//    @Query(value="select new com.example.demo1.model.PartColums(t.id,t.info_mc,t.status,t.comment) from zgyyxxcxpt_zy t")
//    List<PartColums> findall(PageRequest pageRequest);

    @Transactional
    @Query(value = "select count(*) from data_disease_zy_zgyyxxcxpt_old",nativeQuery = true)
    int getNum();


    @Query(value="select new com.example.demo1.model.PartColums(t.id,t.info_mc,t.status,t.comment) from zgyyxxcxpt_zy t where t.info_mc like CONCAT('%',?2,'%')  and t.status=?1")
    List<PartColums> findPartList(int status,String info_mc);

    @Query("select new com.example.demo1.model.PartColums(t.id,t.info_mc,t.status,t.comment) from zgyyxxcxpt_zy t where t.info_mc like CONCAT('%',?2,'%')  and t.status=?1")
    List<PartColums> findPartList(int status,String info_mc,PageRequest pageRequest);

    @Transactional
    @Query(value = "select * from data_zgyyxxcxpt_zy where info_mc like CONCAT('%',?1,'%')",nativeQuery = true)
    List<zgyyxxcxpt_zy> findByName(String name,PageRequest pageRequest);

    @Modifying
    @Transactional
    @Query(value = "update zgyyxxcxpt_zy a set " +
            "a.info_mc = CASE WHEN :#{#u.info_mc} IS NULL THEN a.info_mc ELSE :#{#u.info_mc} END ," +
            "a.info_mcjs = CASE WHEN :#{#u.info_mcjs} IS NULL THEN a.info_mcjs ELSE :#{#u.info_mcjs} END ," +
            "a.info_bm = CASE WHEN :#{#u.info_bm} IS NULL THEN a.info_bm ELSE :#{#u.info_bm} END ," +
            "a.info_ywmc = CASE WHEN :#{#u.info_ywmc} IS NULL THEN a.info_ywmc ELSE :#{#u.info_ywmc} END ," +
            "a.info_fk = CASE WHEN :#{#u.info_fk} IS NULL THEN a.info_fk ELSE :#{#u.info_fk} END ," +
            "a.info_dfrq = CASE WHEN :#{#u.info_dfrq} IS NULL THEN a.info_dfrq ELSE :#{#u.info_dfrq} END ," +
            "a.info_fbbw = CASE WHEN :#{#u.info_fbbw} IS NULL THEN a.info_fbbw ELSE :#{#u.info_fbbw} END ," +
            "a.info_xybm = CASE WHEN :#{#u.info_xybm} IS NULL THEN a.info_xybm ELSE :#{#u.info_xybm} END ," +
            "a.info_bybj = CASE WHEN :#{#u.info_bybj} IS NULL THEN a.info_bybj ELSE :#{#u.info_bybj} END ," +
            "a.info_lcbx = CASE WHEN :#{#u.info_lcbx} IS NULL THEN a.info_lcbx ELSE :#{#u.info_lcbx} END ," +
            "a.info_jbzd = CASE WHEN :#{#u.info_jbzd} IS NULL THEN a.info_jbzd ELSE :#{#u.info_jbzd} END ," +
            "a.info_bzsz = CASE WHEN :#{#u.info_bzsz} IS NULL THEN a.info_bzsz ELSE :#{#u.info_bzsz} END ," +
            "a.info_fj = CASE WHEN :#{#u.info_fj} IS NULL THEN a.info_fj ELSE :#{#u.info_fj} END ," +
            "a.info_zjlf = CASE WHEN :#{#u.info_zjlf} IS NULL THEN a.info_zjlf ELSE :#{#u.info_zjlf} END ," +
            "a.info_yfbj = CASE WHEN :#{#u.info_yfbj} IS NULL THEN a.info_yfbj ELSE :#{#u.info_yfbj} END ," +
            "a.info_yslf = CASE WHEN :#{#u.info_yslf} IS NULL THEN a.info_yslf ELSE :#{#u.info_yslf} END ," +
            "a.info_tnlf = CASE WHEN :#{#u.info_tnlf} IS NULL THEN a.info_tnlf ELSE :#{#u.info_tnlf} END ," +
            "a.info_wfwz = CASE WHEN :#{#u.info_wfwz} IS NULL THEN a.info_wfwz ELSE :#{#u.info_wfwz} END ," +
            "a.info_hl = CASE WHEN :#{#u.info_hl} IS NULL THEN a.info_hl ELSE :#{#u.info_hl} END ," +
            "a.info_yh = CASE WHEN :#{#u.info_yh} IS NULL THEN a.info_yh ELSE :#{#u.info_yh} END ," +
            "a.info_qt = CASE WHEN :#{#u.info_qt} IS NULL THEN a.info_qt ELSE :#{#u.info_qt} END ," +
            "a.create_time = CASE WHEN :#{#u.create_time} IS NULL THEN a.create_time ELSE :#{#u.create_time} END ," +
            "a.update_time = CASE WHEN :#{#u.update_time} IS NULL THEN a.update_time ELSE :#{#u.update_time} END ," +
            "a.status = CASE WHEN :#{#u.status} IS NULL THEN a.status ELSE :#{#u.status} END ," +
            "a.origin_url = CASE WHEN :#{#u.origin_url} IS NULL THEN a.origin_url ELSE :#{#u.origin_url} END ," +
            "a.comment = CASE WHEN :#{#u.comment} IS NULL THEN a.comment ELSE :#{#u.comment} END " +
            "where a.id = :#{#u.id}")
    int updateCommentAndStatus(@Param("u") zgyyxxcxpt_zy u);
}
