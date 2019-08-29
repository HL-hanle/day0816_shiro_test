package com.qf.service;

import com.qf.pojo.SysPermission;

import java.util.List;

public interface SysPermissionService {
    /**
     * 获取所有权限信息
     * @return  返回值类型为list
     */
    public List<SysPermission> loadPermissionAll();
}
