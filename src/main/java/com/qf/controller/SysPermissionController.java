package com.qf.controller;

import com.qf.pojo.SysPermission;
import com.qf.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SysPermissionController {
    @Autowired
    private SysPermissionService permissionService;
    //获取所有权限信息
    @ResponseBody
    @RequestMapping("/loadPermissionAll")
    public List<SysPermission> loadPermissionAll(){
        List<SysPermission> list = permissionService.loadPermissionAll();
        return list;
    }
}
