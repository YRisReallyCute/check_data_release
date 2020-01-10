SELECT (id+100000)as id,info_mc,info_lcbx,info_xybm,info_jbzd from data_all_symptom_disease_zxy where type=1
UNION ALL
select (id+200000)as id,info_mc,info_lcbx,info_xybm,info_jbzd from data_disease_zy_zgyyxxcxpt
UNION ALL
select (id+300000)as id,info_mc,info_lcbx,info_xybm,info_jbzd from data_symptom_zy_baidubaike where info_mc in 
 (
  SELECT info_mc from data_all_symptom_disease_zxy where type=1 and origin_baike=1
 )