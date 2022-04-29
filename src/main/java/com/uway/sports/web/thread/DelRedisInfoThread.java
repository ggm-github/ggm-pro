package com.uway.sports.web.thread;

import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;

import java.util.concurrent.atomic.AtomicLong;
/**
 * Auth: liujingce
 * Date: 2018/3/15 14:50
 * Title: 用于循环删除redis中，数量较多的一类key
 * Describer:
**/
public class DelRedisInfoThread extends Thread{



    public static final String PRODUCT_INFO_REDIS = "product:";
    public static final String ACTIVITY_INFO_REDIS = "activity:";
    public static final String USER_ID_REDIS_HEAD = "user:";
    public static final String USER_LOGIN_NAME_REDIS_HEAD = "loginName:";
    public static final String USER_CARD_GZ_REDIS_HEAD = "userCard_1:";
    public static final String USER_CARD_BD_REDIS_HEAD = "userCard_2:";

    private Logger logger = LoggerFactory.getLogger(DelRedisInfoThread.class);
    private String type;
    private int threadNum;
    private int startNum;
    private int maxNum;
    private SharedRedisClient sharedRedisClient;
    private AtomicLong finished;

    public DelRedisInfoThread(String type, int threadNum,int startNum, int maxNum,
                              AtomicLong finished, SharedRedisClient sharedRedisClient) {
        this.type = type;
        this.startNum = startNum;
        this.maxNum = maxNum;
        this.threadNum = threadNum;
        this.finished = finished;
        this.sharedRedisClient = sharedRedisClient;
    }

    @Override
    public void run() {
        //删除用户信息及所绑定的积分卡的信息
        if("user".equals(type)){
            long time1 = System.currentTimeMillis();
            int m = 0;
            ShardedJedis jedis = sharedRedisClient.getJedis();
            try{
                //目前无法按照key的规则，获取所有的同类key值，所以按照库中的id最大进行循环处理
                String loginName;
                for (int i=startNum;i<= maxNum;i++) {
                    //查询出id对应的redis中的用户名
                    loginName = jedis.get(USER_ID_REDIS_HEAD + i);
                    //删除redis中用户名的key
                    if (StringUtils.isNotBlank(loginName)){
                        jedis.del(USER_LOGIN_NAME_REDIS_HEAD + loginName);
                    }
                    //删除用户id的key
                    jedis.del(USER_ID_REDIS_HEAD + i);
                    //删除用户绑定的积分卡的key
                    jedis.del(USER_CARD_GZ_REDIS_HEAD + i);
                    //删除用户关注的积分卡的key
                    jedis.del(USER_CARD_BD_REDIS_HEAD + i);
                    m++;
                }
                finished.getAndAdd(1);
            }catch(Exception e){
                e.printStackTrace();
                finished.getAndAdd(1);
            }finally{
                jedis.close();
            }
            long time2 = System.currentTimeMillis();
            logger.debug("此用户信息删除线程【"+threadNum+"】用时："+((time2-time1)/1000)+"s,共处理"+m+"个用户的信息");
        }
        //商品
        if("product".equals(type)){
            long time1 = System.currentTimeMillis();
            int m = 0;
            ShardedJedis jedis = sharedRedisClient.getJedis();
            try{
                //目前无法按照key的规则，获取所有的同类key值，所以按照库中的id最大进行循环处理
                for (int i=startNum;i<= maxNum;i++) {
                    //删除商品key
                    jedis.del(PRODUCT_INFO_REDIS + i);
                    m++;
                }
                finished.getAndAdd(1);
            }catch(Exception e){
                e.printStackTrace();
                finished.getAndAdd(1);
            }finally{
                jedis.close();
            }
            long time2 = System.currentTimeMillis();
            logger.debug("此商品信息删除线程【"+threadNum+"】用时："+((time2-time1)/1000)+"s,共处理"+m+"个商品信息");
        }

        //活动
        if("activity".equals(type)){
            long time1 = System.currentTimeMillis();
            int m = 0;
            ShardedJedis jedis = sharedRedisClient.getJedis();
            try{
                //目前无法按照key的规则，获取所有的同类key值，所以按照库中的id最大进行循环处理
                for (int i=startNum;i<= maxNum;i++) {
                    //删除商品key
                    jedis.del(ACTIVITY_INFO_REDIS + i);
                    m++;
                }
                finished.getAndAdd(1);
            }catch(Exception e){
                e.printStackTrace();
                finished.getAndAdd(1);
            }finally{
                jedis.close();
            }
            long time2 = System.currentTimeMillis();
            logger.debug("此活动信息删除线程【"+threadNum+"】用时："+((time2-time1)/1000)+"s,共处理"+m+"个活动信息");
        }


    }
}