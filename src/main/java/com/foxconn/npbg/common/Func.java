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
public class Func {

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

    /**
     * 根据lineId得到它的lineName
     * @param lineConfig
     * @param lineId
     * @return
     */
    public static String getNameBySeq(LinkedHashMap<String, Object> lineConfig, int lineId){
        int index = 1;
        for (Map.Entry<String, Object> entry: lineConfig.entrySet()){
            if (index == lineId)
                return entry.getKey();
            index ++;
        }
        return null;
    }

    /**
     * 根据lineId以及stationId得到它的stationName
     * @param lineConfig
     * @param lineId
     * @param stationId
     * @return
     */
    public static String getNameBySeq(LinkedHashMap<String, Object> lineConfig, int lineId, int stationId){
        int lineIndex = 1;
        for (Map.Entry<String, Object> entry: lineConfig.entrySet()){
            if (lineIndex == lineId){
                LinkedHashMap<String, Object> lineDetail = (LinkedHashMap)entry.getValue();
                int stationIndex = 1;
                for (Map.Entry<String, Object> entry1: lineDetail.entrySet()){
                    if (stationIndex == stationId)
                        return entry1.getKey();
                    stationIndex ++;
                }
            }
            lineIndex++;
        }
        return null;
    }

    /**
     * 获得dt时间所在班别的开始时间和结束时间
     * 其中白班从早8点到晚8点；晚班从晚8点到次日早8点
     * @param dt
     * @return
     */
    public static LocalDateTime[] startAndEndOfClass(LocalDateTime dt){
        LocalDateTime eightHour = dt.withHour(8).withMinute(0).withSecond(0);
        LocalDateTime twentyHour = dt.withHour(20).withMinute(0).withSecond(0);
        // dt <= 当日8点，则返回昨日20点至当日8点
        if (dt.isBefore(eightHour) || dt.isEqual(eightHour))
            return new LocalDateTime[]{twentyHour.minusDays(1), eightHour};
        // 当日早8点 < dt <= 当日20点，则返回当日8点至当日20点
        else if (dt.isAfter(eightHour) && (dt.isBefore(twentyHour) || dt.isEqual(twentyHour)))
            return new LocalDateTime[]{eightHour, twentyHour};
        // 当日20点 < dt，则返回当日20点至次日8点
        else
            return new LocalDateTime[]{twentyHour, eightHour.plusDays(1)};
    }
}
