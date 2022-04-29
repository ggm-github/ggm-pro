package com.uway.sports.web.payUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

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
import com.alibaba.fastjson.JSONObject;

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
		if (map != null && map.size() == 0) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Entry<String, String> pair : map.entrySet()) {
				nvps.add(new BasicNameValuePair(pair.getKey(), pair.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		}
		return new HttpPost(url);
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

		if(!hasInit){
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
}
