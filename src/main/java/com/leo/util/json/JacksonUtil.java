package com.leo.util.json;


import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
/**
 * @author 刘绍林
 * @description
 * @time 2017/4/6
 */
public class JacksonUtil {

    private static ObjectMapper objectMapper;
    private static Logger logger = Logger.getLogger(JacksonUtil.class);
    static {
        if(objectMapper==null) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            objectMapper.configure(JsonParser.Feature.INTERN_FIELD_NAMES, true);
            //属性为NULL不序列化
            objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            objectMapper.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, true);
            objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }

    public static String jsonFormat(Object obj) {
        return jsonFormat(obj, null);
    }

    public static void setDateFormat(String dateFormat) {
        if (dateFormat != null && dateFormat.isEmpty()) {
            objectMapper.setDateFormat(new SimpleDateFormat(dateFormat));
        }
    }

    public static String jsonFormat(Object obj, String dateFormat) {
        try {
            setDateFormat(dateFormat);
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
           e.printStackTrace();
        }
        return null;
    }


    /**
     * 将json string反序列化成对象
     *
     * @param json json字符串
     * @param valueType 序列化类型
     * @return <T>
     */
    public static <T> T decode(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json array反序列化为对象
     *
     * @param json          json字符串
     * @param typeReference 类型
     * @return <T>
     */
    @SuppressWarnings("unchecked")
    public static <T> T decode(String json, TypeReference<T> typeReference) {
        try {
            return (T) objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> toMap(String str) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue(str, Map.class);
            return map;
        } catch (JsonParseException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }
}
