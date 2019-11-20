//package com.example.demo1.service;
//
//
//import com.example.demo1.Repository.symptom_zy.DataAllRepository;
//import com.example.demo1.model.PartColums;
//import com.example.demo1.model.disease_and_symptom.User;
//import com.example.demo1.model.disease_and_symptom.data_all;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.*;
//
//@Service
//public class DataAllService {
//
//    @Autowired
//    private DataAllRepository dataAllRepository;
//
//    public Map<String,Object> getPartList(PartColums partColums, int page, int size){
//        /**root：要查询的类型
//         * query：添加查询条件
//         * cb：构建条件
//         * specification：作为一个匿名内部类
//         */
//        Specification<data_all> specification=new Specification<data_all>() {
//            @Override
//            public Predicate toPredicate(Root<data_all> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//
//                List<Predicate> predicates=new ArrayList<Predicate>();
//                List<Predicate> predicatesAnd=new ArrayList<>();
//
//                /**cb.equal()相当于判断后面两个参数是否一致
//                 * root相当于实体类的一个路径，使用get可以获取到我们要的字段
//                 *  此处字段类型都是Integer，所以使用as(Integer.class)
//                 * 第二个为前台传过来的参数
//                 * 这句话相当于 数据库字段“origin_naike”= 传过来的参数
//                 */
//                if(partColums.getOrigin_baike()==1){
//                    predicates.add(criteriaBuilder.equal(root.get("origin_baike").as(Integer.class),partColums.getOrigin_baike()));
//                }
//
//                if(partColums.getOrigin_disease_xy()==1){
//                    predicates.add(criteriaBuilder.equal(root.get("origin_disease_xy").as(Integer.class),partColums.getOrigin_disease_xy()));
//                }
//
//                if(partColums.getOrigin_disease_zy()==1){
//                    predicates.add(criteriaBuilder.equal(root.get("origin_disease_zy").as(Integer.class),partColums.getOrigin_disease_zy()));
//                }
//
//                if(partColums.getOrigin_symptom_xy()==1){
//                    predicates.add(criteriaBuilder.equal(root.get("origin_symptom_xy").as(Integer.class),partColums.getOrigin_symptom_xy()));
//                    if(partColums.getSymptom_xy_batch()!=0){
//                        predicatesAnd.add(criteriaBuilder.equal(root.get("symptom_xy_batch").as(Integer.class),partColums.getSymptom_xy_batch()));
//                    }
//                }
//
//                if(partColums.getOrigin_symptom_zy()==1){
//                    predicates.add(criteriaBuilder.equal(root.get("origin_symptom_zy").as(Integer.class),partColums.getOrigin_symptom_zy()));
//                    if(partColums.getSymptom_zy_batch()!=0){
//                        predicatesAnd.add(criteriaBuilder.equal(root.get("symptom_zy_batch").as(Integer.class),partColums.getSymptom_zy_batch()));
//                    }
//                }
//
//                if(partColums.getOrigin_xy()==1){
//                    predicates.add(criteriaBuilder.equal(root.get("origin_xy").as(Integer.class),partColums.getOrigin_xy()));
//                }
//
//                if(partColums.getOrigin_zy()==1){
//                    predicates.add(criteriaBuilder.equal(root.get("origin_zy").as(Integer.class),partColums.getOrigin_zy()));
//                }
//
//                if(partColums.getStatus()!=null){
//                    //状态
//                    predicatesAnd.add(criteriaBuilder.equal(root.get("status").as(Integer.class),partColums.getStatus()));
//                }
//
//                if(partColums.getInfo_mc()!=null){
//                    //这里相当于数据库字段 name like %前台传过来的值%
//                    predicatesAnd.add(criteriaBuilder.like(root.get("info_mc"),"%"+partColums.getInfo_mc()+"%"));
//                }
//
//
//                //根据id倒序排序
//                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
//
//                //组合or查询条件
//                Predicate predicateOR=criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
//
//                //组合and查询条件
//                Predicate predicateAND=criteriaBuilder.and(predicatesAnd.toArray(new Predicate[predicatesAnd.size()]));
//
//                //将上面拼接好的条件返回，将作为SQL语句中的where部分【注意是Arry类型】
//                return criteriaQuery.where(predicateAND,predicateOR).getRestriction();
//            }
//        };
//
//        Map<String,Object> map = new LinkedHashMap<>();
//
//        List<data_all> list= dataAllRepository.findAll(specification);
//        map.put("totalNum",list.size());
//
//        List<PartColums> list2=new LinkedList<>();
//
//        for(int i=page*size;(i<page*size+size)&&(i<list.size());i++){
//            list2.add(new PartColums(list.get(i)));
//        }
//
//        map.put("currentPageNum",list2.size());
//        map.put("list",list2);
//        return map;
//    }
//
//
//}
