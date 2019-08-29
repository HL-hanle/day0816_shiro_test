package com.qf.controller;


import com.qf.pojo.SysUser;
import com.qf.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SysUserController {
    @Autowired
    private SysUserService userService;

    @RequestMapping(value = "/dealLogin",method = RequestMethod.POST)
    public String login(@RequestParam("loginName") String loginName,
                        @RequestParam("password") String password,
                        @RequestParam("realname") String realname,
                        HttpServletRequest req){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password,realname);
        /**
         * 使用shiro后，这些步骤统一交给shiro处理
         */
        try {
            subject.login(token);
            req.setAttribute("loginName",loginName);
            req.getSession().setAttribute("loginName",loginName);
            if (subject.isAuthenticated()){
                if (realname.equals("会员")){
                    return "redirect:memberView";
                }else if (realname.equals("管理员")){
                    return "redirect:main";
                }else {
                    return "redirect:index";
                }
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "index";
    }
    //进前台页面
    @RequestMapping("/memberView")
    public String memberView(Model model){
        return "member";
    }
    // 当用户权限不足时，访问的页面
    @RequestMapping("/unOauth")
    public String showUnOauth(){
        return "unauth";
    }
    //用户登出时
    @RequestMapping("/logout")
    public String logout(){
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();//清空用户在shiro中的驻留信息
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "redirect:login";
    }
    //
    @RequiresPermissions(value = {"user_edit"})
    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    //在用户管理界面显示所有的用户
    @RequestMapping("loadUser")
    public String loadUser(Model model){
        List<SysUser> userList = userService.loadAll();
        model.addAttribute("userList",userList);
        return "user";
    }

    //根据用户id删除当前用户信息并返回用户显示界面
    @RequestMapping("deleteUser")
    public String deleteUser(int userId){
        boolean b = userService.delUserByUserId(userId);
        return b?"redirect:loadUser":"error";
    }

    //根据当前用户id查询用户所有信息
    @RequestMapping("loadUserByUserId")
    public String loadUserByUserId(int userId,Model model){
        SysUser sysUser = userService.loadUserByUserId(userId);
        model.addAttribute("updateUser",sysUser);
        return "edit";
    }
    //修改用户信息
    @RequestMapping("updateUser")
    public String updateUser(SysUser sysUser){
        boolean b = userService.updateByUser(sysUser);
        return b?"redirect:loadUser":"error";
    }
}
