package com.uway.sports.common.utils;

import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 天御服务API工具类
 */
class BspAPI {
    /* Basic request URL */
    private static final String URL = "csec.api.qcloud.com/v2/index.php";

    /**
    * 编码
    * @param bstr
    * @return String
    */
    private static String encode(byte[] bstr){
        return new BASE64Encoder().encode(bstr).replaceAll("\r|\n","");
    }

    /* Signature algorithm using HMAC-SHA1 */
    public static String hmacSHA1(String key, String text, String charset) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException
    {
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(key.getBytes(charset), "HmacSHA1"));
        return encode(mac.doFinal(text.getBytes(charset)));
    }

    /* Assemble query string */
    public static String makeQueryString(Map<String, String> args, String charset) throws UnsupportedEncodingException
    {
        String url = "";

        for (Map.Entry<String, String> entry : args.entrySet())
            url += entry.getKey() + "=" + (charset == null ? entry.getValue() : URLEncoder.encode(entry.getValue(), charset)) + "&";

        return url.substring(0, url.length()-1);
    }

    public static String getURL(boolean isHttps)
    {
        // 指定host需要使用http，否则会报java.security.cert.CertificateException
        return isHttps ? "http://" + URL : "http://" + URL;
    }

    /* Build query parameters*/
    public static SortedMap<String, String> buildParameters(
        String method,
        String action,
        String region,
        String secretId,
        String secretKey,
        Map<String, String> args,
        String charset)
    throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException
    {
        SortedMap<String, String> arguments = new TreeMap<String, String>();

        /* Sort all parameters, then calculate signature */
        arguments.putAll(args);
        arguments.put("Nonce", String.valueOf((int)(Math.random() * 0x7fffffff)));
        arguments.put("Action", action);
        arguments.put("Region", region);
        arguments.put("SecretId", secretId);
        arguments.put("Timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        arguments.put("Signature", hmacSHA1(secretKey, String.format("%s%s?%s", method, URL, makeQueryString(arguments, null)), charset));

        return arguments;
    }

    /* Message-Struct constructor for API `UgcAntiSpam` */
    public abstract static class Message
    {
        /* Basic TLV structure */
        public abstract static class TLV
        {
            /* The "T" and "V" part of "TLV" */
            private final int    type;
            private final String value;

            public TLV(int type)
            {
                this(type, "");
            }

            public TLV(int type, String value)
            {
                this.type  = type;
                this.value = value;
            }

            /* Assemble a TLV element */
            public byte[] assemble() throws UnsupportedEncodingException
            {
                byte[] bytes = value.getBytes("utf-8");
                byte[] result = new byte[bytes.length + 8];

                /* Type */
                result[0] = (byte)((type & 0xff000000) >>> 24);
                result[1] = (byte)((type & 0x00ff0000) >>> 16);
                result[2] = (byte)((type & 0x0000ff00) >>>  8);
                result[3] = (byte)((type & 0x000000ff) >>>  0);

                /* Length */
                result[4] = (byte)((bytes.length & 0xff000000) >>> 24);
                result[5] = (byte)((bytes.length & 0x00ff0000) >>> 16);
                result[6] = (byte)((bytes.length & 0x0000ff00) >>>  8);
                result[7] = (byte)((bytes.length & 0x000000ff) >>>  0);

                /* Value */
                System.arraycopy(bytes, 0, result, 8, bytes.length);
                return result;
            }
        }

        /* Message items of the Message-Struct */
        public static class Content extends TLV
        {
            public Content()             { super(1);        }
            public Content(String value) { super(1, value); }
        }

        public static class ImageURL extends TLV
        {
            public ImageURL()             { super(2);        }
            public ImageURL(String value) { super(2, value); }
        }

        public static class VideoURL extends TLV
        {
            public VideoURL()             { super(3);        }
            public VideoURL(String value) { super(3, value); }
        }

        public static class AudioURL extends TLV
        {
            public AudioURL()             { super(5);        }
            public AudioURL(String value) { super(4, value); }
        }

        public static class WebsiteURL extends TLV
        {
            public WebsiteURL()             { super(5);        }
            public WebsiteURL(String value) { super(5, value); }
        }

        public static class Emoticon extends TLV
        {
            public Emoticon()             { super(6);        }
            public Emoticon(String value) { super(6, value); }
        }

        public static class Title extends TLV
        {
            public Title()             { super(7);        }
            public Title(String value) { super(7, value); }
        }

        public static class Location extends TLV { public Location() { super(8); } }
        public static class Custom   extends TLV { public Custom()   { super(9); } }
        public static class File     extends TLV { public File()     { super(10); } }
        public static class Other    extends TLV { public Other()    { super(1000); } }

        /* Factory method of Message-Struct */
        public static String build(TLV ... items) throws UnsupportedEncodingException
        {
            int length = 0;
            ArrayList<byte[]> parts = new ArrayList<byte[]>();

            /* Assemble each part */
            for (TLV item : items)
            {
                byte[] part = item.assemble();

                parts.add(part);
                length += part.length;
            }

            int current = 0;
            byte[] result = new byte[length];

            /* Copy to result */
            for (byte[] part : parts)
            {
                System.arraycopy(part, 0, result, current, part.length);
                current += part.length;
            }

            /* Encode using Base64 */
            return encode(result);
        }
    }
}


/**
 * Http 请求工具类
 */
class ApiRequest {
    /**
     * Get 请求
     *
     * @param url    http请求地址
     * @param params http请求参数
     * @return String
     */
    public static ApiResponse sendGet(String url, String params) {
        BufferedReader br = null;
        ApiResponse response = new ApiResponse();
        try {
            String urlNameString = url;
            if(0 != params.length()) {
                String linkOperator = urlNameString.contains("?") ? "&" : "?";
                urlNameString += linkOperator + params;
            }
            // 允许使用显示头部, 设置Host
            System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection)new URL(urlNameString).openConnection();

            // 设置请求头
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 设置Host
            //connection.setRequestProperty("Host","csec.api.qcloud.com");

            // 获取请求头字段
            Map<String, List<String>> header = connection.getHeaderFields();

            // 获取URL的输入流，读取请求响应
            String body = readString(connection.getInputStream());
            response.setHeader(header);
            response.setBody(body);

        } catch (Exception e) {
            System.out.println("发送GET请求出现异常!" + e);
            e.printStackTrace();
        }

        return response;
    }

    /**
     * POST 请求
     *
     * @param url    http请求地址
     * @param params http请求参数
     * @return String
     */
    public static ApiResponse sendPost(String url, String params) {
        PrintWriter pw = null;
        BufferedReader br = null;
        ApiResponse response = new ApiResponse();
        try {
            HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();

            // 设置请求头
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 设置 POST
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // Post 请求不能使用缓存
            connection.setUseCaches(false);

            connection.setConnectTimeout(5000);

            // 获取URL的输出流, 发送请求参数
            pw = new PrintWriter(connection.getOutputStream());
            pw.print(params);
            pw.flush();

            // 获取请求头字段
            Map<String, List<String>> header = connection.getHeaderFields();

            // 获取URL的输入流，读取请求响应
            String body = readString(connection.getInputStream());

            response.setHeader(header);
            response.setBody(body);

        } catch (Exception e) {
            System.out.println("发送POST请求出现异常!" + e);
            e.printStackTrace();
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }


    private static String readString(InputStream is) {
        BufferedReader br = null;
        String content = "";
        try {
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                content += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}

/**
 * Http 请求响应
 */
class ApiResponse {
    private Map header;
    private Object body;

    public ApiResponse() {
        this.header = new TreeMap();
        this.body = "";
    }

    public ApiResponse(Map header, String body) {
        this.header = header;
        this.body = body;
    }

    public void setHeader(Map header) {
        this.header = header;
    }

    public Map getHeader() {
        return this.header;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Object getBody() {
        return this.body;
    }
}

/* Demo section */
public class UgcAntiSpam {

//    public static void main(String[] argv) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException
//    {
//        /* 密钥,请进行替换,密钥申请地址 https://console.qcloud.com/capi  */
//        final String SECRET_ID = "AKIDvdBUaTVBwU0Rjt5Vw76tZ0DBvXR7Zwzn";
//        final String SECRET_KEY = "jETZVIvOegXDxUnxylJl001rS5UdhXDe";
//
//        Map<String, String> args = new TreeMap<String, String>();
//
//        /* 帐号信息参数 */
//        args.put("accountType", "7");
//        args.put("uid", "100009624769");
//        args.put("associateAccount","373909726");
//        args.put("nickName", "helloword");
//        args.put("phoneNumber", "086+15166666666");
//        args.put("emailAddress", "hellword@qq.com");
//        args.put("registerTime", "1440416972");
//        args.put("registerIp", "121.14.96.121");
//
//        /* 消息内容 */
//        args.put("messageStruct", BspAPI.Message.build(new BspAPI.Message.Content("法轮功大法好")));
//        args.put("messageId","ieafdasfk");
//
//        /* 行为信息 */
//        args.put("postIp","221.123.137.2");
//        args.put("postTime","1436664316");
//        args.put("loginSource","4");
//        args.put("loginType","3");
//
//        /* 设备信息 */
//        args.put("macAddress","00-05-9A-3C-7A-00");
//        args.put("vendorId","tencent.com");
//        args.put("imei","54654654646");
//
//        /* 其他信息 */
//        args.put("businessId","1");
//        /** GET 请求 */
//        // SortedMap<String, String> params = BspAPI.buildParameters("GET", "ContentSecurity.Text.AntiSpam", "gz", SECRET_ID, SECRET_KEY, args, "utf-8");
//        // String queryStr = BspAPI.makeQueryString(params, "utf-8");
//        // String url = BspAPI.getURL(true) + "?" + queryStr;
//        // ApiResponse res = ApiRequest.sendGet(url, "");
//
//        /** POST 请求 */
//        SortedMap<String, String> params = BspAPI.buildParameters("POST", "ContentSecurity.Text.AntiSpam", "gz", SECRET_ID, SECRET_KEY, args, "utf-8");
//        String queryStr = BspAPI.makeQueryString(params, "utf-8");
//        String url = BspAPI.getURL(true);
//        ApiResponse res = ApiRequest.sendPost(url, queryStr);
//        System.out.println(res.getBody());
//    }

    public static boolean checktext(String secretId,String secretKey,String text,String ip)
    {
        /* 密钥,请进行替换,密钥申请地址 https://console.qcloud.com/capi  */
        final String SECRET_ID = secretId;
        final String SECRET_KEY = secretKey;

        Map<String, String> args = new TreeMap<String, String>();

        /* 帐号信息参数 */
        args.put("accountType", "7");
        args.put("uid", "100009624769");
        /* 消息内容 */
        try {
            args.put("messageStruct", BspAPI.Message.build(new BspAPI.Message.Content(text)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /* 行为信息 */
        args.put("postIp",ip);
        /** GET 请求 */
        // SortedMap<String, String> params = BspAPI.buildParameters("GET", "ContentSecurity.Text.AntiSpam", "gz", SECRET_ID, SECRET_KEY, args, "utf-8");
        // String queryStr = BspAPI.makeQueryString(params, "utf-8");
        // String url = BspAPI.getURL(true) + "?" + queryStr;
        // ApiResponse res = ApiRequest.sendGet(url, "");

        /** POST 请求 */
        SortedMap<String, String> params = null;
        try {
            params = BspAPI.buildParameters("POST", "ContentSecurity.Text.AntiSpam", "gz", SECRET_ID, SECRET_KEY, args, "utf-8");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String queryStr = null;
        try {
            queryStr = BspAPI.makeQueryString(params, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = BspAPI.getURL(true);
        ApiResponse res = ApiRequest.sendPost(url, queryStr);
        System.out.println(res.getBody());
        JSONObject jsonObject = JSONUtils.formJson(res.getBody().toString());

        if(jsonObject.get("level") != null && Integer.parseInt(jsonObject.get("level").toString())==0){
            return true;
        }
        return false;
    }

}