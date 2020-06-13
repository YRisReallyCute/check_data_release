package com.example.demo1.model.index;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_var_value")
@Data
public class SysVarValue {

    @Id
    @Column(name = "es_key")
    private String key;

    @Column(name = "es_value")
    private String value;

}
