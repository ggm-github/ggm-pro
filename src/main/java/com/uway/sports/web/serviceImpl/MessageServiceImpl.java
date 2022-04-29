package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.common.utils.JSONUtils;
import com.uway.sports.common.utils.RestResponse;
import com.uway.sports.common.utils.RestResponseFactory;
import com.uway.sports.common.utils.StringUtils;
import com.uway.sports.push.PushService;
import com.uway.sports.web.dao.IMessageDao;
import com.uway.sports.web.entity.CommConstantEntity;
import com.uway.sports.web.entity.dbentity.MessagePush;
import com.uway.sports.web.entity.dbentity.Pushdevice;
import com.uway.sports.web.entity.dbentity.UmengPush;
import com.uway.sports.web.entity.paraentity.MessageInUser;
import com.uway.sports.web.entity.paraentity.MessagePushTable;
import com.uway.sports.web.entity.paraentity.MessageResponse;
import com.uway.sports.web.entity.paraentity.PushMessage;
import com.uway.sports.web.service.IMessageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/26 16:19
 * @Discription
 */
@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl implements IMessageService {
    private Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private IMessageDao messageDao;

    @Override
    public int getMessageTableCnt(MessagePushTable param) {
        return messageDao.getMessageTableCnt(param);
    }

    @Override
    public List<MessageResponse> getMessageTable(MessagePushTable param) {
        List<MessageResponse> list = messageDao.getMessageTable(param);
        return list;
    }

    @Override
    public RestResponse addMessage(MessagePush param) {
        messageDao.addMessage(param);
        return RestResponseFactory.newSuccessfulResponse("新增成功");
    }

    @Override
    public int addMessageByTeam(MessagePush param) {
        return messageDao.addMessageByTeam(param);
    }

    @Override
    public void updateMessageStatus(HashMap param) {
        messageDao.updateMessageStatus(param);
    }

    @Override
    public Map<String, String> updateMessageForSend(Integer id) {
        Map<String,String> result = new HashMap<>();
        MessagePushTable table = new MessagePushTable();
        table.setMessageId(id);
        List<MessageResponse> list = messageDao.getMessageTable(table);
        MessageResponse info = list.get(0);
        if (info.getStatus() != 0){
            result.put("success","0");
            result.put("message","该消息已发布或已取消");
            return result;
        }
        HashMap<String,Object> param = new HashMap<>();
        param.put("messageId",id);
        param.put("statusParam",1);
        int n = messageDao.updateMessageStatus(param);
        logger.debug("更新主消息状态 n=" + n);
        if (n > 0){
            Integer areaId = info.getAreaId();
            Integer industryId = info.getIndustryId();
            Integer userId = info.getUserId();
            Integer teamId = info.getTeamId();
            String addParamJson = info.getAddParamJson();
            // 新增消息记录
            info.setRelId(info.getMessageId());
            info.setStatus(1);
            int m = 0;
            List<Integer> userIdList = null;
            if(userId != null){
                info.setUserId(userId);
                m = messageDao.addMessage(info);
            }else if (areaId != null || industryId != null){
                // 根据 区域、行业查询出所有的战队下的用户，并添加消息
                m = addMessageByTeam(info);
            }else if(StringUtils.isNotEmpty(addParamJson)){
                MessageInUser userParam = JSONUtils.fromJson(addParamJson,MessageInUser.class);
                userIdList = userParam.getIdList();
                userParam.setMessagePush(info);
                m = messageDao.addMessageByUser(userParam);
            }else if(industryId == null && areaId == null
                    && teamId == null && userId == null && StringUtils.isEmpty(addParamJson)){
                // 给所有人发送
                m = messageDao.addMessageForAll(info);
            }
            logger.debug("发送消息  m=" + m);
            // 判断是否需要发推送
            Integer isUmeng = info.getIsUmeng();
            if (isUmeng != null && isUmeng == 1){
                // 向推送表中添加记录
                PushMessage pushMessage = new PushMessage();
                pushMessage.setTitle(info.getReferentName());
                pushMessage.setBody(info.getReferentDsc());
                Integer openWay = info.getOpenWay();
                if (openWay != null && openWay == 0){
                    pushMessage.setType("0");
                    pushMessage.setUrl(info.getReferentUrl());
                }else if (openWay != null && openWay > 0){
                    pushMessage.setType("1");
                    pushMessage.setActivity(CommConstantEntity.umengActivityMap.get(openWay));
                    pushMessage.setModelId(info.getReferentUrl());
                }
                pushMessage.setMessageType("0");
                if (userIdList != null && userIdList.size() > 0){
                    pushMessage.setSendType(3);
                    StringBuffer sbUserId = new StringBuffer();
                    for (Integer userid : userIdList){
                        sbUserId.append(userid).append(",");
                    }
                    pushMessage.setAlias(sbUserId.substring(0,sbUserId.length() - 1));
                }
                addUmenpush(pushMessage);
            }
            result.put("success","1");
        }else{
            result.put("success","0");
            result.put("message","该消息已发布或已取消");
        }
        return result;
    }

    @Override
    public MessageResponse getMessageById(int messageId) {
        MessageResponse info = messageDao.getMessageInfoById(messageId);
        return info;
    }

    @Override
    public int addUmenpush(PushMessage pushMessage) {
//        private String title;//标题
//        private String subtitle;//副标题
//        private String body;//题文 正文
//        private String url;//访问的url
//        private String activity;//访问的app原生页面
//        private String type="0";//0 是url  1是app原生页面
//        private String messageType;// 消息发送类型系统类型 0是全部  1是android  2是ios
        int sendType = pushMessage.getSendType();
        if("0".equals(pushMessage.getMessageType()) || "2".equals(pushMessage.getMessageType())){
            PushService pushService = new PushService("5cac8c530cafb23697000581","ioxbytndilvyebjc7hnbhjgqomuxts0x");
            try {
                if (sendType == 2){
                    pushService.sendIOSListcast(pushMessage);
                }else if(sendType == 3){
                    pushService.sendIOSCustomizedcast(pushMessage);
                } else{
                    pushService.sendIOSBroadcast(pushMessage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if("0".equals(pushMessage.getMessageType()) || "1".equals(pushMessage.getMessageType())){
            //android
            PushService pushService1 = new PushService("5cac8cb761f564b37b000edb","trblkl1a3vvvrtknlkwd3xe5vjn0cn8k");
            try {
                if (sendType == 2){
                    pushService1.sendAndroidListcast(pushMessage);
                }else if(sendType == 3){
                    pushService1.sendAndroidCustomizedcast(pushMessage);
                }else{
                    pushService1.sendAndroidBroadcast(pushMessage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return messageDao.addUmenpush(pushMessage);
    }

    @Override
    public List<UmengPush> getListByUmenPush(UmengPush umengPush) {
        return messageDao.getListByUmenPush(umengPush);
    }

    @Override
    public Long getListCountByUmengPush(UmengPush condition) {
        return messageDao.getCountByUmengPush(condition);
    }
}
