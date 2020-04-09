package com.foxconn.npbg.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foxconn.npbg.bean.RedisUtil;
import com.foxconn.npbg.common.Func;
import com.foxconn.npbg.service.MachineRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class MachineRecordServiceImpl implements MachineRecordService {

    @Autowired
    private LinkedHashMap<String, Object> lineConfig;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Map<String, String> addMachineRecords(JSONObject acceptData) throws Exception{
        // 响应体中的data
        Map<String, String> backDataMap = new LinkedHashMap<>();
        // 每个线体具体的数据(按小时划分)
        Map<LocalDateTime, Set<String>> lineData = new HashMap<>();

        int lineId = 1;
        for (Map.Entry<String, Object> entry: lineConfig.entrySet()) {
            String lineName = entry.getKey();
            JSONArray jsonArray = acceptData.getJSONArray(lineName);
            if (jsonArray == null) {
                backDataMap.put(lineName, "接收0条，储存0条，去重0条");
            } else {
                // 每个线体接收到的数据量
                int acceptNum = jsonArray.size();
                for (Object obj : jsonArray) {
                    JSONObject js = (JSONObject) obj;
                    String machineName = js.getString("machine_name");
                    String section = this.belongToSection(machineName);
                    if (section == null){
                        acceptNum --;//有一个工站不在配置表中，则收到的数据量减一
                        continue;
                    }
                    js.put("section", section);
                    js.put("line_id", lineId);
                    LocalDateTime dt = Func.strToDateTime(js.getString("log_time"), "yyyy-MM-dd HH:mm:ss");

                    LocalDateTime dtKey = Func.getNextHour(dt);
                    // 临时list
                    Set<String> temp = new HashSet<>();
                    if (lineData.containsKey(dtKey))
                        temp = lineData.get(dtKey);
                    temp.add(js.toJSONString());
                    lineData.put(dtKey, temp);
                }

                // 开始存储该线数据
                int storeNum = 0;
                Set<LocalDateTime> timeKeys = lineData.keySet();
                for (LocalDateTime dt : timeKeys) {
                    String key = lineName + "_" + dt.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
                    storeNum += redisUtil.setAdd(key, lineData.get(dt));
                }
                backDataMap.put(lineName, String.format("接收%d条，储存%d条，去重%d条", acceptNum, storeNum, acceptNum - storeNum));
                lineData.clear();
            }
            lineId ++;
        }
        return backDataMap;
    }


    @Override
    public String belongToSection(String machineName){
        Set<String> lineNames = lineConfig.keySet();
        for (String lineName: lineNames){
            LinkedHashMap<String, Object> lineDetailMap = (LinkedHashMap)lineConfig.get(lineName);
            if (lineDetailMap.containsKey(machineName)){
                LinkedHashMap<String, Object> stationMap = (LinkedHashMap)lineConfig.get(lineName);
                return (String) stationMap.get("section");
            }
            break; // 因为三条线配置都差不多，所以一遍没找到就break
        }
        return null;
    }
}
