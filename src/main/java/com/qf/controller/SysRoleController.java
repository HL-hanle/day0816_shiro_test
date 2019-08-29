package com.qf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.SysRole;
import com.qf.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SysRoleController {
    @Autowired
    private SysRoleService roleService;
    //获取所有角色信息
    @RequestMapping("/loadRoleAll")
    public String loadRoleAll(Model model,
                              @RequestParam(defaultValue = "1",value = "page")Integer page){
        PageHelper.startPage(page,2);
        List<SysRole> roleList = roleService.loadRoleAll();
        PageInfo<SysRole> pageInfo = new PageInfo<>(roleList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("roleList",roleList);
        return "role";
    }
    //增加一个角色信息
    @RequestMapping("/addRole")
    public String addRole(SysRole sysRole){
        sysRole.setRole_id(roleService.getRoleMaxId()+1);
        boolean b = roleService.addRole(sysRole);
        return b?"redirect:loadRoleAll":"error";
    }
    //通过id删除角色信息
    @RequestMapping("/deleteRole")
    public String deleteRole(int role_id){
        roleService.deleteUser_Role(role_id);
        boolean b = roleService.deleteRole(role_id);
        return b?"redirect:loadRoleAll":"error";
    }
    //批量删除角色
    @ResponseBody
    @RequestMapping("/deleteRoles")
    public boolean deleteRoles(String role_ids){
        //System.out.println(role_ids);
        boolean bool=false;
        String[] ids = role_ids.split(",");
        //System.out.println(ids);
        for (int i = 0; i < ids.length; i++) {
            if (!ids[i].equals("on")){
                roleService.deleteUser_Role(Integer.parseInt(ids[i]));
                //System.out.println(ids[i]);
                bool = roleService.deleteRole(Integer.parseInt(ids[i]));
                //System.out.println(bool);
            }
        }
        return bool;
    }
    //用Id获取角色信息
    @RequestMapping("/loadRoleById")
    public String loadRoleById(int role_id,Model model){
        SysRole role=roleService.loadRoleById(role_id);
        model.addAttribute("updateRole",role);
        return "role_edit";
    }
    //修改角色
    @RequestMapping("/updateRole")
    public String updateRole(SysRole sysRole){
        boolean b = roleService.updateRole(sysRole);
        return b?"redirect:loadRoleAll":"error";
    }
    //根据名字模糊查询
    @RequestMapping("/getRoleByName")
    public String getRoleByName(String name,Model model){
        List<SysRole> roleList = roleService.getRoleByName(name);
        if (!name.equals(null)) {
            model.addAttribute("roleList",roleList);
        }else {
            model.addAttribute("roleList",null);
        }
        return "roles";
    }
}
