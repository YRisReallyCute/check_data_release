//package com.example.demo1.Controller;
//
//import com.example.demo1.Repository.XyRepository;
//import com.example.demo1.functions.ReplaceLabels;
//import com.example.demo1.model.PartColums;
//import com.example.demo1.model.zgyyxxcxpt_xy;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//@RestController
//@RequestMapping(path = "/zgyyxxcxpt_xy")
//@CrossOrigin
//public class XyController {
//    @Autowired
//    private XyRepository xyRepository;
//
//    @Autowired
//    private ReplaceLabels replaceLabels;
//
//    private Integer rest_status;
//
//    @RequestMapping( "/add")
//    @ResponseBody
//    public String addNewData(@RequestParam Integer id
//            ,@RequestParam String info_mc
//            ,@RequestParam String info_mcjs
//            ,@RequestParam String info_bm
//            ,@RequestParam String info_ywmc
//            ,@RequestParam String info_fk
//            ,@RequestParam String info_dfrq
//            ,@RequestParam String info_fbbw
//            ,@RequestParam String info_zybm
//            ,@RequestParam String info_bybj
//            ,@RequestParam String info_lcbx
//            ,@RequestParam String info_jbzd
//            ,@RequestParam String info_bzsz
//            ,@RequestParam String info_fj
//            ,@RequestParam String info_zjlf
//            ,@RequestParam String info_yfbj
//            ,@RequestParam String info_yslf
//            ,@RequestParam String info_tnlf
//            ,@RequestParam String info_wfwz
//            ,@RequestParam String info_hl
//            ,@RequestParam String info_yh
//            ,@RequestParam String info_qt
//            ,@RequestParam LocalDateTime create_time
//            ,@RequestParam LocalDateTime update_time
//            ,@RequestParam Integer status,@RequestParam String origin_url,@RequestParam String comment)
//    {
//        zgyyxxcxpt_xy n = new zgyyxxcxpt_xy(id
//                ,info_mc
//                ,info_mcjs
//                ,info_bm
//                ,info_ywmc
//                ,info_fk
//                ,info_dfrq
//                ,info_fbbw
//                ,info_zybm
//                ,info_bybj
//                ,info_lcbx
//                ,info_jbzd
//                ,info_bzsz
//                ,info_fj
//                ,info_zjlf
//                ,info_yfbj
//                ,info_yslf
//                ,info_tnlf
//                ,info_wfwz
//                ,info_hl
//                ,info_yh
//                ,info_qt
//                ,create_time
//                ,update_time
//                ,status,origin_url,comment);
//        xyRepository.save(n);
//        return "ok!";
//    }
////    @GetMapping(path = "/getPartList1")   @ResponseBody
////    //@ResponseStatus(code= HttpStatus.SWITCHING_PROTOCOLS,reason = "success")
////    public Map<String,Object> getPartList1(@RequestParam int page, @RequestParam int size)
////    {
////        Map<String,Object> map = new LinkedHashMap<String,Object>();
////        Sort sort=new Sort(Sort.DEFAULT_DIRECTION,"id");
////        PageRequest pageRequest = new PageRequest(page,size,sort);
////        List<PartColums> list=xyRepository.findall(pageRequest);
////        map.put("code","200");
////        map.put("resultList",list);
////        return map;
////    }
//
//    /*
//     * 根据状态和名字查找，并返回User的部分列
//     * @status:状态
//     * @name:疾病名称
//     * */
//    @GetMapping(path = "/getPartList")   @ResponseBody
//    //@ResponseStatus(code= HttpStatus.SWITCHING_PROTOCOLS,reason = "success")
//    public Map<String,Object> getPartList(@RequestParam int page,
//                                          @RequestParam int size,
//                                          @RequestParam int status,
//                                          @RequestParam (name = "name", required = false, defaultValue = "")
//                                                  String name
//    )
//    {
//        Map<String,Object> map = new LinkedHashMap<String,Object>();
//        Sort sort=new Sort(Sort.DEFAULT_DIRECTION,"id");
//        PageRequest pageRequest = new PageRequest(page,size,sort);
//        List<PartColums> list=xyRepository.findPartList(status,name,pageRequest);
//        PageInfo pageInfo=new PageInfo(list);
//
//        List<PartColums> list2=xyRepository.findPartList(status,name);
//        int totalNum=list2.size();
//        if (pageInfo.getPageSize()==0){
//            //该页面是空页面
//            map.put("code","404");
//            map.put("totalNum",totalNum);
//        }
//        else{
//            map.put("code","200");
//            map.put("totalNum",totalNum);
//            map.put("resultList",pageInfo);
//        }
//        return map;
//    }
//
//    @GetMapping(path = "/findById")    @ResponseBody
//    //Optional可以避免null exception
//    public Map<String,Object> findById(@RequestParam Integer id)
//    {
//        Map<String,Object> map = new LinkedHashMap<String, Object>();
//        Optional<zgyyxxcxpt_xy> list= xyRepository.findById(id);
//
//        //替换标签<h1>,<h2>
//        list.get().replaceStr(replaceLabels);
//
//        if (!list.isPresent()){
//            map.put("code","404");
//        }
//        else {
//            map.put("code", "200");
//            map.put("result", list);
//        }
//        return map;
//    }
//
//    @GetMapping(path = "/findByName")
//    public Map<String,Object> findByName(@RequestParam String name,
//                                         @RequestParam(required = true,defaultValue = "0") int page,
//                                         @RequestParam(required = true,defaultValue = "10") int size){
//        Map<String,Object> map = new LinkedHashMap<String, Object>();
//        Sort sort=new Sort(Sort.DEFAULT_DIRECTION,"id");
//        PageRequest pageRequest=new PageRequest(page,size,sort);
//        List<zgyyxxcxpt_xy> list= xyRepository.findByName(name,pageRequest);
//        PageInfo pageInfo=new PageInfo(list);
//        if (list.isEmpty()){
//            map.put("code","404");
//        }
//        else {
//            map.put("code", "200");
//            map.put("totalNum",list.size());
//            map.put("result", pageInfo);
//        }
//        return map;
//    }
//
//    @GetMapping(path = "/updateComment")
//    @ResponseBody
//    public Map<String,Object> updateCommentAnfidStatus(@RequestParam Integer id,@RequestParam String comment,@RequestParam int status){
//        zgyyxxcxpt_xy u=new zgyyxxcxpt_xy(id,comment,status);
//        int result=xyRepository.updateCommentAndStatus(u);
//        Map<String,Object> map=new LinkedHashMap<String, Object>();
//
//        if(result==1){
//            map.put("code","200");
//        }
//        else{
//            //更新异常
//            map.put("code","400");
//        }
//        return map;
//    }
//
//    @DeleteMapping(path = "/delete")
//    public void delete(@RequestParam Integer id)
//    {
//        xyRepository.deleteById(id);
//    }
//
//}
