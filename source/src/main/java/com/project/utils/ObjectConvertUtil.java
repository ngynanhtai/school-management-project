package com.project.utils;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectConvertUtil {

    public static String objectToJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String jsonStr = "";
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.error("Convert Object To Json error: {}", e.getMessage());
        }
        return jsonStr;
    }
    
    public static <T> T mapToObject(Map<String, Object> map, Class<T> classT) {
    	ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.convertValue(map, classT);
        } catch (Exception e) {
            log.error("Convert Object To Json error: {}", e.getMessage());
        }
        return null;
    }
    
    public static <T> T jsonToObject(String json, Class<T> classT) {
    	ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, classT);
        } catch (Exception e) {
            log.error("Convert Object To Json error: {}", e.getMessage());
        }
        return null;
    }
    
}
