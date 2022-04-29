package com.uway.sports.web.dao;


import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.MessagePush;
import com.uway.sports.web.entity.dbentity.Pushdevice;
import com.uway.sports.web.entity.dbentity.UmengPush;
import com.uway.sports.web.entity.paraentity.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Auth: liujingce
 * Date: 2018/4/3 19:21
 * Title:
 * Describer:
 **/
@MyBatisDao
public interface IMessageDao {
    int getMessageTableCnt(MessagePushTable param);
    List<MessageResponse> getMessageTable(MessagePushTable param);
    int addMessage(MessagePush param);
    int addMessageByTeam(MessagePush param);
    int addMessageByUser(MessageInUser param);
    int addMessageForAll(MessagePush param);
//    void updateMessage(MessagePush param);
    MessageResponse getMessageInfoById(int messageId);
    int updateMessageStatus(HashMap param);
//    void updateUserMsgTime(UserMsgTime param);
//    void addUserMsgTime(UserMsgTime param);
//    int getUserMsgTimeCnt(MessageRequest param);


    int addUmenpush(PushMessage pushMessage);
    List<UmengPush> getListByUmenPush(UmengPush umengPush);
    Long getCountByUmengPush(UmengPush umengPush);
    List<Pushdevice> getPushdeviceList(@Param(value="userIdList") List<Integer> userIdList,
                                       @Param(value="terminalType") Integer terminalType);
}
