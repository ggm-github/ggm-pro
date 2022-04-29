package com.uway.sports.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;


public class HttpRequestUtils {
	
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);    //日志记录
   
    static PoolingHttpClientConnectionManager cm = null;
    static{
	    LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(800);
        cm.setDefaultMaxPerRoute(50);  
    }
    
    public  static CloseableHttpClient getHttpClient() {
    	RequestConfig config = RequestConfig.custom()
    			.setConnectTimeout(15000) //连接超时时间
    			.setSocketTimeout(15000)  //等待数据超时时间
    			.setConnectionRequestTimeout(10000) //从连接池获取连接的超时时间
    			.build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(config)
                .build();  
        
        /*CloseableHttpClient httpClient = HttpClients.createDefault();//如果不采用连接池就是这种方式获取连接*/
        return httpClient;
    }

    
 
 
    /**
     * 发送get请求
     * @param url 路径
     * @return
     */
    public static String httpGet(String url){
    	CloseableHttpResponse response = null;
        try {
        	//httpclient = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            response = getHttpClient().execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == 200 
            		|| response.getStatusLine().getStatusCode() == 202 
            		|| response.getStatusLine().getStatusCode() == 302 ) {
            	   HttpEntity entity = response.getEntity();
            	   return EntityUtils.toString(entity);
            } else {
                logger.error("get请求提交失败:" + url);
                return null;
            }
        } 
        catch (Exception e) {
            //logger.error("get请求提交失败:" + url);
            return null;
        }finally{
        	if(response!=null){
        		try {
        			response.close();
    			} 
            	catch (IOException e) {
    			}
        	}  
        }
    }
    
    
    
    /**
     * 发送get请求
     * @param url 路径
     * @param encoding 编码
     * @return
     */
    public static String httpGet(String url, String encoding){
    	CloseableHttpResponse response = null;
        try {
        	//httpclient = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            response = getHttpClient().execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == 200) {
            	   HttpEntity entity = response.getEntity();
            	   return EntityUtils.toString(entity,encoding);
            } else {
                //logger.error("get请求提交失败:" + url);
                return null;
            }
        } 
        catch (Exception e) {
            //logger.error("get请求提交失败:" + url);
            return null;
        }finally{
        	if(response!=null){
        		try {
        			response.close();
    			}
            	catch (IOException e) {
    			}
        	}     
        }
    }
    
    
    /**
     * 发送get请求，只取状态码，不管响应内容
     * @param url 路径
     * @return
     */
    public static boolean  httpGetNoResult(String url){
    	CloseableHttpResponse response = null;
        try {
        	//httpclient = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            response = getHttpClient().execute(request);
            /**请求发送成功，并得到响应**/
            if(response.getStatusLine().getStatusCode() == 200  || response.getStatusLine().getStatusCode() == 302 ) {
            	return true;
            } 
            else {
                //logger.error("get请求提交失败:" + url);
                return false;
            }
        }
        catch (Exception e) {
            //logger.error("get请求提交失败:" + url); 
            return false;
        }finally{
        	if(response!=null){
        		try {
        			response.close();
    			}
            	catch (IOException e) {
    			}
        	}     
        }
    }
    
    
    
    /**
     * 发送POST请求
     * @param url 路径
     * @return
     */
    public static String httpPost(String url,String param){
    	CloseableHttpResponse response = null;
    	String fullUrl = url;	
        try {
        	//httpclient = HttpClients.createDefault();
            //发送get请求
            HttpPost request = new HttpPost(url);
            if(param!=null && !"".equals(param.trim())){
            	if(url.indexOf("?")==-1){
            		fullUrl = url+"?"+param;	
            	}
            	else{
            		fullUrl = url+"&"+param;	
            	}     	    	
     	    	HttpEntity strEntity = new StringEntity(param,"UTF-8");
     	    	request.setEntity(strEntity);
     	    }  
            response = getHttpClient().execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == 200) {
            	   HttpEntity entity = response.getEntity();
            	   return EntityUtils.toString(entity);
            } else {
                //logger.error("post请求提交失败:" + fullUrl);
                return null;
            }
        } 
        catch (Exception e) {
            logger.error("post请求提交失败:" + fullUrl);
            return null;
        }finally{
        	if(response!=null){
        		try {
        			response.close();
    			}
            	catch (IOException e) {
    			}
        	}     
        }
    }
    
    
    public static String httpPost(String url, Map<String, String> parameterMap){
    	CloseableHttpResponse response = null;
        try {
        	//httpclient = HttpClients.createDefault();
            //发送get请求
            HttpPost request = new HttpPost(url);
            List<BasicNameValuePair> parameterList = new ArrayList<BasicNameValuePair>();
	      	Set<Entry<String,String>> entrySet = parameterMap.entrySet();
	      	for(Iterator<Entry<String,String>> it = entrySet.iterator(); it.hasNext(); ){
	      		Entry<String,String> entry = it.next();
	      		parameterList.add( new BasicNameValuePair(entry.getKey(), entry.getValue() ) );
	      	}        	
	      	HttpEntity formEntity = new UrlEncodedFormEntity(parameterList, "UTF-8");
	      	request.setEntity(formEntity);
	      	response = getHttpClient().execute(request);
	        if(response.getStatusLine().getStatusCode()== 200){
	        	HttpEntity entity = response.getEntity();
        		return EntityUtils.toString(entity, "UTF-8");
        	}
        	else{  
        		logger.error("post请求提交失败:" +url+ ", parameterMap="+parameterMap.toString()+", HTTP StatusCode="
        		                 +response.getStatusLine().getStatusCode());
                return null;
		    }
        } 
        catch (Exception e) {
            logger.error("post请求提交失败:" +url+ ", parameterMap="+parameterMap.toString() );
            return null;
        }finally{
        	if(response!=null){
        		try {
        			response.close();
    			}
            	catch (IOException e) {
    			}
        	}        	
        }
    }
      
    
    /**
     * 使用代理IP发送Get请求
     * 发送get请求
     * @param url 路径
     * @param encoding 编码
     * @return
     */
    public static String httpGetWithProxy(String proxyIp, int proxyPort,String url, String encoding){
    	CloseableHttpResponse response = null;
        try {
        	//httpclient = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpHost proxy = new HttpHost(proxyIp, proxyPort);
            RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
            request.setConfig(requestConfig);
            request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
            response = getHttpClient().execute(request);
            /**请求发送成功，并得到响应**/
            if(response.getStatusLine().getStatusCode() == 200) {
            	   HttpEntity entity = response.getEntity();
            	   return EntityUtils.toString(entity,encoding);
            } 
            else{
                //logger.error("get请求提交失败:" + url+",http.code = "+response.getStatusLine().getStatusCode());
                return null; 
            }
        } 
        catch (Exception e) {
            //logger.error("get请求提交失败:" + url);
            return null;
        }finally{
        	if(response!=null){
        		try {
        			response.close(); 
        		}
            	catch (IOException e) {             		
            	}
        	}
        }
    }
    

}