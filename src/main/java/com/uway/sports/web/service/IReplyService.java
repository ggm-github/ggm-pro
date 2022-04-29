package com.uway.sports.web.service;

import com.uway.sports.web.entity.dbentity.MomentsReply;
import com.uway.sports.web.entity.paraentity.MomentsReplyResult;
import com.uway.sports.web.entity.paraentity.MomentsReplyTable;

import java.util.List;

public interface IReplyService {
    List<MomentsReplyResult> getMomentsReplyList(MomentsReplyTable param);
    int getMomentsReplyCnt(MomentsReplyTable param);
    int updateMomentsReplyBy(MomentsReply param);
    MomentsReplyResult getMomentsReplyById(Integer id);
}
