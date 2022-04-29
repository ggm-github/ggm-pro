package com.uway.sports.common.utils;

import com.alibaba.fastjson.JSON;
import com.uway.sports.web.entity.paraentity.SmsSendRequest;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class SendSMS {

    private static Logger logger = LoggerFactory.getLogger(SendSMS.class);

    public static void main(String[] args) {

        try {

            //boolean r = SendMsg("13552357227","askdfasdfkasdjf","http://sep9.cn/tigi02");
            //System.out.println(r);

			/*System.out.println("s:"+DateUtils.getDateTime());
			String msg = "【爱奇艺】您好，您的卡密是：ADFASDF232KEWJAB，激活连接：http://sep9.cn/tigi02";// 短信内容
			String r = batchSend(url, un, pw, "15330596688,13552357227", msg, "1", null);
			System.out.println(r);
			System.out.println("e:"+DateUtils.getDateTime());*/

			/*String s=batchSend(new NameValuePair[] {
					new NameValuePair("account", account),
					new NameValuePair("pswd", pswd),
					new NameValuePair("msg", URLEncoder.encode(msg)),
					new NameValuePair("params", URLEncoder.encode(String.format(params,"13552357227","KDJFDKEIOEKDUKF","http://sep9.cn/tigi02"))),
					new NameValuePair("needstatus", "true") });
			System.out.println(s);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String url="http://smsbj1.253.com/msg/send/json";
    private static String un = "N6898765";
    private static String pw = "QwoWSCcLyg561f";
    private static String sign = "【积分兔子】"; 	// 短信签名
    private static String duration = "10";
    private static String context = "%s%s于%s支付了%s元，成功购买商品%s";
    private static String contextCheckNo = "%s您的验证码是%s，%s分钟内有效。如非本人操作，请忽略本短信。";
    private static String contextLoanApply = "%s于%s申请贷款%s元，联系电话%s";
//    private static void InitSend() {
//        List<HashMap<String, String>> maps=commonDao.getDictByDictType("vcode");
//        if(maps!=null){
//            for (int i = 0; i < maps.size(); i++) {
//                if (maps.get(i).get("dict_key").equals("url")) {
//                    url = maps.get(i).get("dict_value");
//                } else if (maps.get(i).get("dict_key").equals("un")) {
//                    un = maps.get(i).get("dict_value");
//                } else if (maps.get(i).get("dict_key").equals("pw")) {
//                    pw = maps.get(i).get("dict_value");
//                } else if (maps.get(i).get("dict_key").equals("sign")) {
//                    sign = maps.get(i).get("dict_value");
//                } else if (maps.get(i).get("dict_key").equals("context")) {
//                    context = maps.get(i).get("dict_value");
//                } else if (maps.get(i).get("dict_key").equals("duration")) {
//                    duration = maps.get(i).get("dict_value");
//                }
//            }
//        }
//    }

    /** 短信发送 Mobile:手机号码，多个用逗号分隔;vcode：验证码 */
    public static boolean Send(String Mobile, String userId, String timestr, String cost, String title){
        return Send(Mobile, userId, timestr,cost,title,5);
    }

    /** 短信发送 Mobile:手机号码，多个用逗号分隔;vcode：验证码;发送失败重发次数*/
    @SuppressWarnings("deprecation")
    public static boolean Send(String Mobile, String userId, String timestr, String cost, String title, Integer repeatNum) {
        logger.info(String.format("send vcode start,phone:%s", Mobile));
        if (Mobile == null || Mobile == "") {
            logger.error("发送短信：手机号码为空,无法发送。");
            return false;
        }

        //InitSend();
        logger.info("s:" + DateUtils.getDateTime());
        com.uway.sports.common.utils.HttpClient httpClient = new com.uway.sports.common.utils.HttpClient();
        Integer index = 1;
        String result = null;
        try {
            SmsSendRequest smsSingleRequest = new SmsSendRequest(un, pw, URLEncoder.encode(String.format(context, sign, userId, timestr,cost,title),"utf-8"), Mobile,"true");

            String requestJson = JSON.toJSONString(smsSingleRequest);

            System.out.println("before request string is: " + requestJson);

            result = httpClient.sendSmsByPost(url, requestJson);

            logger.info("url:" + url);
            logger.info("sendmsg--response:" + result);

            logger.info("返回结果:" + result);

            while (index <= repeatNum) {
                if (result == null && index <= repeatNum) {

                    Thread.sleep(3000);
                    result = httpClient.sendSmsByPost(url, requestJson);
                    if (result != null) {
                        break;
                    }
                    index++;
                }else{
                    break;
                }
            }
        } catch (Exception e) {
            logger.error(String.format("send vcode error,phone:%s,error:", Mobile, e.getMessage()));
        }

        if (result == null) {
            logger.info(String.format("send vcode phone:%s failed", Mobile));
            return false;
        } else {
            String[] arr = result.split("\n");
            String[] tmp = arr[0].split(",");
            logger.info(String.format("send vcode phone:%s finished", Mobile));
            return tmp[1].equals("0") ? true : false;
        }
    }

    /**
     * @param  nameValuePairs
     * @return 返回值定义参见HTTP协议文档
     * @throws Exception
     */
    public static String batchSend(String url, NameValuePair[] nameValuePairs) throws Exception {
        HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        GetMethod method = new GetMethod();
        try {
            URI base = new URI(url, false);
            method.setURI(new URI(base, "send", false));
            method.setQueryString(nameValuePairs);
            int result = client.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                return URLDecoder.decode(baos.toString(), "UTF-8");
            } else {
                throw new Exception("HTTP ERROR Status: "
                        + method.getStatusCode() + ":" + method.getStatusText());
            }
        } finally {
            method.releaseConnection();
        }
    }



    /** 发送短信验证码 Mobile:手机号码，多个用逗号分隔;vcode：验证码 */
    public static boolean SendCheckNo(String mobile, String vcode){
        String sendContext = String.format(contextCheckNo,sign,vcode,duration);
        return Send(mobile, sendContext, 5);
    }

    /** 发送短信通知（有人申请贷款） Mobile:手机号码，多个用逗号分隔;vcode：验证码 */
    public static boolean SendLoanApplyNotify(String mobile,String applyName,String applyDate,int applyValue,String applyPhone){
        String sendContext = String.format(contextLoanApply,applyName,applyDate,applyValue,applyPhone);
        return Send(mobile, sendContext, 5);
    }

    /** 短信发送 Mobile:手机号码，多个用逗号分隔;vcode：验证码;发送失败重发次数*/
    @SuppressWarnings("deprecation")
    public static boolean Send(String mobile,String sendContext, Integer repeatNum) {
        logger.info(String.format("send vcode start,phone:%s", mobile));
        if (mobile == null || mobile == "") {
            logger.error("发送短信：手机号码为空,无法发送。");
            return false;
        }
        logger.info("s:" + DateUtils.getDateTime());
        com.uway.sports.common.utils.HttpClient httpClient = new com.uway.sports.common.utils.HttpClient();
        Integer index = 1;
        String result = null;
        try {
            SmsSendRequest smsSingleRequest = new SmsSendRequest(un, pw, URLEncoder.encode(sendContext,"utf-8"), mobile,"true");
            String requestJson = JSON.toJSONString(smsSingleRequest);
            System.out.println("before request string is: " + requestJson);
            result = httpClient.sendSmsByPost(url, requestJson);
            logger.info("url:" + url);
            logger.info("sendmsg--response:" + result);
            logger.info("返回结果:" + result);
            while (index <= repeatNum) {
                if (result == null && index <= repeatNum) {
                    Thread.sleep(3000);
                    result = httpClient.sendSmsByPost(url, requestJson);
                    if (result != null) {
                        break;
                    }
                    index++;
                }else{
                    break;
                }
            }
        } catch (Exception e) {
            logger.error(String.format("send vcode error,phone:%s,error:", mobile, e.getMessage()));
        }
        if (result == null) {
            logger.info(String.format("send vcode phone:%s failed", mobile));
            return false;
        } else {
            String[] arr = result.split("\n");
            String[] tmp = arr[0].split(",");
            logger.info(String.format("send vcode phone:%s finished", mobile));
            return tmp[1].equals("0") ? true : false;
        }
    }


}
