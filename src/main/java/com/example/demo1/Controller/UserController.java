//package com.example.demo1.Controller;
//
//import com.example.demo1.Repository.UserRepository;
//import com.example.demo1.functions.ReplaceLabels;
//import com.example.demo1.model.PartColums;
//import com.example.demo1.model.User;
//import com.example.demo1.service.UserServiceImpl.UserServiceImpl;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.JpaContext;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//@RestController
//@RequestMapping(path = "/baidu_baike")
//@CrossOrigin
//public class UserController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    @Autowired
//    private ReplaceLabels replaceLabels;
//
//    private Integer rest_status;
//
//    @RequestMapping( "/add")
//    @ResponseBody
//    public String addNewUser(@RequestParam Integer id
//            ,@RequestParam String info_mc
//            ,@RequestParam String info_mcjs
//            ,@RequestParam String info_bm
//            ,@RequestParam String info_ywmc
//            ,@RequestParam String info_fk
//            ,@RequestParam String info_dfrq
//            ,@RequestParam String info_fbbw
//            ,@RequestParam String info_xybm
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
//        User n = new User(id
//                ,info_mc
//                ,info_mcjs
//                ,info_bm
//                ,info_ywmc
//                ,info_fk
//                ,info_dfrq
//                ,info_fbbw
//                ,info_xybm
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
//        userRepository.save(n);
//        return "ok!";
//    }
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
//                                                      String name
//    )
//    {
//        PageHelper.startPage(page,size);
//        Map<String,Object> map = new LinkedHashMap<String,Object>();
//        Sort sort=new Sort(Sort.DEFAULT_DIRECTION,"id");
//        PageRequest pageRequest = new PageRequest(page,size,sort);
////        name='%'+name+'%';
//        List<PartColums> list=userRepository.findPartList(status,name,pageRequest);
//        PageInfo pageInfo=new PageInfo(list);
//        List<PartColums> list2=userRepository.findPartList(status,name);
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
//        Optional<User> list= userRepository.findById(id);
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
//        List<User> list= userRepository.findByName(name,pageRequest);
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
//    @GetMapping(path = "/getUserList")
//    @ResponseBody
//    public Page<User> getList(@RequestParam int page,@RequestParam int size){
//        return userService.getUserList(page,size);
//    }
//
//    @GetMapping(path = "/updateComment")
//    @ResponseBody
//    public Map<String,Object> updateCommentAnfidStatus(@RequestParam Integer id,@RequestParam String comment,@RequestParam int status){
//        User u=new User(id,comment,status);
//        int result=userRepository.updateCommentAndStatus(u);
//        Map<String,Object> map=new LinkedHashMap<String, Object>();
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
//        userRepository.deleteById(id);
//    }
//
//}
