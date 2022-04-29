package com.uway.sports.web.utils;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/4/2 17:57
 * @Discription
 */
public class FileUtils {

    /**
     * @Title 获取视频文件的播放时长（秒）
     * @Author liujingce
     * @Date 2019/4/2 19:19
     *
     */
    public static long getVideoFileTime(String path){
        File source = new File(path);
        if (!source.exists()){
            return 0;
        }
        Encoder encoder = new Encoder();
        long length = 0;
        try {
            MultimediaInfo m = encoder.getInfo(source);
            length = m.getDuration()/1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    public static void main(String[] args) {
        long n = getVideoFileTime("http://www.jifentuzi.cn");
        System.out.println(n);
    }

}
