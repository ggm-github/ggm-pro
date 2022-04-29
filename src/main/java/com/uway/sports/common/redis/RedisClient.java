package com.uway.sports.common.redis;

import com.uway.sports.common.utils.StringUtils;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * redis客户端
 *
 */
public class RedisClient {
	/**
	 * jedis对象池
	 */
	private JedisPool jedisPool;

	/**
	 * redis编码
	 */
	private String charSet="UTF-8";
	
	
	private static Logger logger= Logger.getLogger(RedisClient.class);
	
	
	public RedisClient(final JedisPoolConfig poolConfig, final String host, final int port, final String password){
        try{  
        	
        	if(StringUtils.isEmpty(password)){
        		jedisPool = new JedisPool(poolConfig, host, port);
        	}else{
        		jedisPool = new JedisPool(poolConfig, host, port, 60000, password);
        	}
        }catch(Exception e){  
            logger.error("Jedis Pool init failed:", e);  
        }  
    } 
	
	
	
	
	
	/**
	 * 获取字符串
	 * @param key
	 * @return
	 */
	public String get(String key){
		Jedis jedis = getJedis();
		try{
			return jedis.get(key);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}
	
	/**
	 * 设置字符串
	 * @param key
	 * @param value
	 */
	public String set(String key,String value){
		
		Jedis jedis = getJedis();
		try{
			return jedis.set(key, value);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
		
	}
	
	public Long set(String key,String value, int seconds){
		
		Jedis jedis = getJedis();
		try{
			jedis.set(key, value);
			return jedis.expire(key, seconds);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}
	//设置过期时间点，精确到秒
	public Long set(String key,String value, long unixTime){
		
		Jedis jedis = getJedis();
		try{
			jedis.set(key, value);
			return jedis.expireAt(key, unixTime);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}
	
	public Long setObject(String key,Object obj, int seconds){
		
		Jedis jedis = getJedis();
		try{
			byte[] bKey=key.getBytes(charSet);
			byte[] bValue=SerializeUtil.serialize(obj);
			jedis.set(bKey, bValue);
			return jedis.expire(key, seconds);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}
	
	public Long setObject(String key,Object obj, Long unixTime){
		
		Jedis jedis = getJedis();
		try{
			byte[] bKey=key.getBytes(charSet);
			byte[] bValue=SerializeUtil.serialize(obj);
			jedis.set(bKey, bValue);
			return jedis.expireAt(key, unixTime);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}
	
	/**
	 * 获取字符串
	 * @param key	
	 * @return
	 */
	public Object getObject(String key){
		Jedis jedis = getJedis();
		try {
			byte[] bKey=key.getBytes(charSet);
			byte[] bRes=jedis.get(bKey);
			if(bRes!=null){
				return SerializeUtil.unserialize(bRes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		
		return null;
	}
	
	/**
	 * 设置字符串
	 * @param key	
	 * @param value	可序列化
	 */
	public String setObject(String key,Object value){
		Jedis jedis = getJedis();
		try {
			byte[] bKey=key.getBytes(charSet);
			byte[] bValue=SerializeUtil.serialize(value);
			
			return jedis.set(bKey, bValue);
			
//			return (String)handle("set",bKey, bValue);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		
		return null;
	}
	
	
	/**
	 * 删除
	 * @param keys
	 * @return
	 */
	public Long del(String... keys){
		
		Jedis jedis = getJedis();
		try {
			return jedis.del(keys);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}
	
	/**
	 * 设置生存时间
	 * @param key
	 * @param seconds
	 */
	public Long expire(String key,int seconds){
		Jedis jedis = getJedis();
		return jedis.expire(key, seconds);
	}
	
	/**
	 * 获取jedis对象
	 * @return
	 */
	public Jedis getJedis(){
		Jedis jedis=jedisPool.getResource();
		return jedis;
	}
	
	/**
	 * 释放jedis资源
	 * @param jedis
	 */
	public void returnJedis(Jedis jedis){
		if(jedis!=null){
			jedisPool.returnResource(jedis);
		}
	}
	
	public boolean isExistsByKey(String key){
		Jedis jedis = getJedis();
		try {
			return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return false;
	}
	
	/**
	 * 设置hash
	 * @param hkey  hash的key
	 * @param key   key
	 * @param value
	 * @param unixTime
	 * @return
	 */
	public Long hset(String hkey, String key, String value,Long unixTime){
		  Jedis jedis = getJedis();
		  try {
			  if(!jedis.exists(hkey) && unixTime != null){
				  jedis.hset(hkey, key, value); 
				  return jedis.expireAt(hkey, unixTime);
			  }else{
				  jedis.hset(hkey, key, value); 
				  return 0L;
			  }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				returnJedis(jedis);
			}
		return null;
	}
	
    /**
     * 设置set
     * @param skey  set的名字
     * @param value  内容
     */
	public void sadd(String skey,String value){
		Jedis jedis = getJedis();
		try {
			jedis.sadd(skey, value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
	}
	
	/**
	 * 设置set的过期时间
	 * @param skey  set的key
	 * @param value
	 * @param unixTime
	 * @return
	 */
	public void sadd(String skey, String value,Long unixTime){
		  Jedis jedis = getJedis();
		  try {
			  	jedis.sadd(skey, value); 
			    jedis.expireAt(skey, unixTime);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				returnJedis(jedis);
			}
	}
	
	/**
	 * 判断value在skey的set中是否存在
	 * @param skey
	 * @param value
	 */
	public Boolean sismember(String skey, String value){
		Jedis jedis = getJedis();
		try {
			return jedis.sismember(skey, value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return false;
	}
	
	/**
	 * 获取set的值
	 * @param args
	 * @throws Exception
	 */
	public Set<String> smembers(String setKey){
		Jedis jedis = getJedis();
		try {
			return jedis.smembers(setKey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}
	
	/**
	 * set的长度
	 * @param args
	 * @throws Exception
	 */
	public long scard(String key){
		Jedis jedis = getJedis();
		try {
			return jedis.scard(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return 0;
	}
	
	/**
	 * 删除数据
	 * @param args
	 * @throws Exception
	 */
	public void srem(String setKey, String key){
		Jedis jedis = getJedis();
		try {
			jedis.srem(setKey, key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
	}
	/**
	 * 随机返回一个成员
	 * @param args
	 * @throws Exception
	 */
	public String srandmember(String setKey){
		Jedis jedis = getJedis();
		try {
			return jedis.srandmember(setKey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}

	public List<String> srandmember(String setKey, int count){
        Jedis jedis = getJedis();
        try {
            return jedis.srandmember(setKey, count);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            returnJedis(jedis);
        }
        return null;
    }
	
	/**
	 * 返回匹配的集合
	 * @param args
	 * @throws Exception
	 */
	public Set<String> keys(String key){
		Jedis jedis = getJedis();
		try {
			return jedis.keys(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}
	
	
	public static void main(String[] args) throws Exception {
		for(int i = 0 ; i < 1000000; i++)
		System.out.println(getBucketId(("asssss"+i)));
	}
	
	public static String getBucketId(String keys) throws NoSuchAlgorithmException {
		Integer bit = 48;
		byte[] key = keys.getBytes();
		MessageDigest mdInst = MessageDigest.getInstance("MD5");

		mdInst.update(key);

		byte [] md = mdInst.digest();

		byte [] r = new byte[(bit-1)/7 + 1];// 因为一个字节中只有7位能够表示成单字符

		int a = (int) Math.pow(2, bit%7)-2;

		md[r.length-1] = (byte) (md[r.length-1] & a);

		System.arraycopy(md, 0, r, 0, r.length);

		for(int i=0;i<r.length;i++) {
			if(r[i]<0) r[i] &= 127;
		}

		return r.toString().substring(3);

}




	public Set<String> hkeys(String hkey) {
		Jedis jedis = getJedis();
		try {
			return jedis.hkeys(hkey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}




	public String hget(String hkey, String field) {
		Jedis jedis = getJedis();
		try {
			return jedis.hget(hkey, field);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}




	public void decr(String fileNumKey) {
		Jedis jedis = getJedis();
		try {
			jedis.decr(fileNumKey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		
	}




	public boolean hexists(String hkey, String field) {
		Jedis jedis = getJedis();
		try {
			return jedis.hexists(hkey, field);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return false;
	}




	public void hdel(String hkey, String field) {
		Jedis jedis = getJedis();
		try {
			jedis.hdel(hkey, field);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		
	}

	public void hmset(String hkey, Map<String, String> map) {
		Jedis jedis = getJedis();
		try {
			jedis.hmset(hkey, map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		
	}




	public Map<String, String> hgetAll(String oldHkey) {
		Jedis jedis = getJedis();
		try {
			return jedis.hgetAll(oldHkey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}




	public long incr(String hourScceedKey) {
		Jedis jedis = getJedis();
		try {
			return jedis.incr(hourScceedKey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		
		return 0L;
	}



	/**
	 * hash 的数量
	 * @param hkey
	 * @return
	 */
	public long hlen(String hkey) {
		Jedis jedis = getJedis();
		try {
			return jedis.hlen(hkey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		
		return 0L;
	}




	public String lindex(String key, int i) {
		Jedis jedis = getJedis();
		try {
			return jedis.lindex(key,i);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
