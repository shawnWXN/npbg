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
    public Map<String, Map<String, List<Object>>> lineConfig(){
        try {
            Map<String, Map<String, List<Object>>> result = new LinkedHashMap<>();
            String configString = IOUtils.toString(lineConfigJson.getInputStream(), "UTF-8");
            JSONObject js = JSONObject.parseObject(configString, Feature.OrderedField);//Feature.OrderedField使js按json文件定义的顺序解析
            for (Map.Entry entry: js.entrySet()){
                String lineName = (String)entry.getKey();
                JSONObject stationMap = (JSONObject)entry.getValue();
                Map<String, List<Object>> temp = new LinkedHashMap<>();
                for (Map.Entry entry1: stationMap.entrySet()){
                    JSONArray stationDetails = (JSONArray)entry1.getValue();
                    temp.put((String)entry1.getKey(), JSONObject.parseArray(stationDetails.toJSONString()));
                }
                result.put(lineName, temp);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
