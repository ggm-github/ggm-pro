package com.uway.sports.web.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Auth: liujingce
 * Date: 2018/2/3 11:56
 * Title: 存放一些功能的常量
 * Describer:
 **/
public class CommConstantEntity {

    /**
     * 是否有效状态status  1-有效
     */
    public static final int STATUS_USED = 1;
    /**
     * 是否有效状态status  0-失效
     */
    public static final int STATUS_UNUSED = 0;

    /**
     * app版本类型  1-ios  2-android
     */
    public static final int APP_VERSION_TYPE_IOS = 1;
    public static final int APP_VERSION_TYPE_ANDROID = 2;

    /**
     * 启动页图片在redis中的key值
     */
    public static final String START_UP_PICTURE = "start_up_picture";
    /**
     * app版本列表
     */
    public static final String VERSION_INFO_MAP_KEY = "version_info";
    /**
     * app当前最新版本唉redis中的key值
     */
    public static final String LAST_VERSION_KEY_IOS = "last_version:1";
    public static final String LAST_VERSION_KEY_ANDROID = "last_version:2";

    public static final String VIDEO_KEY = "team_video:";

    /** 话题存放map的key */
    public static final String THEME_KEY = "all_theme";
    /** 话题存放map的hkey   eg:【theme-info:themeId】 */
    public static final String THEME_HKEY_HEAD = "theme_info:";
    /** 话题动态数量存放map的key eg：【all_theme_moments_cnt:themeId】*/
    public static final String THEME_MOMENTS_CNT_KEY = "all_theme_moments_cnt";
    /** 话题动态数量存放map的hkey  eg：【moments-cnt:themeId】*/
    public static final String THEME_MOMENTS_CNT_HKEY_HEAD = "moments_cnt:";

    /** 回复存放map的key  */
    public static final String REPLY_MAP_KEY = "all_reply";
    /** 回复存放map的key  eg:【replay_info:replyId】 */
    public static final String REPLY_HKEY_HEAD = "replay_info:";
    /** 动态最新的几条回复存id放list的key  eg:【replay_lastest:videoId】 */
    public static final String MOMENTS_REPLY_LASTEST = "moments_replay_lastest:";
    /** 评论回复数量存放map的key */
    public static final String REPLY_REPLY_CNT_KEY = "all_reply_reply_cnt";
    /** 评论回复数量存放map的hkey */
    public static final String REPLY_REPLY_CNT_HKEY_HEAD = "reply_cnt:";
    /** 评论最新的几条回复id存放list的key */
    public static final String REPLY_REPLY_LASTEST = "reply_reply_lastest:";

    /** 动态上一小时的热度值 */
    public static final String MOMENTS_HOT_SET_LAST_KEY = "moments_hot_set_last:";
    /**************** 参数配置表中值 ******************************/
    public static final String CONF_RANKING_MESSAGE = "ranking_message";
    public static final String CONF_RANKING_MESSAGE_DAY = "ranking_message_day";
    public static final String CONF_RANKING_MESSAGE_WEEK = "ranking_message_week";
    public static final String CONF_RANKING_MESSAGE_MONTH = "ranking_message_month";
    public static final String CONF_RANKING_MESSAGE_FINAL = "ranking_message_final";

    /** 用户关注关系key  eg：【user_attention:userId】 attentionUserId:isAttention */
    public static final String USER_ATTENTION_HEAD = "user_attention:";
    /** 用户关注列表key  eg：【user_attention_list:userId】 有序的set，顺序使用关注的时间升序 */
    public static final String USER_ATTENTION_LIST_HEAD = "user_attention_list:";
    /** 用户粉丝列表key  eg：【user_fans_list:userId】 有序的set，顺序使用粉丝关注（被关注）的时间升序 */
    public static final String USER_FANS_LIST_HEAD = "user_fans_list:";
    /** 用户信息key */
    public static final String USER_INFO_HEAD = "user:";
    /***************** 友盟推送 原生跳转设置 *************************/
    public static final Map<Integer,String> umengActivityMap = new HashMap<>();
    static {
        umengActivityMap.put(1,"matchDetail");
        umengActivityMap.put(14,"themeDetail");
        umengActivityMap.put(18,"momentsDetail");
        umengActivityMap.put(23,"messageList");
    }
    /** 操场部分功能的默认用户id */
    public static final Integer SPORTS_USER_ID = 120;


}
