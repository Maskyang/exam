package com.mask.exam.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mask.exam.domain.*;
import com.mask.exam.service.PaperService;
import com.mask.exam.service.TitleService;
import com.mask.exam.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mask on 2019/6/11
 */
@RequestMapping
@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    UserService userService;

    @Autowired
    TitleService titleService;

    @Autowired
    PaperService paperService;

    @RequestMapping("/selectByPrimaryKey")
    @ResponseBody
    public TblUser selectByPrimaryKey(int id) {
        TblUser user = userService.selectByPrimaryKey(id);
        logger.info(user.toString());
        return user;
    }

    //前往登录页面
    @RequestMapping("toLogin")
    public String tologin() {
        return "login";
    }

    //登录
    @RequestMapping("/login")
    public String showlogin(String userName, String userPassword, String userCode, TblUserExample example, HttpServletRequest request, Map<String, Object> map) {
        TblUser user = userService.login(userName);
        if (user == null) {
            return "login";
        } else {

            boolean isUser = user.getUserPassword().equals(userPassword);
            if (isUser) {
                if (userCode.equals(user.getUserCode()) && userCode.equals("m03")) {
                    request.getSession().setAttribute("user", user);
                    return "redirect:/filter/paper";
                } else if (userCode.equals(user.getUserCode()) && userCode.equals("m02")) {
                    request.getSession().setAttribute("user", user);
                    return "redirect:/filter/examManage";
                } else if (!userCode.equals(user.getUserCode())) {
                    map.put("ErrMsg", "请先搞清角色!");
                    return "login";
                }

            } else {
                map.put("ErrMsg", "用户名密码不一致或不存在");
                return "login";
            }
        }
        return "login";
    }


    //修改密码
    @RequestMapping("/filter/toChangePW")
    public String toChangePW(HttpServletRequest request,TblUser sysUser,Map<String, Object> map) {
        sysUser = (TblUser) request.getSession().getAttribute("user");
        System.out.println("修改密码");
        map.put("user",sysUser);

        return "/changePW";
    }


    @RequestMapping("/filter/changePW")
    public String changePW(String userPassword,Map<String, Object> map,String newPassword,String ispw,HttpServletRequest request){
        TblUser sysUser = (TblUser) request.getSession().getAttribute("user");
        if(null==userPassword||null==ispw||null==newPassword){
            map.put("ErrMsg","用户名或密码为空,请重新输入!");
            return "redirect:/toChangePW";

        }else if(sysUser.getUserPassword().equals(userPassword)){
            if(!newPassword.equals(ispw)){
                map.put("ErrMsg","密码不一致,请重新输入!");
                return "redirect:/toChangePW";
            }else{
                sysUser.setUserPassword(newPassword);
                userService.updateByPrimaryKey(sysUser);
                return "login";
            }
        }else{
            map.put("ErrMsg","原密码错误,请重新输入!");
            return "redirect:/toChangePW";
        }
    }


    //退出登录
    @RequestMapping("/logout")
    public String remove(HttpSession session) {
        /* session.removeAttribute("sessionemp");*/
        session.setAttribute("user", null);
        System.out.println("退出登录");

        return "redirect:/login";
    }


    //去查询用户列表
    @RequestMapping("/filter/toList")
    public String toList(HttpServletRequest request){
        TblUser user = (TblUser) request.getSession().getAttribute("user");
        if(user.getUserCode().equals("m02")||user.getUserCode().equals("m01")){
            return "redirect:/filter/userList";

        }else{
            return "error";

        }
    }


    //用户列表加分页查询
    @RequestMapping("/filter/userList")
    public String userList(Map<String, Object> map,Model model,
                                     @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                     @RequestParam(defaultValue="10",value="pageSize")Integer pageSize) {
        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;    //设置默认每页显示的数据数
        }
        logger.info("当前页是："+pageNum+"显示条数是："+pageSize);//1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum,pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<TblUser> userList = userService.selectByExample(null);
            System.out.println("分页数据："+userList);
            map.put("users",userList);

            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<TblUser> pageInfo = new PageInfo<TblUser>(userList,pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //5.设置返回的jsp/html等前端页面
        // thymeleaf默认就会拼串classpath:/templates/xxxx.html
        return "userList";
    }


    @RequestMapping("/filter/add")
    public String add(TblUser TblUser, HttpServletRequest request){
        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");
        if(sessionUser.getUserCode().equals("m02")){
            userService.insert(TblUser);
            return "redirect:/filter/userList";
        }else{
            return "error";
        }
    }


    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
//
//    @RequestMapping("register")
//    public String register(TblUser TblUser, TblUserExample example , Map<String, Object> map, String ispw, HttpServletRequest request){
//        TblUserExample.Criteria criteria = example.createCriteria();
//        criteria.andUserNameEqualTo(TblUser.getUserName());
//        List<TblUser> TblUserList = userService.selectByExample(example);
//        if(null==TblUser.getUserName()||null==TblUser.getUserPassword()){
//            map.put("ErrMsg","用户名或密码为空,请重新输入!");
//            return "redirect:/toRegister";
//
//            }else if(!TblUser.getUserPassword().equals(ispw)){
//            map.put("ErrMsg","密码不一致,请重新输入!");
//            return "register";
//
//        }else if(TblUserList.size()>0){
//                map.put("ErrMsg","用户名已存在!");
//                return "register";
//            }else{
//            map.put("successMsg","用户名注册成功!请登录");
//            userService.insert(TblUser);
//            List<TblUser> TblUserList2 = userService.selectByExample(example);
//            request.getSession().setAttribute("user",TblUserList2.get(0));
//            return "redirect:/filter/faqList";
//        }
//    }


    @RequestMapping(value="/filter/delete")
    @ResponseBody
    public Map<String,String>  delete(Integer id, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");
        TblUser deleteUser = userService.selectByPrimaryKey(id);
        if (sessionUser.getUserCode().equals("m02") && !deleteUser.getUserCode().equals("m02") && !deleteUser.getUserCode().equals("m01")) {
            TblTitleExample titleExample = new TblTitleExample();
            TblTitleExample.Criteria titleExampleCriteria = titleExample.createCriteria();
            titleExampleCriteria.andUserIdEqualTo(id);
            if (titleService.selectByExample(titleExample).size() > 0) {
                titleService.deleteByExample(titleExample);

            }

            TblPaperExample paperExample = new TblPaperExample();
            TblPaperExample.Criteria paperExampleCriteria = paperExample.createCriteria();
            paperExampleCriteria.andUseridEqualTo(id);
            List<TblPaper> paperList = paperService.selectByExample(paperExample);
            if (paperList.size() > 0) {
                paperService.deleteByExample(paperExample);

            }
            int a = userService.deleteByPrimaryKey(id);
            System.out.println(a);
            map.put("result", "提交成功");
        } else {
            map.put("result", "提交失败,无法删除同等级或高等级用户");
        }

        return map;

    }



    @RequestMapping("/filter/edit")
    public String edit(TblUser user, HttpServletRequest request,Map<String, Object> map){
        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");

        if(sessionUser.getUserCode().equals("m02")&&!user.getUserCode().equals("m02")&&!user.getUserCode().equals("m01")){
            userService.updateByPrimaryKey(user);
            return "redirect:/filter/userList";

        }else{
            map.put("ErrMsg","原密码错误,请重新输入!");
            return "error";
        }
    }




}