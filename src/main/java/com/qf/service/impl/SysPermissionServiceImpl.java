package com.qf.service.impl;

import com.qf.mapper.SysPernissionMapper;
import com.qf.pojo.SysPermission;
import com.qf.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPernissionMapper pernissionMapper;
    //获取所有权限信息
    @Override
    public List<SysPermission> loadPermissionAll() {
        List<SysPermission> list = pernissionMapper.loadPermissionAll();
        return list;
    }

}
