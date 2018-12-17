package com.leo.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * fastJson工具类
 *
 * @author 刘绍林
 * @create 2017-08-12 10:27
 **/
public class FastJsonUtil {
    private static final SerializeConfig CONFIG;

    static {
        CONFIG = new SerializeConfig();
        // 使用和json-lib兼容的日期输出格式
        CONFIG.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        // 使用和json-lib兼容的日期输出格式
        CONFIG.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }


    private static final SerializerFeature[] FEATURES = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    /**
     * @param obj //对象模型
     * @return String   //转换完毕的字符串
     * @Title: obj2Json
     * @Description: 将对象转换为JSON字符串
     */
    public static String toJSONString(Object obj) {
        String str = JSON.toJSONString(obj, CONFIG, FEATURES);
        return str;
    }

    /**
     * 將json转换为Object
     *
     * @param text json字符串
     * @return Object
     */
    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * 转换为数组
     *
     * @param text json字符串
     * @param <T>  范型
     * @return T
     */
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }

    /**
     * 转换为数组
     *
     * @param text  json字符串
     * @param clazz 数组类型
     * @param <T>   泛型
     * @return T
     */
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    /**
     * 转换为List
     *
     * @param text  json字符串
     * @param clazz 数组类型
     * @param <T>   泛型
     * @return List<T>
     */
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * json字符串转化为map
     *
     * @param jsonText json字符串
     * @return Map
     */
    public static Map stringToCollect(String jsonText) {
        Map m = JSONObject.parseObject(jsonText);
        return m;
    }

    /**
     * 将map转化为string
     *
     * @param m map对象
     * @return String
     */
    public static String collectToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

}
