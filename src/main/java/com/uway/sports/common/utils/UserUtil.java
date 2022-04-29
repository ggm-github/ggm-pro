package com.uway.sports.common.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UserUtil {
    public static final List<String> userNames= Arrays.asList("荷兰兔","安哥拉兔","海棠兔","花明兔",
            "垂耳兔","波兰兔","暹罗兔","忌廉兔","喜马拉雅兔","迷你雷克斯兔","巨型格仔兔","英国斑点兔"
            ,"狮子头兔","棉尾兔");
    public static final List<String> userNamesDesc= Arrays.asList("高贵的","美丽的","优雅的","智慧的","娇媚的","窈窕的",
            "英俊的","帅气的","风流的","清新的","温文尔雅的","可爱的","活泼的","聪明的","伶俐的","温顺的","叛逆的","温柔的");
    /*密钥*/
    public static final String[] codes = {"9a5122ed","3c8921e4","25e616cb","42949bdc","3c8921e4","25e616cb","c985fbd9","e752165a","0a5281fe","2cae932e"};
    private static Random random=null;
    private static Random getRandom(){
        if (random==null) random=new Random();
        return random;
    }
    public static Integer getRandomIndex(Integer upLimit){
        return getRandom().nextInt(upLimit);
    }
    public static String getMD5(String word){
        return MD5Encoder.encode(word);
    }
    public static boolean checkSecret(Object key,Integer index,String secret) throws Exception{
        String p= getMD5(key+codes[index]).toLowerCase();
        if (p.equals(secret)) return true;
        return false;
    }

    public static void main(String[] args) {
        String md5 = getMD5("123456").toLowerCase();
        System.out.println(md5);
    }
}
