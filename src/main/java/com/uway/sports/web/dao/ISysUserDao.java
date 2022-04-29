package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.TableEntity;
import com.uway.sports.web.entity.dbentity.SysLog;
import com.uway.sports.web.entity.dbentity.SysMenu;
import com.uway.sports.web.entity.dbentity.SysRole;
import com.uway.sports.web.entity.dbentity.SysUser;
import com.uway.sports.web.entity.paraentity.LogTable;

import java.util.List;

@MyBatisDao
public interface ISysUserDao {
    /**
     * 通过用户id获取用户信息
     * @param userId
     * @return
     */
    SysUser getUserbyId(Integer userId);

    /**
    * 通过用户name获取用户信息
    * @param username
    * @return
    */
    SysUser getUserbyName(String username);

    List<SysMenu> getmenuListBy(SysMenu sysMenu);

    SysRole getRolebyId(Integer roleId);

    List<SysMenu> getmenuList(List<Integer> menuIds);

    /**
     * 获取后台管理系统用户列表
     * @param entity
     * @return
     */
    List<SysUser> getUserPage(TableEntity entity);

    int getUserTotal(TableEntity entity);

    /**
     * 获取所有角色
     * @return
     */
    List<SysRole> getAllRole();

    /**
     * 更新用户
     * @param entity
     * @return
     */
    int updateUser(SysUser entity);

    /**
     * 更新用户
     * @param entity
     * @return
     */
    int addUser(SysUser entity);

    /**修改密码*/
    int updatePasswordById(SysUser user);

    //获取所有菜单
    List<SysMenu> getAllmenuList();

    /**添加角色*/
    int addRole(SysRole role);

    /**修改角色*/
    int editRole(SysRole role);

    /**删除角色*/
    int deleteRoleById(Integer roleId);

    /**查询日志总数*/
    Long getLogTotal(LogTable logTable);
    /**分页查询日志*/
    List<SysLog> getLogList(LogTable logTable);

    //添加用户管理后台的操作日志
    void addsysUserLog(SysLog sysLog);

    /**查询日志总数*/
    Long getsysUserLogTotal(LogTable logTable);
    /**分页查询日志*/
    List<SysLog> getsysUserLogList(LogTable logTable);
    /** 获取菜单所有的分组 */
    List<SysMenu> getMenuGroup();
}
