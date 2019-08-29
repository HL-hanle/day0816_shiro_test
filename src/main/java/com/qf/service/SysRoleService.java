package com.qf.service;

import com.qf.pojo.SysRole;

import java.util.List;

public interface SysRoleService {
    /**
     * 获取所有角色信息
     * @return 所有角色集合
     */
    public List<SysRole> loadRoleAll();
    /**
     * 增加一个角色信息
     *@param sysRole 一个角色对象
     *@return  返回一个boolean类型
     */
    public boolean addRole(SysRole sysRole);
    /**
     * 获取一个最大的id数
     * @return  返回一个int类型
     */
    public int getRoleMaxId();
    /**
     * 通过role_id删除用户角色关系表中的数据
     * @param role_id   角色id
     * @return  返回boolean类型
     */
    public boolean deleteUser_Role(int role_id);
    /**
     * 通过id删除角色
     * @param role_id   角色id
     * @return  返回boolean类型
     */
    public boolean deleteRole(int role_id);
    /**
     * 批量删除角色
     * @param role_ids  多个角色id
     * @return  返回boolean类型
     */
    public boolean deleteRoles(String role_ids);
    /**
     * 修改角色信息
     * @param sysRole 角色对象
     * @return  返回int类型
     */
    public boolean updateRole(SysRole sysRole);
    /**
     * 通过id获取角色信息
     * @param role_id  角色id
     * @return  SysRole 返回角色对象
     */
    public SysRole loadRoleById(int role_id);
    /**
     * 根据role_name模糊查询
     * @param name  角色名字
     * @return  返回SysRole集合
     */
    public List<SysRole> getRoleByName(String name);
}
