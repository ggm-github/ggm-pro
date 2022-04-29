package com.uway.sports.web.service;

import com.uway.sports.common.service.IBaseService;
import com.uway.sports.web.entity.dbentity.FeedBack;
import com.uway.sports.web.entity.dbentity.User;
import com.uway.sports.web.entity.dbentity.UserAttention;
import com.uway.sports.web.entity.dbentity.UserPost;
import com.uway.sports.web.entity.excel.UserExport;
import com.uway.sports.web.entity.paraentity.NoticeTaskRequest;
import com.uway.sports.web.entity.paraentity.TimeTable;
import com.uway.sports.web.entity.paraentity.NoticeTaskResult;

import java.util.List;

public interface IAppUserService extends IBaseService{
    List<User> getListByUser(User user);
    Long getListCountByUser(User user);
    void updateUserById(User user);

    Long addUser(User user);
    User getUserById(Long userId);
    User getUserInRedisById(Integer userId);
    Long getFeedbackCount(TimeTable timeTable);
    List<FeedBack> getFeedbackTable(TimeTable timeTable);
    FeedBack getFeedbackById(Long id);
    List<User> getBindCardListByUser(User user);
    /**
     * 获取导出用户数据
     * @param userPost
     * @return
     */
    List<UserExport> exportUserList(UserPost userPost);

    List<UserAttention> getUserAttentionList(UserAttention userAttention);
    int updateUserAttention(Integer userId,Integer status);

    List<NoticeTaskResult> getUserAttentionForNotice(NoticeTaskRequest noticeTaskRequest);
    List<NoticeTaskResult> getUserClickGoodForNotice(NoticeTaskRequest noticeTaskRequest);
    List<NoticeTaskResult> getUserMomentsForNotice(NoticeTaskRequest noticeTaskRequest);

}
