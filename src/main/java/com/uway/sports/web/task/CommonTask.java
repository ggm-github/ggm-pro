package com.uway.sports.web.task;

import com.uway.sports.web.controller.CommContoller;
import com.uway.sports.web.service.ICommonTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("CommonTask")
public class CommonTask {

    private Logger logger = LoggerFactory.getLogger(CommContoller.class);

    @Resource
    private ICommonTaskService iCommonTaskService;

    public void addAppLog() throws Exception {
        long beginTime = System.currentTimeMillis();//1、开始时间
        logger.info(String.format("将app用户日志入库 ；时间：%s ；",new SimpleDateFormat("hh:mm:ss.SSS")
                .format(beginTime)));
         iCommonTaskService.addAppLog();
    }

    public void updateRanking(){
        logger.info(String.format("更新队伍排名；时间：%s ；",new SimpleDateFormat("hh:mm:ss.SSS").format(new Date())));
        iCommonTaskService.updateRanking();
    }

    public void addVote(){
        logger.info(String.format("将投票入库；时间：%s ；",new SimpleDateFormat("hh:mm:ss.SSS").format(new Date())));
        iCommonTaskService.addVote();
    }

    public void updateTxTeamVideo(){
        logger.info(String.format("腾讯视频鉴黄时间回调；时间：%s ；",new SimpleDateFormat("hh:mm:ss.SSS").format(new Date())));
        iCommonTaskService.updateTxTeamVideo();
    }

    public void updateExpireSort(){
        logger.info(String.format("新闻、赛事，到期权重归零；时间：%s",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date())));
        iCommonTaskService.updateExpireSort();
    }

    public void updateExpireOrder(){
        logger.info(String.format("更新订单中未及时支付的数据；时间：%s",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date())));
        iCommonTaskService.updateExpireOrder();
    }

    public void getAndUpdateRanking(){
        logger.info(String.format("获取并更新赛事活动排名；时间：%s",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date())));
        iCommonTaskService.getAndUpdateRanking();
    }

    public void getMomentsHot(){
        logger.info(String.format("动态热度调整；时间：%s",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date())));
        iCommonTaskService.getMomentsHot();
    }

    public void pushSendAttention(){
        logger.info(String.format("用户被关注提醒；时间：%s",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date())));
        iCommonTaskService.pushSendAttention();
    }
    public void pushSendGood(){
        logger.info(String.format("用户被点赞提醒；时间：%s",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date())));
        iCommonTaskService.pushSendGood();
    }
    public void pushSendMoments(){
        logger.info(String.format("用户关注人新动态提醒；时间：%s",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date())));
        iCommonTaskService.pushSendMoments();
    }
    public void getTeamlistForNotWorksMoments(){
        logger.info(String.format("用户报名赛事活动，参赛作品上传提醒；时间：%s",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date())));
        iCommonTaskService.getTeamlistForNotWorksMoments();
    }

    public void getSpecialMatchRanking(){
        logger.info(String.format("特殊赛事排名；开始时间：%s",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())));
        iCommonTaskService.getDepartStepInfoToRedis();
        logger.info(String.format("特殊赛事排名完成；结束时间：%s",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())));

    }

}