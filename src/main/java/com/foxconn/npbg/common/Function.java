package com.foxconn.npbg.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 一些公用的方法
 */
public class Function {

    /**
     * json字符串转java对象
     * @param jsonString
     * @param object
     * @param <T>
     * @return
     */
    public static<T> T jsonToObject(String jsonString, Class<T> object){
        try {
            return JSONObject.parseObject(jsonString, object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * java对象转json字符串
     * @param object
     * @param <T>
     * @return
     */
    public static<T> String objectToJson(T object){
        return JSONObject.toJSONString(object);
    }

    /**
     * 获得dt的下一个整点时刻。如 dt=2020-03-10 15:23:00，则得到2020-03-10 16:00:00
     * @param dt
     * @return
     */
    public static LocalDateTime getNextHour(LocalDateTime dt){
        LocalDateTime plusOneHour = dt.plusHours(1);
        return plusOneHour.withMinute(0).withSecond(0).withNano(0);
    }

    public static LocalDateTime getNextHour(){ return getNextHour(LocalDateTime.now()); }

    /**
     * 获得dt的整点时刻。如 dt=2020-03-10 15:23:00，则得到2020-03-10 15:00:00
     * @param dt
     * @return
     */
    public static LocalDateTime getThisHour(LocalDateTime dt){
        return dt.withMinute(0).withSecond(0).withNano(0);
    }

    public static LocalDateTime getThisHour(){ return getThisHour(LocalDateTime.now()); }

    /**
     * 将字符串类型的日期值转为LocalDateTime类型
     * @param dateTimeString
     * @param pattern
     * @return
     */
    public static LocalDateTime strToDateTime(String dateTimeString, String pattern){
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(pattern));
    }

    public static String getNameBySeq(Map<String, Map<String, Map<String, Object>>> lineConfig, int lineId){
        int index = 1;
        for (Map.Entry<String, Map<String, Map<String, Object>>> entry: lineConfig.entrySet()){
            if (index == lineId)
                return entry.getKey();
            index ++;
        }
        return null;
    }

    public static String getNameBySeq(Map<String, Map<String, Map<String, Object>>> lineConfig, int lineId, int stationId){
        int lineIndex = 1;
        for (Map.Entry<String, Map<String, Map<String, Object>>> entry: lineConfig.entrySet()){
            if (lineIndex == lineId){
                Map<String, Map<String, Object>> lineDetail = entry.getValue();
                int stationIndex = 1;
                for (Map.Entry<String, Map<String, Object>> entry1: lineDetail.entrySet()){
                    if (stationIndex == stationId)
                        return entry1.getKey();
                    stationIndex ++;
                }
            }
            lineIndex++;
        }
        return null;
    }
}
