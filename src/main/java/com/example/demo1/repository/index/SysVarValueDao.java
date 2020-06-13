package com.example.demo1.repository.index;


import com.example.demo1.model.index.SysVarValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SysVarValueDao extends JpaRepository<SysVarValue, String>, CrudRepository<SysVarValue, String> {
}
