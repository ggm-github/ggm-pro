package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.web.dao.INewsDao;
import com.uway.sports.web.entity.dbentity.MatchGroup;
import com.uway.sports.web.entity.dbentity.MatchNews;
import com.uway.sports.web.entity.dbentity.SportsNews;
import com.uway.sports.web.entity.dbentity.TencentVideoInfo;
import com.uway.sports.web.entity.paraentity.*;
import com.uway.sports.web.service.INewsService;
import com.uway.sports.web.service.IVideoService;
import org.apache.xmlbeans.impl.regex.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/20 20:39
 * @Discription
 */
@Service("newsService")
public class NewsServiceImpl extends BaseServiceImpl implements INewsService {
    @Autowired
    private INewsDao newsDao;
    @Autowired
    private IVideoService iVideoService;

    @Override
    public int addSportsNews(SportsNews sportsNews) {
        return newsDao.addSportsNews(sportsNews);
    }

    @Override
    public int updateSportsNews(SportsNews sportsNews) {
        return newsDao.updateSportsNews(sportsNews);
    }

    @Override
    public int updateSportsNewsBy(SportsNews sportsNews) {
        return newsDao.updateSportsNewsBy(sportsNews);
    }

    @Override
    public List<SportsNewsInfo> getSportsNewsTable(SportsNewsTable sportsNewsTable) {
        return newsDao.getSportsNewsTable(sportsNewsTable);
    }

    @Override
    public int getSportsNewsCnt(SportsNewsTable sportsNewsTable) {
        return newsDao.getSportsNewsCnt(sportsNewsTable);
    }

    @Override
    public SportsNewsInfo getSportsNewsById(Integer id) {
        return newsDao.getSportsNewsById(id);
    }

    @Override
    public int updateSportsNewsExpireSort() {
        return newsDao.updateSportsNewsExpireSort();
    }

    @Override
    public int addMatchNews(MatchNews matchNews) {
        return newsDao.addMatchNews(matchNews);
    }

    @Override
    public int updateMatchNews(MatchNews matchNews) {
        return newsDao.updateMatchNews(matchNews);
    }

    @Override
    public int updateMatchNewsBy(MatchNews matchNews) {
        return newsDao.updateMatchNewsBy(matchNews);
    }

    @Override
    public List<MatchNewsInfo> getMatchNewsTable(MatchNewsTable matchNewsTable) {
        return newsDao.getMatchNewsTable(matchNewsTable);
    }

    @Override
    public int getMatchNewsCnt(MatchNewsTable matchNewsTable) {
        return newsDao.getMatchNewsCnt(matchNewsTable);
    }

    @Override
    public MatchNewsInfo getMatchNewsById(Integer id) {
        MatchNewsInfo info = newsDao.getMatchNewsById(id);
        if (info == null){
            return info;
        }
        // 查询视频
        if (info.getIsNeedVideo() != null && info.getIsNeedVideo() == 1){
            TencentVideoRequest tencentVideoRequest = new TencentVideoRequest();
            tencentVideoRequest.setVideoType(1);
            tencentVideoRequest.setRelatedId(id);
            List<TencentVideoInfo> videoList = iVideoService.getTencentVideoBy(tencentVideoRequest);
            info.setVideoList(videoList);
        }
        // 查询项目
        List<MatchItemRelInfo> itemList = newsDao.getItemByMatchId(id);
        info.setItemList(itemList);

        return info;
    }

    @Override
    public int updateMatchNewsExpireSort() {
        return newsDao.updateMatchNewsExpireSort();
    }

    /**
     * 给新闻添加阅读量
     * @param id
     * @return
     */
    @Override
    public int updateNewsReadCnt(Integer id){
        return newsDao.updateNewsReadCnt(id);
    }

    @Override
    public List<MatchGroup> getMatchGroupList(MatchGroupTable matchGroupTable) {
        return newsDao.getMatchGroupList(matchGroupTable);
    }

    @Override
    public int getMatchGroupCnt(MatchGroupTable matchGroupTable) {
        return newsDao.getMatchGroupCnt(matchGroupTable);
    }

    @Override
    public MatchGroup getMatchGroupById(Integer id) {
        return newsDao.getMatchGroupById(id);
    }

    @Override
    public int addMatchGroup(MatchGroup matchGroup) {
        return newsDao.addMatchGroup(matchGroup);
    }

    @Override
    public int updateMatchGroupInfo(MatchGroup matchGroup) {
        return newsDao.updateMatchGroupInfo(matchGroup);
    }

    @Override
    public int updateMatchGroupBy(MatchGroup matchGroup) {
        return newsDao.updateMatchGroupBy(matchGroup);
    }
}
