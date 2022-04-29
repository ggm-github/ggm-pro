package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.*;
import com.uway.sports.web.entity.excel.UserExport;
import com.uway.sports.web.entity.paraentity.NoticeTaskRequest;
import com.uway.sports.web.entity.paraentity.TimeTable;
import com.uway.sports.web.entity.paraentity.NoticeTaskResult;

import java.util.List;

@MyBatisDao
public interface IAppUserDao {
    void updateUserById(User user);
    List<User> getListByUser(User user);
    Long getListCountByUser(User user);

    void addUser(User user);

    User getUserById(Long userId);

    /**
     * 将app日志入库
     */
    void addAppLog(List<SysLog> sysLog);

    List<UrlMenu> getAllurlMenu();

    Long getFeedbackCount(TimeTable timeTable);

    List<FeedBack> getFeedbackTable(TimeTable timeTable);

    FeedBack getFeedbackById(Long id);
    List<User> getBindCardListByUser(User user);
    int getUserMaxId();

    /**
     * 获取导出用户数据
     * @param userPost
     * @return
     */
    List<UserExport> exportUserList(UserPost userPost);

    /**
     * 查询用户的关注关系
     * @param userAttention
     * @return
     */
    List<UserAttention> getUserAttentionList(UserAttention userAttention);

    int updateUserAttention(UserAttention param);

    List<NoticeTaskResult> getUserAttentionForNotice(NoticeTaskRequest param);
    List<NoticeTaskResult> getUserClickGoodForNotice(NoticeTaskRequest param);
    List<NoticeTaskResult> getUserMomentsForNotice(NoticeTaskRequest param);

}
