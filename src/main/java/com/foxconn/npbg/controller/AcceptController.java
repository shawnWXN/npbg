package com.foxconn.npbg.controller;

import com.alibaba.fastjson.JSONObject;
import com.foxconn.npbg.bean.CustomResp;
import com.foxconn.npbg.service.MachineRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

/**
 * 接收数据相关接口
 */
@RestController()
public class AcceptController {

    @Autowired
    private CustomResp resp;

    @Autowired
    private MachineRecordService machineRecordService;

    /**
     * 接收数据
     * @param param
     * {
     *     "mfgiii_test_first_data": [
     *          {
     *              "MACHINE_NAME": "XXXX",
     *              "LOG_TIME": "2019-03-03 08:05:03",
     *              "D550": 1,
     *              "D551": 2,
     *              ...
     *          },
     *          ...
     *     ]
     * }
     * @return
     */
    @PostMapping(value = "/acceptData", produces = "application/json;charset=UTF-8") // 响应类型由produces参数决定。
    public Map acceptData(@RequestBody JSONObject param){ //接收类型由此方法的参数决定。json用RequestBody注解
        LocalTime startTime = LocalTime.now();

        Map<String, Object> respMap = new LinkedHashMap<>();
        Map<String, String> backDataMap = null;
//        try {
//            backDataMap = machineRecordService.addMachineRecords(param);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            respMap.put("status", "false");
//        }
        respMap.put("status", "true");
        Long cost = Duration.between(startTime, LocalTime.now()).toMillis();
        respMap.put("message", "It costs " + Duration.between(startTime, LocalTime.now()).toMillis() / 1000L + "s");
        respMap.put("data", backDataMap);
        return respMap;
    }
}
