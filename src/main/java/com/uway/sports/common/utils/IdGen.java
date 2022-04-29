/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.uway.sports.common.utils;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
@Lazy(false)
public class IdGen {

	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

    /**
     * 获取一个范围内的随机数,
     *     返回值固定长度,长度不足左侧补0，长度大于length，返回原数据
     * @param minInt
     * @param maxInt
     * @return
     */
	public static String randomInt(int minInt,int maxInt,int length){
        int s = random.nextInt(maxInt) % (maxInt - minInt + 1) + minInt;
        String str = String.valueOf(s);
        if(length > 0 && str.length() < length){
            int n = length - str.length();
            for(int i=0;i<n;i++){
                str = "0"+str;
            }
        }
        return str;
    }

    /**
     * 获取一个范围内的随机数,
     * @param minInt
     * @param maxInt
     * @return
     */
    public static int randomInt(int minInt,int maxInt){
        int s = random.nextInt(maxInt) % (maxInt - minInt + 1) + minInt;
        return s;
    }
    /**
     * Auth: liujingce
     * Date: 2018/7/16 11:41
     * Title: 获取一个随机字符串
     * @param length
     * @return String
    **/
    public static String getRandomString(int length){
        //产生随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //循环length次
        for(int i=0; i<length; i++){
            //产生0-2个随机数，既与a-z，A-Z，0-9三种可能
            int number=random.nextInt(3);
            long result=0;
            switch(number){
                //如果number产生的是数字0；
                case 0:
                    //产生A-Z的ASCII码
                    result=Math.round(Math.random()*25+65);
                    //将ASCII码转换成字符
                    sb.append(String.valueOf((char)result));
                    break;
                case 1:
                    //产生a-z的ASCII码
                    result=Math.round(Math.random()*25+97);
                    sb.append(String.valueOf((char)result));
                    break;
                case 2:
                    //产生0-9的数字
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}
	
	/**
	 * Activiti ID 生成
	 */
	public String getNextId() {
		return IdGen.uuid();
	}

	public static void main(String[] args) {
	    String json = "<xml><appid><![CDATA[wx2e4982d2919513a2]]></appid><bank_type><![CDATA[CMBC_DEBIT]]></bank_type><cash_fee><![CDATA[101]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[N]]></is_subscribe><mch_id><![CDATA[1508282041]]></mch_id><nonce_str><![CDATA[T28jRi34594T3ynw]]></nonce_str><openid><![CDATA[oIUik05xZznZk0Oxk0_1qnkbC9Yw]]></openid><out_trade_no><![CDATA[reward679A1531979820010]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[39328EC578C99D44F2A2C3B9F7B66951]]></sign><time_end><![CDATA[20180719135712]]></time_end><total_fee>101</total_fee><trade_type><![CDATA[MWEB]]></trade_type><transaction_id><![CDATA[4200000136201807193091821209]]></transaction_id></xml>";
        Map<String,String> returnMap = JSONUtils.fromJson(json,Map.class);
	}

}
