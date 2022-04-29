package com.uway.sports.common.redis;

import java.io.*;

/**
 * 序列化与反序列化工具类
 * @author mazhengzheng
 *
 */
public class SerializeUtil {
	/**
	 * 序列化
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos=null;
		ByteArrayOutputStream baos=null;
		try {
			baos=new ByteArrayOutputStream();
			oos=new ObjectOutputStream(baos);
			oos.writeObject(object);
			
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			try {
				if(oos!=null){
					oos.close();
				}
				if(baos!=null){
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais=null;
		ObjectInputStream ois=null;
		try {
			bais=new ByteArrayInputStream(bytes);
			ois=new ObjectInputStream(bais);
			
			Object res=ois.readObject();
			return res;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			try {
				if(ois!=null){
					ois.close();
				}
				if(bais!=null){
					bais.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
