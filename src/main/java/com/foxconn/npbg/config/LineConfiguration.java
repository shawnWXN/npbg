package com.foxconn.npbg.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LineConfiguration {
    @Value("classpath:json/LineConfig.json")
    private Resource lineConfigJson;

    @Bean
    public JSONObject lineConfig(){
        try {
            String configString = IOUtils.toString(lineConfigJson.getInputStream(), "UTF-8");
            return JSONObject.parseObject(configString);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
