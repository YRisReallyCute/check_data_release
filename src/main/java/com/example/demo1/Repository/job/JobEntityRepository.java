package com.example.demo1.Repository.job;

import com.example.demo1.model.jobEntity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface JobEntityRepository extends JpaRepository<JobEntity,Integer> {
    JobEntity getById(Integer id);

    JobEntity getByName(String name);

    @Query(value = "select * from data_job_entity where job_group like 'symptom_zy'",nativeQuery = true)
    List<JobEntity> getSymptomZyGroup();

    @Query(value = "select * from data_job_entity where job_group like 'patent'",nativeQuery = true)
    List<JobEntity> getPatentGroup();

    @Query(value = "select * from data_job_entity where job_group like 'herbal'",nativeQuery = true)
    List<JobEntity> getHerbalyGroup();

    @Modifying
    @Transactional
    @Query(value = "update data_job_entity set vm_param = ?2 where id = ?1",nativeQuery = true)
    int updataParam(int id,String conf);

    @Query(value = "select job_group from data_job_entity GROUP BY job_group",nativeQuery = true)
    List<String> getDbName();

}
