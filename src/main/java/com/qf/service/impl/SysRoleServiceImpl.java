package com.qf.service.impl;

import com.qf.mapper.SysRoleMapper;
import com.qf.pojo.SysRole;
import com.qf.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;
    //获取所有角色信息
    @Override
    public List<SysRole> loadRoleAll() {
        List<SysRole> roleList = roleMapper.loadRoleAll();
        return roleList;
    }
    //增加一个角色信息
    @Override
    public boolean addRole(SysRole sysRole) {
        int i = roleMapper.addRole(sysRole);
        return i>0?true:false;
    }
    //获取一个最大id数
    @Override
    public int getRoleMaxId() {
        int i = roleMapper.getRoleMaxId();
        return i;
    }
    //通过role_id删除用户角色关系表中的数据
    @Override
    public boolean deleteUser_Role(int role_id) {
        int i = roleMapper.deleteUser_Role(role_id);
        return i>0?true:false;
    }
    //通过id删除单个角色信息
    @Override
    public boolean deleteRole(int role_id) {
        int i = roleMapper.deleteRole(role_id);
        return i>0?true:false;
    }
    //批量删除角色
    @Override
    public boolean deleteRoles(String role_ids) {
        int i = roleMapper.deleteRoles(role_ids);
        return i>0?true:false;
    }

    //修改角色
    @Override
    public boolean updateRole(SysRole sysRole) {
        int i = roleMapper.updateRole(sysRole);
        return i>0?true:false;
    }
    //通过id获取角色信息
    @Override
    public SysRole loadRoleById(int role_id) {
        SysRole sysRole = roleMapper.loadRoleById(role_id);
        return sysRole;
    }
    //根据name模糊查询
    @Override
    public List<SysRole> getRoleByName(String name) {
        ArrayList<SysRole> list = new ArrayList<>();
        List<SysRole> name1 = roleMapper.getRoleByName(name);
        if (name1 != null) {
            for (SysRole role : name1) {
                list.add(role);
            }
        }
        return list;
    }


}
