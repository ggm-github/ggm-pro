package com.uway.sports.common.config;

import com.uway.sports.common.config.properties.RedisProperties;
import com.uway.sports.common.redis.SharedRedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class RedisConfig {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RedisProperties redisProperties;
	@Autowired
	private JedisPoolConfig jedisConfig;

	@Autowired
	private ShardedJedisPool shardedJedisPool;

	@Bean
	public JedisPoolConfig jedisConfig(){
		JedisPoolConfig cfg = new JedisPoolConfig();
		//最大连接数
		cfg.setMaxTotal(redisProperties.getMaxTotal()); 
		//pool中最多有多少个状态为idle(空闲的)的jedis实例
		cfg.setMaxIdle(redisProperties.getMaxIdle());
		cfg.setMinIdle(redisProperties.getMinIdle());
		cfg.setMaxWaitMillis(redisProperties.getMaxWaitMillis()); //最大等待wait时间 
		cfg.setNumTestsPerEvictionRun(redisProperties.getNumTestsPerEvictionRun()); //一次最多收回jedis实例的个数
		cfg.setTimeBetweenEvictionRunsMillis(redisProperties.getTimeBetweenEvictionRunsMillis()); //多久回收一次
		cfg.setTestOnBorrow(true); //获得一个jedis实例的时候是否检查连接可用性（ping）
		cfg.setTestOnReturn(true); //return 一个jedis实例给pool时，是否检查连接可用性（ping）
		cfg.setTestWhileIdle(true);//idle状态监测用异步线程evict进行检查		
		cfg.setBlockWhenExhausted(true); //阻塞住，或者达到maxWait时抛出JedisConnectionException
		return cfg;
	}
	
	
	@Bean
	public ShardedJedisPool shardedJedisPool() throws URISyntaxException {
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		List<String> uriList = redisProperties.getUriList();
		for(String uri : uriList){
			logger.debug("========redis.uri={}", uri);
			JedisShardInfo shardInfo = new JedisShardInfo(new URI(uri));  //默认权重为1
			shardInfo.setConnectionTimeout(redisProperties.getTimeout());
			shardInfo.setSoTimeout(redisProperties.getTimeout());
			shards.add(shardInfo);
		}	
		//将不同的key分配到不同的redis server上
		return new ShardedJedisPool(jedisConfig,shards, Hashing.MURMUR_HASH,
				Sharded.DEFAULT_KEY_TAG_PATTERN);
	} 
	
	
	@Bean
	public SharedRedisClient redisClient(){
		return  new  SharedRedisClient(shardedJedisPool);
	}	     	
	
}

