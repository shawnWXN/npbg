package com.foxconn.npbg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.foxconn.npbg.common.Function;
import com.foxconn.npbg.pojo.MachineRecord;
import com.foxconn.npbg.service.MachineRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MachineRecordServiceImpl implements MachineRecordService {

    @Autowired
    private JSONObject getMachineConfig;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String belongToSection(String machineName) {
        Iterator<String> it = getMachineConfig.keySet().iterator();
        while (it.hasNext()){
            String key = it.next();
            List<String> macList = (List<String>)getMachineConfig.get(key);
            if (macList.contains(machineName)){
                return key;
            }
        }
        return null;
    }

    @Override
    public MachineRecord jsonToObject(JSONObject js) {
        try {
            MachineRecord record = new MachineRecord();
            record.setLogTime(Function.strToDateTime(js.getString("LOG_TIME"), "yyyy-MM-dd HH:mm:ss"));
            record.setMachineName(js.getString("MACHINE_NAME"));
            record.setLineId(js.getInteger("LINE_ID"));
            record.setSection(js.getString("SECTION"));

            List<Integer> temp = new ArrayList<>();//硬编码，默认从D550至D560共11个字段
            for (int i = 0; i < 11; i++) {
                String plc = "D" + (550 + i);
                temp.add(js.getInteger(plc));
            }
            record.setPlcNum(temp);
            return record;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer setAdd(String key, List<String> list) {
        String[] records = new String[list.size()];
        try {
            boolean keyExist = redisTemplate.hasKey(key);
            int count = redisTemplate.opsForSet().add(key, list.toArray(records)).intValue();
            // 如果一开始该key不存在，则设置7天有效期
            if (!keyExist){
                redisTemplate.expire(key, 7, TimeUnit.DAYS);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
