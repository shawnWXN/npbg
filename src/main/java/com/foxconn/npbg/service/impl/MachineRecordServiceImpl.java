package com.foxconn.npbg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.foxconn.npbg.service.MachineRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class MachineRecordServiceImpl implements MachineRecordService {

    @Autowired
    private JSONObject lineConfig;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String belongToSection(String machineName) {
        Iterator<String> it = lineConfig.keySet().iterator();
        while (it.hasNext()){
            String key = it.next();
            List<String> macList = (List<String>)lineConfig.get(key);
            if (macList.contains(machineName)){
                return key;
            }
        }
        return null;
    }
}
