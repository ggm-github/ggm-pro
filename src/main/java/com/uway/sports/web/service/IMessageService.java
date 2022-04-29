package com.uway.sports.web.service;


import com.uway.sports.common.service.IBaseService;
import com.uway.sports.common.utils.RestResponse;
import com.uway.sports.web.entity.dbentity.MessagePush;
import com.uway.sports.web.entity.dbentity.UmengPush;
import com.uway.sports.web.entity.paraentity.MessagePushTable;
import com.uway.sports.web.entity.paraentity.MessageResponse;
import com.uway.sports.web.entity.paraentity.PushMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IMessageService extends IBaseService {

    int getMessageTableCnt(MessagePushTable param);
    List<MessageResponse> getMessageTable(MessagePushTable param);
    RestResponse addMessage(MessagePush param);
    int addMessageByTeam(MessagePush param);
    void updateMessageStatus(HashMap param);
    Map<String,String> updateMessageForSend(Integer id);
//    RestResponse updateMessage(MessagePush param);
    MessageResponse getMessageById(int messageid);
//    RestResponse addUserMsg(UserMsgTime param);


    int addUmenpush(PushMessage pushMessage);
    List<UmengPush> getListByUmenPush(UmengPush umengPush);
    Long getListCountByUmengPush(UmengPush condition);
}
