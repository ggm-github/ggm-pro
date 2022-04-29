package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.utils.DateUtils;
import com.uway.sports.common.utils.StringUtils;
import com.uway.sports.web.dao.IReplyDao;
import com.uway.sports.web.entity.CommConstantEntity;
import com.uway.sports.web.entity.dbentity.MessagePush;
import com.uway.sports.web.entity.dbentity.MomentsReply;
import com.uway.sports.web.entity.paraentity.MomentsReplyResult;
import com.uway.sports.web.entity.paraentity.MomentsReplyTable;
import com.uway.sports.web.service.IMessageService;
import com.uway.sports.web.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("iReplyService")
public class ReplyServiceImpl implements IReplyService {
    @Autowired
    private IReplyDao iReplyDao;
    @Autowired
    private IMessageService messageService;
    @Autowired
    private SharedRedisClient sharedRedisClient;
    @Override
    public List<MomentsReplyResult> getMomentsReplyList(MomentsReplyTable param) {
        return iReplyDao.getMomentsReplyList(param);
    }

    @Override
    public int getMomentsReplyCnt(MomentsReplyTable param) {
        return iReplyDao.getMomentsReplyCnt(param);
    }

    @Override
    public int updateMomentsReplyBy(MomentsReply param) {
        Integer id = param.getId();
        Integer status = param.getStatus();
        int n = iReplyDao.updateMomentsReplyBy(param);
        if (n > 0){
            sharedRedisClient.hdel(CommConstantEntity.REPLY_MAP_KEY,CommConstantEntity.REPLY_HKEY_HEAD + id);
            if (status != null && status == 0){
                MomentsReplyResult info = getMomentsReplyById(id);
                Integer replyId = info.getReplyId();
                if (replyId == 0){
                    sharedRedisClient.srem(CommConstantEntity.MOMENTS_REPLY_LASTEST + info.getMomentsId(),id.toString());
                }else{
                    sharedRedisClient.srem(CommConstantEntity.REPLY_REPLY_LASTEST + replyId,id.toString());
                }
                // 评论被驳回时，发送消息
                String title = "系统通知";
                String content = info.getReplyContent();
                if(content.length() > 10){
                    content = content.substring(0,10) + "......";
                }
                String replyDateStr = DateUtils.formatDate(DateUtils.parse(info.getInserttime(),"yyyy-MM-dd HH:mm:ss"),"MM-dd HH:mm:ss");
                String desc = String.format("您于%s 发布的%s的评论内容，因%s，系统已进行屏蔽将不予在平台显示，请您注意自己在公共平台的言论，内容多次不符合规定，将进行封号处理并追究相关法律责任。",replyDateStr,content,info.getRemarks());
                // 给队长发送战队消息，审核结果通知
                messageService.addMessage(new MessagePush(0,2,-1,info.getOperUserId(),title,desc,1));

            }
        }
        return n;
    }

    @Override
    public MomentsReplyResult getMomentsReplyById(Integer id) {
        return iReplyDao.getMomentsReplyById(id);
    }
}
