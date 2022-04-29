package com.uway.sports.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.uway.sports.common.controller.BaseController;
import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.TableEntity;
import com.uway.sports.web.entity.dbentity.SysLog;
import com.uway.sports.web.entity.dbentity.SysMenu;
import com.uway.sports.web.entity.dbentity.SysRole;
import com.uway.sports.web.entity.dbentity.SysUser;
import com.uway.sports.web.entity.paraentity.LogTable;
import com.uway.sports.web.entity.paraentity.MenuGroup;
import com.uway.sports.web.entity.paraentity.TmpUser;
import com.uway.sports.web.service.ISysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class SysUserController extends BaseController {
    @Resource
    private ISysUserService sysUserService;

    /**
     * 跳转到  用户管理页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/userManage")
    public String usermanageAction(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "jsp/userManage";
    }

    @RequestMapping(value = "/roleManage")
    public String rolemanageAction(HttpServletRequest request, HttpServletResponse response, Model model) {
        String treeJson = JSONUtils.toJson(sysUserService.getMenuForTree());
        model.addAttribute("rightList",treeJson);
        return "jsp/roleManage";
    }
    @RequestMapping(value = "/logManage")
    public String logmanageAction(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "jsp/logManage";
    }

    @RequestMapping(value = "/sysUserlogManage")
    public String sysUserlogManage(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "jsp/sysUserlogManage";
    }



    @RequestMapping(value = "/login")
    public String loginAction(HttpServletRequest request, HttpServletResponse response, Model model) {
        String sessionId = request.getSession().getId();
        return "jsp/login";
    }

    /**
     * 用户登录 并返回用户菜单权限
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/userLogin")
    @ResponseBody
    public RestResultResponse<TmpUser> getUser(HttpServletRequest request, HttpServletResponse response){
        TmpUser user = new TmpUser();

        String username=request.getParameter("username");
        String password=request.getParameter("password");

        if(username == null || password == null || "".equals(username) || "".equals(password)){
            return   RestResponseFactory.newFailedResponse("用户名或密码为空！");
        }

        String passMd5 = MiscUtils.MD532(password);
        String sessionId = request.getSession().getId();
        SysUser sysUser = sysUserService.userLogin(username,passMd5);

        if(sysUser == null){
            return   RestResponseFactory.newFailedResponse("登录失败！");
        }else if(sysUser.getStatus() == 0){
            return  RestResponseFactory.newFailedResponse("该账号已无效！");
        }
        user.setUserId(sysUser.getUserId());
        user.setUserName(sysUser.getUserName());
        user.setChName(sysUser.getChName());
        user.setMail(sysUser.getMail());
        user.setPhone(sysUser.getPhone());
        user.setRoleId(sysUser.getRoleId());
        user.setToken(sessionId);
        List<MenuGroup> groupList = new ArrayList<>();

        groupList = sysUserService.getMenugroup(sysUser.getRoleId());

        user.setMenuGroupList(groupList);

        sysUserService.setToken(sessionId,sysUser.getUserId().toString());
        sysUserService.setToken(sysUser.getUserId().toString(),sessionId);

        return  RestResponseFactory.newSuccessfulResponse(user);
    }


    /**
     * 获取用户管理页面 用户列表数据
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/getUserTableData", method = RequestMethod.POST)
    public
    @ResponseBody
    String getUserTableData(HttpServletRequest request, HttpServletResponse response, Model model) {
        TableEntity tableEntity = TableUtils.getTableEntity(request);
        List<SysUser> list = sysUserService.getUserPage(tableEntity);
        int recordsTotal = sysUserService.getUserTotal(tableEntity);

        List<SysRole> sysRoleList = sysUserService.getAllRole();

        List<TmpUser> tmpUserList = new ArrayList<>();

        for (SysUser sysUser:list) {
            TmpUser user = new TmpUser();
            user.setUserId(sysUser.getUserId());
            user.setUserName(sysUser.getUserName());
            user.setChName(sysUser.getChName());
            user.setMail(sysUser.getMail());
            user.setPhone(sysUser.getPhone());
            user.setRoleId(sysUser.getRoleId());
            user.setStatus(sysUser.getStatus());
            user.setCreateTime(sysUser.getCreateTime());

            for (SysRole sr:sysRoleList) {
                if(sr.getRoleId() == sysUser.getRoleId()){
                    user.setRoleName(sr.getRoleName());
                }
            }
            tmpUserList.add(user);
        }



        return TableUtils.getTableJsonString(tmpUserList, recordsTotal, tableEntity.getDraw());
    }

    /**
     * 跳转到 用户编辑页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/userEdit")
    public String userEdit(HttpServletRequest request, HttpServletResponse response, Model model) {
        String userId = request.getParameter("UserId");
        if(userId ==null)
        {  userId ="1";}

        SysUser user = sysUserService.getUserbyId(Integer.parseInt(userId));
        model.addAttribute("userEntity", user);

        List<SysRole> sysRoleList = sysUserService.getAllRole();
        model.addAttribute("sysRoleList", sysRoleList);

        return "jsp/userEdit";
    }

    /**
     * 跳转到 用户编辑页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/userAdd")
    public String userAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<SysRole> sysRoleList = sysUserService.getAllRole();
        model.addAttribute("sysRoleList", sysRoleList);

        return "jsp/userAdd";
    }


    /**
     * 更新用户
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public
    @ResponseBody
    String updateUserInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        SysUser userEntity = new SysUser();
        userEntity.setUserId(Integer.parseInt(request.getParameter("userId")));
        userEntity.setChName(request.getParameter("chName"));
        userEntity.setPhone(request.getParameter("phone"));
        userEntity.setMail(request.getParameter("mail"));
        userEntity.setRoleId(Integer.parseInt(request.getParameter("roleId")));
        userEntity.setStatus(Integer.parseInt(request.getParameter("status")));
        int count = sysUserService.updateUser(userEntity);

        if (count > 0) {
            return "{\"success\":true,\"message\":\"角色修改成功啦\"}";
        } else {
            return "{\"success\":false,\"message\":\"角色修改失败\"}";
        }
    }

    /**
     * 更新用户
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "addUserInfo", method = RequestMethod.POST)
    public
    @ResponseBody
    String addUserInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
        String passMd5 = MiscUtils.MD532("111111");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

        SysUser sysUser = sysUserService.getUserbyName(request.getParameter("userName"));

        if(sysUser != null) {
            return "{\"success\":false,\"message\":\"用户登录名已经存在！\"}";
        }

        SysUser userEntity = new SysUser.Builder()
                .withUserName(request.getParameter("userName"))
                .withPassword(passMd5)
                .withChName(request.getParameter("chName"))
                .withMail(request.getParameter("mail"))
                .withPhone(request.getParameter("phone"))
                .withRoleId(Integer.parseInt(request.getParameter("roleId")))
                .withStatus(Integer.parseInt(request.getParameter("status")))
                .withCreateTime(df.format(new Date()))
                .build();
        sysUserService.addUser(userEntity);

        return "{\"success\":true,\"message\":\"新增角色成功啦\"}";
    }


    @RequestMapping(value = "/updatePasswordByUserId", method = RequestMethod.POST)
    public
    @ResponseBody
    String updatePasswordByUserId(HttpServletRequest request, Model model, HttpServletResponse response) {
        String password = request.getParameter("password");
        String userId = request.getParameter("userId");
        String passMd5 = MiscUtils.MD532(password);

        SysUser user = new SysUser();
        user.setUserId(Integer.parseInt(userId));
        user.setPassword(passMd5);
        int success = sysUserService.updatePasswordById(user);
        if (success == 0) {
            return "";
        }
        return "{\"success\":true,\"message\":\"密码修改成功啦\"}";
    }

    /**
     * 获取所有角色
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "getRoleTableData",method = RequestMethod.POST)
    public @ResponseBody String getRoleTableData(HttpServletRequest request,HttpServletResponse response,Model model){
        TableEntity tableEntity = TableUtils.getTableEntity(request);
        List<SysRole> list = sysUserService.getAllRole();
        int recordsTotal = list.size();
        return TableUtils.getTableJsonString(list, recordsTotal, tableEntity.getDraw());
    }

    @RequestMapping(value = "/allMenu")
    @ResponseBody
    public RestResultResponse<List<SysMenu>> getAllMenu(HttpServletRequest request, HttpServletResponse response){
        List<SysMenu> sysMenuList = new ArrayList<>();

        sysMenuList = sysUserService.getAllmenuList();

        return  RestResponseFactory.newSuccessfulResponse(sysMenuList);
    }

    @RequestMapping(value = "/getRoleById")
    @ResponseBody
    public RestResultResponse<List<MenuGroup>> getRoleById(HttpServletRequest request, HttpServletResponse response){

        String roleidstr =request.getParameter("roleId");
        List<MenuGroup> groupList = new ArrayList<>();
        groupList = sysUserService.getMenugroup(Integer.parseInt(roleidstr));

        return  RestResponseFactory.newSuccessfulResponse(groupList);
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public RestResultResponse<String> addRole(HttpServletRequest request, HttpServletResponse response){

        String rolename =request.getParameter("roleName");
        String rolebak =request.getParameter("roleBak");
        String roledetail =request.getParameter("roleDetail");
        SysRole sysRole = new SysRole.Builder()
               .withRoleName(rolename)
               .withRoleDetail(roledetail)
               .withRoleBak(rolebak)
               .build();
        List<SysRole> roleList = sysUserService.getAllRole();

        for (SysRole role :roleList) {
            if(role.getRoleName().equals(rolename)){
                return  RestResponseFactory.newFailedResponse("角色名称已经存在！");
            }
        }

       Integer role = sysUserService.addRole(sysRole);

        return  RestResponseFactory.newSuccessfulResponse("true");
    }

    @RequestMapping(value = "/editRole", method = RequestMethod.POST)
    @ResponseBody
    public RestResultResponse<String> editRole(HttpServletRequest request, HttpServletResponse response){

        String roleid =request.getParameter("roleId");
        String rolename =request.getParameter("roleName");
        String rolebak =request.getParameter("roleBak");
        String roledetail =request.getParameter("roleDetail");
        SysRole sysRole = new SysRole.Builder()
                .withRoleId(Integer.parseInt(roleid))
                .withRoleName(rolename)
                .withRoleDetail(roledetail)
                .withRoleBak(rolebak)
                .build();

        List<SysRole> roleList = sysUserService.getAllRole();

        for (SysRole role :roleList) {
            if(role.getRoleName().equals(rolename) && role.getRoleId() != Integer.parseInt(roleid)){
                return  RestResponseFactory.newFailedResponse("角色保存失败！");
            }
        }

        Integer role = sysUserService.editRole(sysRole);

        return  RestResponseFactory.newSuccessfulResponse("true");
    }

    /**
     * 删除角色
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "deleteRoleById",method = RequestMethod.POST)
    public @ResponseBody String deleteRoleById(HttpServletRequest request,HttpServletResponse response,Model model){
        String roleid =request.getParameter("roleId");
        sysUserService.deleteRoleById(Integer.parseInt(roleid));
        return "{\"success\":true,\"message\":\"角色删除成功啦\"}";
    }

    /**
     * 日志查询
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "getLogTable",method = RequestMethod.POST)
    public @ResponseBody String getLogTable(HttpServletRequest request,HttpServletResponse response,Model model){
        LogTable logTable = new LogTable();
        try{
            logTable = JSON.parseObject(request.getParameter("condition"),LogTable.class);
        }catch (JSONException e) {
            return JSON.toJSONString(RestResponseFactory.newFailedResponse("输入的条件错误"));
        }
        TableUtils.getTableEntityForLog(request, logTable);
        Long Count = sysUserService.getLogTotal(logTable);
        List<SysLog> logList;
        if(Count>0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            logList  = sysUserService.getLogList(logTable);
            for (SysLog log:logList) {
                if (log.getOperateTime()!=null){
                    log.setOperateTimeStr(sdf.format(log.getOperateTime()));
                }
            }
        }else {
            return TableUtils.getTableJsonString("[]", Count.intValue(), logTable.getDraw());
        }
        String retData = TableUtils.getTableJsonString(logList, Count.intValue(), logTable.getDraw());
        return retData;
        }

    /**
     * 日志查询
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "getsysUserLogTable",method = RequestMethod.POST)
    public @ResponseBody String getsysUserLogTable(HttpServletRequest request,HttpServletResponse response,Model model){
        LogTable logTable = new LogTable();
        try{
            logTable = JSON.parseObject(request.getParameter("condition"),LogTable.class);
        }catch (JSONException e) {
            return JSON.toJSONString(RestResponseFactory.newFailedResponse("输入的条件错误"));
        }
        TableUtils.getTableEntityForLog(request, logTable);
        Long Count = sysUserService.getsysUserLogTotal(logTable);
        List<SysLog> logList;
        if(Count>0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            logList  = sysUserService.getsysUserLogList(logTable);
            for (SysLog log:logList) {
                if (log.getOperateTime()!=null){
                    log.setOperateTimeStr(sdf.format(log.getOperateTime()));
                }
            }
        }else {
            return TableUtils.getTableJsonString("[]", Count.intValue(), logTable.getDraw());
        }
        String retData = TableUtils.getTableJsonString(logList, Count.intValue(), logTable.getDraw());
        return retData;
    }

    /**
     * 用户上传视频获取签名
     * @return
     */
    @RequestMapping(value = "getSignature",method = RequestMethod.POST)
    public @ResponseBody RestResultResponse getSignature(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        String seesionId = request.getSession().getId();
        String userid = sysUserService.getToken(seesionId);

        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }

        String sign = sysUserService.getSignature(Integer.parseInt(userid),66,1);
        return RestResponseFactory.newSuccessfulResponse(sign);
    }

}
