package com.foxconn.npbg.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.*;

@Configuration
public class LineConfiguration {
    @Value("classpath:json/LineConfig.json")
    private Resource lineConfigJson;

    @Bean
    public LinkedHashMap<String, LinkedHashMap<String, String>> lineConfig(){
        try {
            LinkedHashMap<String, LinkedHashMap<String, String>> result = new LinkedHashMap<>();
            String configString = IOUtils.toString(lineConfigJson.getInputStream(), "UTF-8");
            JSONObject js = JSONObject.parseObject(configString, Feature.OrderedField);//Feature.OrderedField使代码按json文件定义的顺序解析
            for (Map.Entry<String, Object> entry: js.entrySet()){
                JSONObject lineJSON = (JSONObject)entry.getValue();
                result.put(entry.getKey(), new LinkedHashMap<>());
                for (Map.Entry<String, Object> entry1: lineJSON.entrySet()){
                    result.get(entry.getKey()).put(entry1.getKey(), (String)entry1.getValue());
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
//        1.fastjson  List转JSONArray
//        List<T> list = new ArrayList<T>();
//        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list))；
//
//        2.fastjson  JSONArray转List
//        JSONArray array = new JSONArray();
//        List<EventColAttr> list = JSONObject.parseArray(array.toJSONString(), EventColAttr.class);
//
//
//        3.fastjson  字符串转List
//        String str = "[1,2,3,4]";
//        List<Integer> list = JSONObject.parseArray(str,Integer.class);

    }
}
