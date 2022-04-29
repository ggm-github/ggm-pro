package com.uway.sports.common.redis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * ShardedJedis Cache 工具类
 * redis集群部署时的工具类
 * 
 */
public class SharedRedisClient {
	
    private ShardedJedisPool shardedJedisPool;
	/**
	 * redis编码
	 */
	private String charSet="UTF-8";

    public SharedRedisClient(){
    }
    
    public SharedRedisClient(ShardedJedisPool shardedJedisPool){
    	this.shardedJedisPool = shardedJedisPool;
    }
    
    /**
	 * 获取字符串
	 * @param key
	 * @return
	 */
	public String get(String key){
		ShardedJedis jedis = getJedis();
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
		
		ShardedJedis jedis = getJedis();
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
		
		ShardedJedis jedis = getJedis();
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
		
		ShardedJedis jedis = getJedis();
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
		
		ShardedJedis jedis = getJedis();
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
		
		ShardedJedis jedis = getJedis();
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
		ShardedJedis jedis = getJedis();
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
		ShardedJedis jedis = getJedis();
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
	 * @param key
	 * @return
	 */
	public Long del(String key){
		
		ShardedJedis jedis = getJedis();
		try {
			return jedis.del(key);
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
		ShardedJedis jedis = getJedis();
		return jedis.expire(key, seconds);
	}
	
	/**
	 * 获取jedis对象
	 * @return
	 */
	public ShardedJedis getJedis(){
		ShardedJedis jedis= shardedJedisPool.getResource();
		return jedis;
	}
	
	/**
	 * 释放jedis资源
	 * @param jedis
	 */
	public void returnJedis(ShardedJedis jedis){
		if(jedis!=null){
			jedis.close();
		}
	}
	
	public boolean isExistsByKey(String key){
		ShardedJedis jedis = getJedis();
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
		  ShardedJedis jedis = getJedis();
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
		ShardedJedis jedis = getJedis();
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
		  ShardedJedis jedis = getJedis();
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
		ShardedJedis jedis = getJedis();
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
	 * @param
	 * @throws Exception
	 */
	public Set<String> smembers(String setKey){
		ShardedJedis jedis = getJedis();
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
	 * @param
	 * @throws Exception
	 */
	public long scard(String key){
		ShardedJedis jedis = getJedis();
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
	 * @param
	 * @throws Exception
	 */
	public void srem(String setKey, String... value){
		ShardedJedis jedis = getJedis();
		try {
			jedis.srem(setKey, value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
	}
	/**
	 * 随机返回一个成员
	 * @param
	 * @throws Exception
	 */
	public String srandmember(String setKey){
		ShardedJedis jedis = getJedis();
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
        ShardedJedis jedis = getJedis();
        try {
            return jedis.srandmember(setKey, count);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            returnJedis(jedis);
        }
        return null;
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
		ShardedJedis jedis = getJedis();
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
		ShardedJedis jedis = getJedis();
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
		ShardedJedis jedis = getJedis();
		try {
			jedis.decr(fileNumKey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		
	}




	public boolean hexists(String hkey, String field) {
		ShardedJedis jedis = getJedis();
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
		ShardedJedis jedis = getJedis();
		try {
			jedis.hdel(hkey, field);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		
	}

	public void hmset(String hkey, Map<String, String> map) {
		ShardedJedis jedis = getJedis();
		try {
			jedis.hmset(hkey, map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		
	}




	public Map<String, String> hgetAll(String hkey) {
		ShardedJedis jedis = getJedis();
		try {
			return jedis.hgetAll(hkey);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}




	public long incr(String hourScceedKey) {
		ShardedJedis jedis = getJedis();
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
		ShardedJedis jedis = getJedis();
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
		ShardedJedis jedis = getJedis();
		try {
			return jedis.lindex(key,i);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}

	public long llen(String key) {
		ShardedJedis jedis = getJedis();
		try {
			return jedis.llen(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return 0;
	}
    /**
     * Auth: liujingce
     * Date: 2018/1/24 15:39
     * Title: 在列表中添加一个或多个值
     * @param key  key值
     * @param values  要插入的值
     *Describer:
     **/
    public long rpush(String key,String... values){
        ShardedJedis jedis = getJedis();
        try {
            return jedis.rpush(key,values);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            returnJedis(jedis);
        }
        return 0;
    }
    /**
     * Auth: liujingce
     * Date: 2018/1/24 15:46
     * Title: 获取列表中所有的值
     * @param key  key值
     *Describer:
     **/
    public List<String> getListAllValue(String key){
        ShardedJedis jedis = getJedis();
        try {
            long len = jedis.llen(key);
            return jedis.lrange(key,0,len-1);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            returnJedis(jedis);
        }
        return null;
    }

	/**
	 * 移除有序数组的成员
	 * @param key
	 * @param value
	 * @return
	 */
	public Long zrem(String key,String... value){
		ShardedJedis jedis = getJedis();
		try {
			return jedis.zrem(key,value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}

	/**
	 * 添加有序数组的成员
	 * @param skey  set的key
	 * @param index  排序值
	 * @param value  内容
	 */
	public void zadd(String skey,double index,String value){
		ShardedJedis jedis = getJedis();
		try {
			jedis.zadd(skey,index, value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
	}

	/**
	 * 按照降序获取，根据下标获取数据
	 * 	 从0开始，获取所有则end为-1
	 * @param key
	 * @param start  从0开始
	 * @param end    获取到最后一名，则为-1
	 * @return
	 */
	public Set<String> zrevrange(String key,int start,int end){
		ShardedJedis jedis = getJedis();
		try {
			return jedis.zrevrange(key,start,end);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnJedis(jedis);
		}
		return null;
	}
}