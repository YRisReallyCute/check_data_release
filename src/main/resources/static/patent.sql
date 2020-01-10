select 
(100000+id) as id,info_ym,info_cf,info_gnzz,info_lcyy,info_syjj,info_zysx
from 
 data_all_drug_patent 
UNION ALL 
select 
 (200000+id) as id,info_ym,info_cf,info_gnzz,info_lcyy,info_syjj,info_zysx
from 
 data_patent_yaobw
UNION ALL
select 
 (300000+id) as id,info_ym,info_cf,info_gnzz,info_lcyy,info_syjj,info_zysx
from 
 data_patent_zyybd_app
UNION ALL
select 
 (400000+id) as id,info_ym,info_cf,info_gnzz,info_lcyy,info_syjj,info_zysx
from 
 data_patent_baidubaike