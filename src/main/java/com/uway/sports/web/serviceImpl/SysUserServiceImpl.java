package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.config.Global;
import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.common.utils.Signature;
import com.uway.sports.common.utils.StringUtils;
import com.uway.sports.web.dao.ISysUserDao;
import com.uway.sports.web.entity.TableEntity;
import com.uway.sports.web.entity.dbentity.SysLog;
import com.uway.sports.web.entity.dbentity.SysMenu;
import com.uway.sports.web.entity.dbentity.SysRole;
import com.uway.sports.web.entity.dbentity.SysUser;
import com.uway.sports.web.entity.paraentity.JsTreeEntity;
import com.uway.sports.web.entity.paraentity.LogTable;
import com.uway.sports.web.entity.paraentity.MenuGroup;
import com.uway.sports.web.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("sysUserServiceImpl")
@Transactional
public class SysUserServiceImpl extends BaseServiceImpl implements ISysUserService {
    public static final String USER_TOKEN_REDIS = "uway_user_token:";
    public static final String USER_VIDEO_SIGN = "web_usersignature:";
    @Autowired
    private ISysUserDao iSysUserDao;

    @Autowired
    private SharedRedisClient sharedRedisClient ;

    @Override
    public SysUser getUserbyName(String username) {
        SysUser user = null;
        user = iSysUserDao.getUserbyName(username);
        return user;
    }


    //通过id获取用户信息
    @Override
    public  SysUser getUserbyId(Integer userId){
        SysUser user = null;
        user = iSysUserDao.getUserbyId(userId);
        return user;
    }

    //用户登录获取用户信息
    @Override
   public SysUser userLogin(String username,String password){
        SysUser user  = getUserbyName(username);

        if(user!=null && password.equals(user.getPassword())){
            return user;
        }

        return null;
   }

    @Override
    public boolean hasRight(String url, Integer userId) {
        SysUser user = iSysUserDao.getUserbyId(userId);
        SysRole role = iSysUserDao.getRolebyId(user.getRoleId());
        String roleDetail = role.getRoleDetail();
        if (StringUtils.isBlank(roleDetail)){
            return false;
        }
        String[] menuIds = roleDetail.split(",");
        List<String> menuIdList = Arrays.asList(menuIds);
        SysMenu menu = new SysMenu();
        menu.setMStatus(1);
        menu.setMPath(url);
        List<SysMenu> menuList = iSysUserDao.getmenuListBy(menu);
        for (int i=0;i<menuList.size();i++){
            String menuId = menuList.get(i).getMId().toString();
            if (menuIdList.contains(menuId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<JsTreeEntity> getMenuForTree() {
        // 权限树结果集
        List<JsTreeEntity> tree = new ArrayList<>();
        // 查询所有的菜单分组
        List<SysMenu> menuGroup = iSysUserDao.getMenuGroup();
        if(menuGroup == null || menuGroup.size() == 0){
            return tree;
        }
        // 将定义的大类放入
        for(int i=0;i<menuGroup.size();i++){
            JsTreeEntity note = new JsTreeEntity("sys" + menuGroup.get(i).getMGroupid(),menuGroup.get(i).getMGroup());
            note.getState().put("disabled",true);
            if(i > 0){
                note.getState().put("opened",false);
            }else{
                note.getState().put("opened",true);
            }
            tree.add(note);
        }
        // 查询出所有的菜单信息
        // （注：菜单表中数据排序是按照m_sort升序排列，子节点的排序要在父节点之后，否自展示有可能异常）
        List<SysMenu> menuList = iSysUserDao.getAllmenuList();
        // id记录map（key为权限id，value为权限对象在结果集中的下标，方便取值）
        Map<Integer,Integer> idMap = new HashMap<>();
        // 处理权限节点
        SysMenu menu;
        Integer parentId;
        Integer id;
        String text;
        Integer groupId;
        JsTreeEntity treeFirstNote;
        for(int i=0;i<menuList.size();i++){
            menu = menuList.get(i);
            groupId = menu.getMGroupid();
            id = menu.getMId();
            parentId = menu.getMParentId();
            text = menu.getMName();
            treeFirstNote = tree.get(groupId - 1);
            // 若存在上级，将此权限 放入对应的父级节点 的子集中
            if (parentId != null && parentId != 0 && idMap.containsKey(parentId)){
                treeFirstNote.getChildren().get(idMap.get(parentId)).getChildren().add(new JsTreeEntity(id.toString(),text));
            }else{
                // 若不存在，则新增一个父级节点，
                idMap.put(id,treeFirstNote.getChildren().size());
                treeFirstNote.getChildren().add(new JsTreeEntity(id.toString(),text));
            }
        }
        return tree;
    }


    /**
     * 通过角色id获取用户菜单
     * @param roleId
     * @return
     */
    @Override
   public List<MenuGroup> getMenugroup(Integer roleId)
    {
        List<MenuGroup> groupList = new ArrayList<>();

        SysRole role = iSysUserDao.getRolebyId(roleId);

        if(role != null){

            List<Integer> menuids = new ArrayList<>();

            String menuidstr[] = role.getRoleDetail().split(",");
            for(int i=0;i<menuidstr.length;i++){
                menuids.add(Integer.parseInt(menuidstr[i]));
            }

            List<SysMenu> menuList = getmenuList(menuids);

            List<String> listg = new ArrayList<>();

            for (SysMenu sm:menuList) {
                if(!listg.contains(sm.getMGroup())){
                    listg.add(sm.getMGroup());
                }
            }

            for (String mgstr:listg) {

                MenuGroup menuGroup = new MenuGroup();

                menuGroup.setGroupname(mgstr);

                List<SysMenu> menuList1 = new ArrayList<>();

                for (SysMenu sm:menuList) {
                    if(mgstr.equals(sm.getMGroup())){
                        menuList1.add(sm);
                    }
                }

                menuGroup.setMenus(menuList1);
                groupList.add(menuGroup);
            }
            return groupList;
        }

        return null;
    }

  public   void setToken(String userid ,String token){
      sharedRedisClient.set(USER_TOKEN_REDIS + userid, token,14400);
  }
    public String getToken(String userid){
      return   sharedRedisClient.get(USER_TOKEN_REDIS + userid);
    }

   public List<SysMenu> getmenuList(List<Integer> menuIds){

        return iSysUserDao.getmenuList(menuIds);
   }

    /**
     * 获取后台管理系统用户列表
     * @param entity
     * @return
     */
    @Override
   public List<SysUser> getUserPage(TableEntity entity){
        return iSysUserDao.getUserPage(entity);
    }

    @Override
    public int getUserTotal(TableEntity entity) {
        // TODO Auto-generated method stub
        return iSysUserDao.getUserTotal(entity);
    }

    /**
     * 获取所有角色
     * @return
     */
    @Override
    public List<SysRole> getAllRole()
    {
        return iSysUserDao.getAllRole();
    }

    /**
     * 更新用户
     * @param entity
     * @return
     */
    @Override
    public int updateUser(SysUser entity){
        return iSysUserDao.updateUser(entity);
    }

     /**修改密码*/
     @Override
     public int updatePasswordById(SysUser user){
         return iSysUserDao.updatePasswordById(user);
     }

    //获取所有菜单
    @Override
   public List<SysMenu> getAllmenuList()
   {
       return iSysUserDao.getAllmenuList();
   }
    /**添加角色*/
    @Override
    public int addRole(SysRole role)
    {
        return iSysUserDao.addRole(role);
    }

    /**修改角色*/
    @Override
    public int editRole(SysRole role){
        return iSysUserDao.editRole(role);
    }

    /**删除角色*/
    @Override
    public int deleteRoleById(Integer roleId)
    {
        return iSysUserDao.deleteRoleById(roleId);
    }

    /**
     * 更新用户
     * @param entity
     * @return
     */
    @Override
    public int addUser(SysUser entity)
    {
        return iSysUserDao.addUser(entity);
    }

    /**查询日志总数*/
    @Override
    public Long getLogTotal(LogTable logTable) {
        return iSysUserDao.getLogTotal(logTable);
    }
    /**分页查询日志*/
    @Override
    public List<SysLog> getLogList(LogTable logTable) {
        return iSysUserDao.getLogList(logTable);
    }

    /**
     * //添加用户管理后台的操作日志
     */
    @Override
    public void addsysUserLog(SysLog sysLog) {
        iSysUserDao.addsysUserLog(sysLog);
    }

    /**查询日志总数*/
    @Override
    public Long getsysUserLogTotal(LogTable logTable) {
        return iSysUserDao.getsysUserLogTotal(logTable);
    }
    /**分页查询日志*/
    @Override
    public List<SysLog> getsysUserLogList(LogTable logTable) {
        return iSysUserDao.getsysUserLogList(logTable);
    }


    /**
     * 获取上传视频签名
     * @return
     */
    @Override
    public String getSignature(Integer userId,Integer videoType,Integer transcoding){

        if(StringUtils.isNotEmpty(sharedRedisClient.get(USER_VIDEO_SIGN +userId+"_"+ videoType))){
            return sharedRedisClient.get(USER_VIDEO_SIGN +userId+"_"+ videoType);
        }

        Signature sign = new Signature();
        sign.setSecretId(Global.getConfig("com.uway.SecretId"));
        sign.setSecretKey(Global.getConfig("com.uway.SecretKey"));
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2);
        sign.setUserId(userId);
        sign.setVideoType(videoType);
        sign.setTeamId(0);
        sign.setTranscoding(transcoding);
        String signature = "";
        try {
            signature = sign.getUploadSignature();
            sharedRedisClient.set(USER_VIDEO_SIGN +userId+"_"+ videoType,signature,3600 * 24 * 2);

        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
        }
        return signature;
    }

}
