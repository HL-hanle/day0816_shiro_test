package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    //首页映射
    @RequestMapping("/indexView")
    public String indexView(){
        return "index";
    }
    //后台页面
    @RequestMapping("/mainView")
    public String mainView(){
        return "main";
    }
    //注册页面映射
    @RequestMapping("/regView")
    public String regView(){
        return "reg";
    }
    //角色增加
    @RequestMapping("/role_addView")
    public String roleAdd(){
        return "role_add";
    }
    //权限页面
    @RequestMapping("/permissionView")
    public String permissionView(){
        return "permission";
    }
    //角色页面模糊查询显示的页面
    @RequestMapping("/rolesView")
    public String rolesView(){
        return "roles";
    }
}
