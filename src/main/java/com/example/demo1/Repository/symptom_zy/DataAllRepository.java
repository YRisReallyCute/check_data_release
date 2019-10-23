package com.example.demo1.Repository.symptom_zy;

import com.example.demo1.model.disease_and_symptom.data_all;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface DataAllRepository extends JpaRepository<data_all,Integer>, PagingAndSortingRepository<data_all,Integer>, JpaSpecificationExecutor<data_all> {
//    @Transactional
//    @Query("select new com.example.demo1.model.PartColums(t.id,t.info_mc,t.status,t.comment,t.origin_baike,t.baike_id,t.origin_disease_xy,t.disease_xy_id,t.origin_disease_zy,t.disease_zy_id,t.origin_symptom_xy,t.symptom_xy_id,t.symptom_xy_batch,t.origin_symptom_zy,t.symptom_zy_id,t.symptom_zy_batch,t.origin_xy,t.xy_id,t.origin_zy,t.zy_id) from data_all t where t.info_mc like CONCAT('%',?2,'%')  and t.status=?1")
//    List<PartColums> findPartList(int status, String info_mc, PageRequest pageRequest);
//
//    @Transactional
//    @Query("select new com.example.demo1.model.PartColums(t.id,t.info_mc,t.status,t.comment,t.origin_baike,t.baike_id,t.origin_disease_xy,t.disease_xy_id,t.origin_disease_zy,t.disease_zy_id,t.origin_symptom_xy,t.symptom_xy_id,t.symptom_xy_batch,t.origin_symptom_zy,t.symptom_zy_id,t.symptom_zy_batch,t.origin_xy,t.xy_id,t.origin_zy,t.zy_id) from data_all t where t.info_mc like CONCAT('%',?2,'%')  and t.status=?1 and t.origin_baike=1")
//    List<PartColums> findPartListBaike(int status, String info_mc, PageRequest pageRequest);
//
//    @Transactional
//    @Query("select new com.example.demo1.model.PartColums(t.id,t.info_mc,t.status,t.comment,t.origin_baike,t.baike_id,t.origin_disease_xy,t.disease_xy_id,t.origin_disease_zy,t.disease_zy_id,t.origin_symptom_xy,t.symptom_xy_id,t.symptom_xy_batch,t.origin_symptom_zy,t.symptom_zy_id,t.symptom_zy_batch,t.origin_xy,t.xy_id,t.origin_zy,t.zy_id) from data_all t where t.info_mc like CONCAT('%',?2,'%')  and t.status=?1 and t.origin_disease_zy=1")
//    List<PartColums> findPartListDiseaseZy(int status, String info_mc,PageRequest pageRequest);
//
//    @Transactional
//    @Query("select new com.example.demo1.model.PartColums(t.id,t.info_mc,t.status,t.comment,t.origin_baike,t.baike_id,t.origin_disease_xy,t.disease_xy_id,t.origin_disease_zy,t.disease_zy_id,t.origin_symptom_xy,t.symptom_xy_id,t.symptom_xy_batch,t.origin_symptom_zy,t.symptom_zy_id,t.symptom_zy_batch,t.origin_xy,t.xy_id,t.origin_zy,t.zy_id) from data_all t where t.info_mc like CONCAT('%',?2,'%')  and t.status=?1 and t.origin_disease_xy=1 ")
//    List<PartColums> findPartListDiseaseXy(int status, String info_mc,PageRequest pageRequest);
//
//    @Query("select new com.example.demo1.model.PartColums(t.id,t.info_mc,t.status,t.comment,t.origin_baike,t.baike_id,t.origin_disease_xy,t.disease_xy_id,t.origin_disease_zy,t.disease_zy_id,t.origin_symptom_xy,t.symptom_xy_id,t.symptom_xy_batch,t.origin_symptom_zy,t.symptom_zy_id,t.symptom_zy_batch,t.origin_xy,t.xy_id,t.origin_zy,t.zy_id) from data_all t where t.info_mc like CONCAT('%',?2,'%')  and t.status=?1 and t.origin_symptom_zy=1 and t.symptom_zy_batch=?3")
//    List<PartColums> findPartListSymptomZy(int status, String info_mc, int batch,PageRequest pageRequest);
//
//    @Query("select new com.example.demo1.model.PartColums(t.id,t.info_mc,t.status,t.comment,t.origin_baike,t.baike_id,t.origin_disease_xy,t.disease_xy_id,t.origin_disease_zy,t.disease_zy_id,t.origin_symptom_xy,t.symptom_xy_id,t.symptom_xy_batch,t.origin_symptom_zy,t.symptom_zy_id,t.symptom_zy_batch,t.origin_xy,t.xy_id,t.origin_zy,t.zy_id) from data_all t where t.info_mc like CONCAT('%',?2,'%')  and t.status=?1 and t.origin_symptom_xy=1 and t.symptom_xy_batch=?3")
//    List<PartColums> findPartListSymptomXy(int status, String info_mc,int batch, PageRequest pageRequest);
//
//    @Query("select new com.example.demo1.model.PartColums(t.id,t.info_mc,t.status,t.comment,t.origin_baike,t.baike_id,t.origin_disease_xy,t.disease_xy_id,t.origin_disease_zy,t.disease_zy_id,t.origin_symptom_xy,t.symptom_xy_id,t.symptom_xy_batch,t.origin_symptom_zy,t.symptom_zy_id,t.symptom_zy_batch,t.origin_xy,t.xy_id,t.origin_zy,t.zy_id) from data_all t where t.info_mc like CONCAT('%',?2,'%')  and t.status=?1 and t.origin_zy=1")
//    List<PartColums> findPartListZy(int status, String info_mc, PageRequest pageRequest);
//
//    @Query("select new com.example.demo1.model.PartColums(t.id,t.info_mc,t.status,t.comment,t.origin_baike,t.baike_id,t.origin_disease_xy,t.disease_xy_id,t.origin_disease_zy,t.disease_zy_id,t.origin_symptom_xy,t.symptom_xy_id,t.symptom_xy_batch,t.origin_symptom_zy,t.symptom_zy_id,t.symptom_zy_batch,t.origin_xy,t.xy_id,t.origin_zy,t.zy_id) from data_all t where t.info_mc like CONCAT('%',?2,'%')  and t.status=?1 and t.origin_xy=1")
//    List<PartColums> findPartListXy(int status, String info_mc, PageRequest pageRequest);

    @Transactional
//    @Query(value = "select * from data_all_symptom_zy where info_mc like CONCAT('%',?1,'%')",nativeQuery = true)
    @Query(value = "select * from data_all_symptom_zy where info_mc like CONCAT(?1)",nativeQuery = true)
    List<data_all> findByName(String name);

    @Transactional
    @Query(value = "select * from data_all_symptom_zy where info_mc like CONCAT('%',?1,'%')",nativeQuery = true)
//    @Query(value = "select * from data_all_symptom_zy where info_mc like CONCAT(?1)",nativeQuery = true)
    List<data_all> findByNameAll(String name);

    @Query(value = "select count(*) from data_all_symptom_zy where origin_baike=1",nativeQuery = true)
    @Transactional
    int baike_num();

    @Query(value = "select count(*) from data_all_symptom_zy where origin_disease_zy=1",nativeQuery = true)
    @Transactional
    int disease_zy_num();

    @Query(value = "select count(*) from data_all_symptom_zy where origin_disease_xy=1",nativeQuery = true)
    @Transactional
    int disease_xy_num();

    @Query(value = "select count(*) from data_all_symptom_zy where origin_symptom_zy=1",nativeQuery = true)
    @Transactional
    int symptom_zy_num();

    @Query(value = "select count(*) from data_all_symptom_zy where origin_symptom_xy=1",nativeQuery = true)
    @Transactional
    int symptom_xy_num();

    @Query(value = "select count(*) from data_all_symptom_zy where origin_zy=1",nativeQuery = true)
    @Transactional
    int zy_num();

    @Query(value = "select count(*) from data_all_symptom_zy where origin_xy=1",nativeQuery = true)
    @Transactional
    int xy_num();

    @Query(value = "select count(*) from data_all_symptom_zy where origin_human=1",nativeQuery = true)
    @Transactional
    int human_num();

    @Modifying
    @Transactional
    @Query(value = "update data_all a set " +
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
            "a.check_data_time = CASE WHEN :#{#u.check_data_time} IS NULL THEN a.check_data_time ELSE :#{#u.check_data_time} END ," +
            "a.status = CASE WHEN :#{#u.status} IS NULL THEN a.status ELSE :#{#u.status} END ," +
            "a.origin_url = CASE WHEN :#{#u.origin_url} IS NULL THEN a.origin_url ELSE :#{#u.origin_url} END ," +
            "a.origin_baike = CASE WHEN :#{#u.origin_baike} IS NULL THEN a.origin_baike ELSE :#{#u.origin_baike} END ," +
            "a.baike_id = CASE WHEN :#{#u.baike_id} IS NULL THEN a.baike_id ELSE :#{#u.baike_id} END ," +
            "a.origin_disease_xy = CASE WHEN :#{#u.origin_disease_xy} IS NULL THEN a.origin_disease_xy ELSE :#{#u.origin_disease_xy} END ," +
            "a.disease_xy_id = CASE WHEN :#{#u.disease_xy_id} IS NULL THEN a.disease_xy_id ELSE :#{#u.disease_xy_id} END ," +
            "a.origin_disease_zy = CASE WHEN :#{#u.origin_disease_zy} IS NULL THEN a.origin_disease_zy ELSE :#{#u.origin_disease_zy} END ," +
            "a.disease_zy_id = CASE WHEN :#{#u.disease_zy_id} IS NULL THEN a.disease_zy_id ELSE :#{#u.disease_zy_id} END ," +
            "a.origin_symptom_xy = CASE WHEN :#{#u.origin_symptom_xy} IS NULL THEN a.origin_symptom_xy ELSE :#{#u.origin_symptom_xy} END ," +
            "a.symptom_xy_id = CASE WHEN :#{#u.symptom_xy_id} IS NULL THEN a.symptom_xy_id ELSE :#{#u.symptom_xy_id} END ," +
            "a.origin_symptom_zy = CASE WHEN :#{#u.origin_symptom_zy} IS NULL THEN a.origin_symptom_zy ELSE :#{#u.origin_symptom_zy} END ," +
            "a.symptom_zy_id = CASE WHEN :#{#u.symptom_zy_id} IS NULL THEN a.symptom_zy_id ELSE :#{#u.symptom_zy_id} END ," +
            "a.symptom_xy_batch = CASE WHEN :#{#u.symptom_xy_batch} IS NULL THEN a.symptom_xy_batch ELSE :#{#u.symptom_xy_batch} END ," +
            "a.symptom_zy_batch = CASE WHEN :#{#u.symptom_zy_batch} IS NULL THEN a.symptom_zy_batch ELSE :#{#u.symptom_zy_batch} END ," +
            "a.origin_xy = CASE WHEN :#{#u.origin_xy} IS NULL THEN a.origin_xy ELSE :#{#u.origin_xy} END ," +
            "a.xy_id = CASE WHEN :#{#u.xy_id} IS NULL THEN a.xy_id ELSE :#{#u.xy_id} END ," +
            "a.origin_zy = CASE WHEN :#{#u.origin_zy} IS NULL THEN a.origin_zy ELSE :#{#u.origin_zy} END ," +
            "a.zy_id = CASE WHEN :#{#u.zy_id} IS NULL THEN a.zy_id ELSE :#{#u.zy_id} END ," +
            "a.comment = CASE WHEN :#{#u.comment} IS NULL THEN a.comment ELSE :#{#u.comment} END " +
            "where a.id = :#{#u.id}")
    int updateCommentAndStatus(@Param("u") data_all u);
}
