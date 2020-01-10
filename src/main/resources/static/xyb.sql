SELECT (id+100000)as id,info_mc,info_lcbx,info_jbzd from data_all_symptom_disease_zxy where type=2
UNION ALL
select (id+200000)as id,info_mc,info_lcbx,info_jbzd from data_disease_xy_zgyyxxcxpt
UNION ALL
select (id+300000)as id,info_mc,info_lcbx,info_jbzd from data_symptom_zy_baidubaike where info_mc in 
 (
  SELECT info_mc from data_all_symptom_disease_zxy where type=2 and origin_baike=1
 )