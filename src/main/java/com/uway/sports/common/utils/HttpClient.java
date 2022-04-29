package com.uway.sports.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class HttpClient {

    //表示请求器是否已经做了初始化工作
    private boolean hasInit = false;

    // 读取超时 60分钟
    private int socketTimeOut = 3600000;
    // 连接超时 10秒
    private int connTimeOut = 10000;
    // 每个HOST的最大连接数量
    private int maxConnPreHost = 10;
    // 连接池的最大连接数
    private int maxConn = 10;
    // 连接池
    private PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    // client of HTTP
    public CloseableHttpClient httpclient = null;
    // request configuration
    private RequestConfig defaultRequestConfig = null;

    public HttpClient() {
        init();
    }

    public static class HttpResult {
        public int code = 0;
        public String out = null;

        HttpResult(int code, String out) {
            this.code = code;
            this.out = out;
        }
    }

    public void setTimeOut(int timeOut) {
        this.socketTimeOut = timeOut;
    }

    public void setMaxConn(int maxConn) {
        this.maxConn = maxConn;
    }

    public void setMaxConnPreHost(int maxConnPreHost) {
        this.maxConnPreHost = maxConnPreHost;
    }

    private void init() {
        cm.setMaxTotal(maxConn);
        cm.setDefaultMaxPerRoute(maxConnPreHost);
        httpclient = HttpClients.custom().setConnectionManager(cm)
                .evictExpiredConnections()
                .evictIdleConnections(5L, TimeUnit.SECONDS).build();

        // Create global request configuration
        defaultRequestConfig = RequestConfig.DEFAULT;
        defaultRequestConfig = RequestConfig.copy(defaultRequestConfig)
                .setSocketTimeout(socketTimeOut).setConnectTimeout(connTimeOut)
                .setConnectionRequestTimeout(connTimeOut).build();

        hasInit = true;
    }

    public void close() {
        if (httpclient != null) {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T requestString(HttpUriRequest request, T objT) {
        try {
            Class<?> classType = objT.getClass();
            CloseableHttpResponse response = httpclient.execute(request);
            try {
                String responseText = EntityUtils.toString(response.getEntity());

                @SuppressWarnings("unchecked")
                T result = (T) JSONObject.parseObject(responseText, classType);
                return result;
            } catch (Exception e) {
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String requestString(HttpUriRequest request) {
        try {
            CloseableHttpResponse response = httpclient.execute(request);
            try {
                String responseText = EntityUtils.toString(response.getEntity());
                return responseText;
            } catch (Exception e) {
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HttpUriRequest getHttpGet(String url, Map<String, String> map) {
        if (map != null && map.size() != 0) {
            StringBuffer sb = new StringBuffer();
            sb.append(url);
            boolean first = true;
            for (Entry<String, String> pair : map.entrySet()) {
                if (first) {
                    sb.append(String.format("?%s=%s", pair.getKey(), pair.getValue()));
                    first = false;
                } else {
                    sb.append(String.format("&%s=%s", pair.getKey(), pair.getValue()));
                }
            }
            url = sb.toString();
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(defaultRequestConfig);
        return httpGet;
    }

    public HttpUriRequest getHttpPost(String url, Map<String, String> map) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(defaultRequestConfig);
        if (map != null && map.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Entry<String, String> pair : map.entrySet()) {
                nvps.add(new BasicNameValuePair(pair.getKey(), pair.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        }
        return httpPost;
    }

    public String doHttpGet(String url, Map<String, String> map) {
        return requestString(getHttpGet(url, map));
    }

    public String doHttpPost(String url, Map<String, String> map) {
        try {
            return requestString(getHttpPost(url, map));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public <T> T doHttpGet(String url, Map<String, String> map, T objT) {
        return requestString(getHttpGet(url, map), objT);
    }

    public <T> T doHttpPost(String url, Map<String, String> map, T objT) {
        try {
            return requestString(getHttpPost(url, map), objT);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public String sendPost(String url, String xmlstr) {

        if (!hasInit) {
            init();
        }

        String result = null;
        HttpPost httpPost = new HttpPost(url);

        // 将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xmlstr;
        System.out.println("API，POST过去的数据是：");
        System.out.println(postDataXML);

        // 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        // 设置请求器的配置
        httpPost.setConfig(defaultRequestConfig);
        System.out.println("executing request" + httpPost.getRequestLine());

        try {
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ConnectionPoolTimeoutException e) {
            System.out.println("http get throw ConnectionPoolTimeoutException(wait time out)");
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            System.out.println("http get throw ConnectTimeoutException");
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            System.out.println("http get throw SocketTimeoutException");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("http get throw Exception");
            e.printStackTrace();
        } finally {
            httpPost.abort();
        }

        return result;
    }


    /**
     * @param path
     * @param postContent
     * @return String
     * @throws
     * @author tianyh
     * @Description
     */
    public static String sendSmsByPost(String path, String postContent) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            httpURLConnection.setConnectTimeout(10000);//连接超时 单位毫秒
            httpURLConnection.setReadTimeout(10000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            httpURLConnection.connect();
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(postContent.getBytes("UTF-8"));
            os.flush();

            StringBuilder sb = new StringBuilder();
            int httpRspCode = httpURLConnection.getResponseCode();
            if (httpRspCode == HttpURLConnection.HTTP_OK) {
                // 开始获取数据
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return sb.toString();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String MaptoString(Map<String, String> params) {
        Set<String> keySet = params.keySet();
        String[] keysArr = keySet.toArray(new String[0]);
        Arrays.sort(keysArr);//对键进行排序
        StringBuilder signedContent = new StringBuilder();
        for (int i = 0; i < keysArr.length; i++) {//将字典集合转换为URL参数对
            signedContent.append(keysArr[i]).append("=").append(params.get(keysArr[i])).append("&");
        }
        String signedContentStr = signedContent.toString();
        if (signedContentStr.endsWith("&"))
            signedContentStr = signedContentStr.substring(0, signedContentStr.length() - 1);
        return signedContentStr;
    }

    /**
     * 卡门网对接使用的post请求
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String KMsendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result  += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
