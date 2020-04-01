package com.foxconn.npbg.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foxconn.npbg.bean.CustomResp;
import com.foxconn.npbg.bean.RedisUtil;
import com.foxconn.npbg.common.Function;
import com.foxconn.npbg.service.MachineRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 接收数据相关接口
 */
@RestController()
public class AcceptController {

    @Autowired
    private CustomResp resp;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MachineRecordService machineRecordService;

    /**
     * 接收数据
     */
    @PostMapping(value = "/acceptData", produces = "application/json;charset=UTF-8") // 响应类型由produces参数决定。
    public CustomResp acceptData(@RequestBody JSONObject param){ //接收类型由此方法的参数决定。json用RequestBody注解

        // 此处硬编码，与IT约定好即可
        String[] line_name = {"mfgiii_test_first_data", "mfgiii_test_second_data", "mfgiii_test_third_data"};
        // 响应体中的data
        Map<String, String> backDataMap = new HashMap<>();
        // 每个线体具体的数据
        Map<LocalDateTime, Set<String>> lineData = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            JSONArray jsonArray = param.getJSONArray(line_name[i]);
            if (jsonArray == null){
                backDataMap.put(line_name[i], "接收0条，储存0条，去重0条");
            }else {
                // 每个线体接收到的数据量
                int acceptNum = jsonArray.size();
                int lineId = i + 1;
                for(Object obj : jsonArray){
                    JSONObject js = (JSONObject) obj;
                    String machineName = js.getString("MACHINE_NAME");
                    String section = machineRecordService.belongToSection(machineName);
                    js.put("SECTION", section);
                    js.put("LINE_ID", lineId);
                    LocalDateTime dt = Function.strToDateTime(js.getString("LOG_TIME"), "yyyy-MM-dd HH:mm:ss");

                    LocalDateTime dtKey = Function.getNextHour(dt);
                    // 临时list
                    Set<String> temp = new HashSet<>();
                    if (lineData.containsKey(dtKey)){
                        temp = lineData.get(dtKey);
                    }
                    temp.add(js.toJSONString());
                    lineData.put(dtKey, temp);
                }

                // 开始存储该线数据
                int storeNum = 0;
                Set<LocalDateTime> timeKeys = lineData.keySet();
                for(LocalDateTime dt: timeKeys){
                    String key = line_name[i] + "_" + dt.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
                    storeNum += redisUtil.setAdd(key, lineData.get(dt));
                }
                backDataMap.put(line_name[i], String.format("接收%d条，储存%d条，去重%d条", acceptNum, storeNum, acceptNum - storeNum));
                lineData.clear();
            }
        }
        resp.setSuccess(CustomResp.SUCCESS);
        resp.setMessage("ok");
        resp.setData(backDataMap);
        return resp;
    }
}
