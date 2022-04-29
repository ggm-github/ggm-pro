package com.uway.sports.web.service;

import com.uway.sports.common.service.IBaseService;
import com.uway.sports.web.entity.TableEntity;
import com.uway.sports.web.entity.dbentity.SysLog;
import com.uway.sports.web.entity.dbentity.SysMenu;
import com.uway.sports.web.entity.dbentity.SysRole;
import com.uway.sports.web.entity.dbentity.SysUser;
import com.uway.sports.web.entity.paraentity.JsTreeEntity;
import com.uway.sports.web.entity.paraentity.LogTable;
import com.uway.sports.web.entity.paraentity.MenuGroup;

import java.util.List;

public interface ISysUserService extends IBaseService {
    //通过name获取用户信息
    SysUser getUserbyName(String username);

    //通过id获取用户信息
    SysUser getUserbyId(Integer userId);

    //用户登录获取用户信息
    SysUser userLogin(String username,String password);

    /** 判断用户该权限是否存在 */
    boolean hasRight(String url,Integer userId);

    List<JsTreeEntity> getMenuForTree();

    /**
     * 通过角色id获取用户菜单
     * @param roleid
     * @return
     */
    List<MenuGroup> getMenugroup(Integer roleid);

    void setToken(String userid ,String token);

    String getToken(String key);

    List<SysMenu> getmenuList(List<Integer> menuIds);

    /**
     * 获取后台管理系统用户列表
     * @param entity
     * @return
     */
    List<SysUser> getUserPage(TableEntity entity);

    /**
     * 获取后台管理系统用户总数
     * @param entity
     * @return
     */
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

    /**
     * 获取上传视频签名
     * @return
     */
    String getSignature(Integer userId,Integer videoType,Integer transcoding);

}
