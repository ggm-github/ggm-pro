package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.utils.StringUtils;
import com.uway.sports.web.dao.IMatchNoticeDao;
import com.uway.sports.web.entity.dbentity.MatchNotice;
import com.uway.sports.web.entity.dbentity.MatchNoticeOperate;
import com.uway.sports.web.entity.dbentity.NoticeTemplate;
import com.uway.sports.web.entity.paraentity.MatchNoticeResult;
import com.uway.sports.web.entity.paraentity.MatchNoticeTable;
import com.uway.sports.web.entity.paraentity.NoticeTemplateRequest;
import com.uway.sports.web.service.IMatchNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iMatchNoticeService")
public class MatchNoticeServiceImpl implements IMatchNoticeService {
    @Autowired
    IMatchNoticeDao iMatchNoticeDao;
    @Override
    public List<MatchNoticeResult> getMatchNoticeList(MatchNoticeTable param) {
        return iMatchNoticeDao.getMatchNoticeList(param);
    }

    @Override
    public int getMatchNoticeCnt(MatchNoticeTable param) {
        return iMatchNoticeDao.getMatchNoticeCnt(param);
    }

    @Override
    public int updateMatchNoticeForAudit(MatchNotice param,Integer operUserId) {
        String noticeTypeStatus = param.getNoticeTypeStatus();
        if (StringUtils.isEmpty(noticeTypeStatus)){
            return 0;
        }
        String[] typeStatusArr = noticeTypeStatus.split(",");
        Integer status = 1;
        for(int i=0;i<typeStatusArr.length;i++){
            // 若有一个不通过，则总状态为不通过
            if ("0".equals(typeStatusArr[i])){
                status = 3;
            }
        }
        param.setStatus(status);
        param.setCheckUserId(operUserId);
        int n = iMatchNoticeDao.updateMatchNoticeBy(param);
        if (n > 0){
            Integer id = param.getId();
            MatchNoticeOperate oper = new MatchNoticeOperate(id,status,operUserId);
            iMatchNoticeDao.addMatchNoticeOperate(oper);
        }
        return n;
    }

    @Override
    public MatchNotice getMatchNoticeById(Integer id) {
        return iMatchNoticeDao.getMatchNoticeById(id);
    }

    @Override
    public List<NoticeTemplate> getNoticeTemplateList(NoticeTemplateRequest param) {
        return iMatchNoticeDao.getNoticeTemplateList(param);
    }
}
