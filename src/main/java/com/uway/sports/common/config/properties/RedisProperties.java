package com.uway.sports.common.config.properties;

import com.uway.sports.common.config.Global;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class RedisProperties {

    private int timeout = Integer.valueOf(Global.getConfig("redis.timeout"));
	
	private int maxTotal= Integer.valueOf(Global.getConfig("redis.maxTotal"));
	
	private int maxIdle= Integer.valueOf(Global.getConfig("redis.maxIdle"));
	
	private int minIdle= Integer.valueOf(Global.getConfig("redis.minIdle"));
	
	private long maxWaitMillis= Long.valueOf(Global.getConfig("redis.maxWaitMillis"));
	
	private int numTestsPerEvictionRun= Integer.valueOf(Global.getConfig("redis.numTestsPerEvictionRun"));
	
	private long timeBetweenEvictionRunsMillis= Long.valueOf(Global.getConfig("redis.timeBetweenEvictionRunsMillis"));
	
	private List<String> uriList = new ArrayList<String>();

	
	
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public long getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public int getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public List<String> getUriList() {
		String uriliststr = Global.getConfig("redis.uriList");
		String urls[] = uriliststr.split(";");

		uriList = java.util.Arrays.asList(urls);
		return uriList;
	}

	public void setUriList(List<String> uriList) {
		this.uriList = uriList;
	}
	
			

	
}
