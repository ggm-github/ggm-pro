package com.uway.sports.web.serviceImpl;


import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.common.utils.JSONUtils;
import com.uway.sports.common.utils.StringUtils;
import com.uway.sports.web.dao.IAppUserDao;
import com.uway.sports.web.entity.CommConstantEntity;
import com.uway.sports.web.entity.dbentity.*;
import com.uway.sports.web.entity.excel.UserExport;
import com.uway.sports.web.entity.paraentity.NoticeTaskRequest;
import com.uway.sports.web.entity.paraentity.TimeTable;
import com.uway.sports.web.entity.paraentity.NoticeTaskResult;
import com.uway.sports.web.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("iAppUserService")
@Transactional
public class AppUserServiceImpl extends BaseServiceImpl implements IAppUserService {
    public static final String USER_INFO_REDIS = "user:";
    public static final String LOGINNAMELIST_INFO_REDIS = "loginNameList";
    public static final String LOGINNAME_REDIS = "loginName:";
    @Autowired
    private IAppUserDao iAppUserDao;

    @Autowired
    private SharedRedisClient sharedRedisClient;

    @Override
    public List<User> getListByUser(User user) {
        return iAppUserDao.getListByUser(user);
    }

    @Override
    public Long getListCountByUser(User user) {
        return iAppUserDao.getListCountByUser(user);
    }

    @Override
    public void updateUserById(User user) {
        iAppUserDao.updateUserById(user);
    }

    @Override
    public Long addUser(User user) {
        iAppUserDao.addUser(user);
        return user.getUserId();
    }

    @Override
    public User getUserById(Long userId) {
        return iAppUserDao.getUserById(userId);
    }

    @Override
    public User getUserInRedisById(Integer userId) {
        String userStr = sharedRedisClient.get(CommConstantEntity.USER_INFO_HEAD + userId);
        User userInfo;
        if (StringUtils.isNotEmpty(userStr)){
            userInfo = JSONUtils.fromJson(userStr,User.class);
        }else{
            userInfo = getUserById(userId.longValue());
        }
        return userInfo;
    }

    @Override
    public Long getFeedbackCount(TimeTable timeTable) {
        return iAppUserDao.getFeedbackCount(timeTable);
    }

    @Override
    public List<FeedBack> getFeedbackTable(TimeTable timeTable) {
        return iAppUserDao.getFeedbackTable(timeTable);
    }

    @Override
    public FeedBack getFeedbackById(Long id) {
        return iAppUserDao.getFeedbackById(id);
    }

    @Override
    public List<User> getBindCardListByUser(User user) {
        return iAppUserDao.getBindCardListByUser(user);
    }

    /**
     * 获取导出用户数据
     * @param userPost
     * @return
     */
    @Override
    public List<UserExport> exportUserList(UserPost userPost) {
        return iAppUserDao.exportUserList(userPost);
    }

    @Override
    public List<UserAttention> getUserAttentionList(UserAttention userAttention) {
        return iAppUserDao.getUserAttentionList(userAttention);
    }

    @Override
    public int updateUserAttention(Integer userId, Integer status) {
        // 关注表中，status只是用来同步用户的状态，所以用户的关注应该是一致的
        // status 用户修改状态   attenStatus 查询条件
        Integer attenStatus = 1;
        if (status == 1){
            attenStatus = 0;
        }
        // 查询用户关注的列表
        UserAttention param = new UserAttention(userId,null,null,attenStatus);
        List<UserAttention> attenList = getUserAttentionList(param);
        if (attenList.size() > 0){
            // 修改用户关注的状态
            param.setStatus(status);
            iAppUserDao.updateUserAttention(param);
            // 清除关注用户，redis缓存
            for(UserAttention info : attenList){
                // 移除被关注人与用户的关系
                sharedRedisClient.hdel(CommConstantEntity.USER_ATTENTION_HEAD + info.getAttentionUserId(),userId.toString());
                // 移除被关注人的粉丝中的用户
                sharedRedisClient.zrem(CommConstantEntity.USER_FANS_LIST_HEAD + info.getAttentionUserId(),userId.toString());
            }
        }
        // 查询用户粉丝列表
        param.setUserId(null);
        param.setAttentionUserId(userId);
        param.setStatus(attenStatus);
        List<UserAttention> fansList = getUserAttentionList(param);
        if (fansList.size() > 0){
            // 修改用户关注的状态
            param.setStatus(status);
            iAppUserDao.updateUserAttention(param);
            // 清除粉丝用户，redis缓存
            for(UserAttention info : attenList){
                // 移除用户粉丝与用户的关注关系
                sharedRedisClient.hdel(CommConstantEntity.USER_ATTENTION_HEAD + info.getUserId(),userId.toString());
                // 移除用户粉丝关注的用户关系
                sharedRedisClient.zrem(CommConstantEntity.USER_ATTENTION_LIST_HEAD + info.getUserId(),userId.toString());
            }
        }
        // 清除用户在redis中的缓存
        sharedRedisClient.del(CommConstantEntity.USER_ATTENTION_HEAD + userId);
        sharedRedisClient.del(CommConstantEntity.USER_ATTENTION_LIST_HEAD + userId);
        sharedRedisClient.del(CommConstantEntity.USER_FANS_LIST_HEAD + userId);
        return 0;
    }

    @Override
    public List<NoticeTaskResult> getUserAttentionForNotice(NoticeTaskRequest noticeTaskRequest) {
        return iAppUserDao.getUserAttentionForNotice(noticeTaskRequest);
    }

    @Override
    public List<NoticeTaskResult> getUserClickGoodForNotice(NoticeTaskRequest noticeTaskRequest) {
        return iAppUserDao.getUserClickGoodForNotice(noticeTaskRequest);
    }

    @Override
    public List<NoticeTaskResult> getUserMomentsForNotice(NoticeTaskRequest noticeTaskRequest) {
        return iAppUserDao.getUserMomentsForNotice(noticeTaskRequest);
    }
}

