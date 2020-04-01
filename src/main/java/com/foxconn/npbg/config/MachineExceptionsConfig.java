package com.foxconn.npbg.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class MachineExceptionsConfig {

    @Value("classpath:json/Exception.json")
    private Resource exceptionJson;

    @Bean
    public JSONObject getMachineException(){
        try {
            String exceptionString = IOUtils.toString(exceptionJson.getInputStream(), "UTF-8");
            return JSONObject.parseObject(exceptionString);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
