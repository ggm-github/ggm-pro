package com.uway.sports.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONUtils {

	 
	private static ValueFilter valueFilter = new FastJsonValueFilter();

	private static final SerializerFeature[] features = {
			SerializerFeature.WriteDateUseDateFormat,
			SerializerFeature.WriteMapNullValue,// 输出空置字段
			SerializerFeature.WriteNullListAsEmpty,// list字段如果为null，输出为[]，而不是null
			//SerializerFeature.WriteNullNumberAsZero,// 数值字段如果为null，输出为0，而不是null
			//SerializerFeature.WriteNullBooleanAsFalse,// Boolean字段如果为null，输出为false，而不是null
			SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
	};
	
	
	
	/**
	 * 有日期类型属性,如为空显示""
	 * @param target
	 * @return
	 */
	public static String toJson(Object target) {
		return JSON.toJSONString(target,valueFilter,features);
	}
	
	/*public static String toJSONString(Object target) {
		return JSON.toJSONString(target,features);
	}*/
	
	public static List<String> toCombo(Map<String,String> map){
		List<String>list = new ArrayList<String>();
		if(map != null){
			for (Map.Entry<String,String> entry : map.entrySet()) {  
				String str="{\"text\":\""+entry.getValue()+"\",\"value\":\""+entry.getKey()+"\"}";
				list.add(str);
			}  
		}
		return list;
	}
	
	/**
	 * 转对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		return JSON.parseObject(json, clazz);
	}
	
	/**
	 * 转成arr
	 * @param json
	 * @return
	 */
	public static JSONArray formJsonToArr(String json){
		return JSON.parseArray(json);
	}
	
	/**
	 * 转成对象
	 * @param json
	 * @return
	 */
	public static JSONObject formJson(String json){
		return JSON.parseObject(json);
	}
	
    
	
	/**
	 * 转数组
	 * @param text
	 * @param clazz
	 * @return
	 */
	public static final <T> List<T> parseArray(String text, Class<T> clazz){
		return JSON.parseArray(text, clazz);
	}

	/**
	 * 自定义时间格式
	 * 
	 * @param jsonText
	 * @return
	 */
	public static String toJson(Object target, String dateFormat) {
		SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (com.alibaba.fastjson.serializer.SerializerFeature feature : features) {
                serializer.config(feature, true);
            }

            serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            
            if (dateFormat != null) {
                serializer.setDateFormat(dateFormat);
            }
            
            serializer.getValueFilters().add(valueFilter);
            
            serializer.write(target);

            return out.toString();
        } finally {
            out.close();
        }
        
		//mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
		//return JSON.toJSONString(target, mapping, features);
		//return JSON.toJSONStringWithDateFormat(target, dateFormat, features);
	}
	
	
}
