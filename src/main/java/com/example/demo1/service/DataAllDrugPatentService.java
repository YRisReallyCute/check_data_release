package com.example.demo1.service;

import com.example.demo1.Repository.patent.DataAllDrugRepository;
import com.example.demo1.model.patent.PartColumsPatent;
import com.example.demo1.model.patent.data_all_drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class DataAllDrugPatentService {

    @Autowired
    private DataAllDrugRepository dataAllDrugRepository;

    public Map<String,Object> getPartList(PartColumsPatent partColumsPatent, int page, int size){
        /**root：要查询的类型
         * query：添加查询条件
         * cb：构建条件
         * specification：作为一个匿名内部类
         */
        Specification<data_all_drug> specification=new Specification<data_all_drug>() {
            @Override
            public Predicate toPredicate(Root<data_all_drug> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates=new ArrayList<Predicate>();
                List<Predicate> predicatesAnd=new ArrayList<>();

                /**cb.equal()相当于判断后面两个参数是否一致
                 * root相当于实体类的一个路径，使用get可以获取到我们要的字段
                 *  此处字段类型都是Integer，所以使用as(Integer.class)
                 * 第二个为前台传过来的参数
                 * 这句话相当于 数据库字段“origin_naike”= 传过来的参数
                 */
                if(partColumsPatent.getOrigin_yaobw()==1){
                    predicates.add(criteriaBuilder.equal(root.get("origin_yaobw").as(Integer.class),partColumsPatent.getOrigin_yaobw()));
                }

                if(partColumsPatent.getOrigin_zyybd()==1){
                    predicates.add(criteriaBuilder.equal(root.get("origin_zyybd").as(Integer.class),partColumsPatent.getOrigin_zyybd()));
                }

                if(partColumsPatent.getStatus()!=null){
                    //状态
                    predicatesAnd.add(criteriaBuilder.equal(root.get("status").as(Integer.class),partColumsPatent.getStatus()));
                }

                if(partColumsPatent.getInfo_ym()!=null){
                    //这里相当于数据库字段 name like %前台传过来的值%
                    predicatesAnd.add(criteriaBuilder.like(root.get("info_ym"),"%"+partColumsPatent.getInfo_ym()+"%"));
                }

                //根据id排序
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));

                //组合or查询条件
                Predicate predicateOR=criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));

                //组合and查询条件
                Predicate predicateAND=criteriaBuilder.and(predicatesAnd.toArray(new Predicate[predicatesAnd.size()]));

                //将上面拼接好的条件返回，将作为SQL语句中的where部分【注意是Arry类型】
                return criteriaQuery.where(predicateAND,predicateOR).getRestriction();
            }
        };

        Map<String,Object> map = new LinkedHashMap<>();

        List<data_all_drug> list= dataAllDrugRepository.findAll(specification);
        map.put("totalNum",list.size());

        List<PartColumsPatent> list2=new LinkedList<>();

        for(int i=page*size;(i<page*size+size)&&(i<list.size());i++){
            list2.add(new PartColumsPatent(list.get(i)));
        }

        map.put("currentPageNum",list2.size());
        map.put("list",list2);
        return map;
    }


}
