package com.foxconn.npbg.controller;

import com.alibaba.fastjson.JSONObject;
import com.foxconn.npbg.bean.CustomResp;
import com.foxconn.npbg.common.Func;
import com.foxconn.npbg.pojo.TestRecordVO;
import com.foxconn.npbg.service.MachineRecordService;
import com.foxconn.npbg.service.TestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController()
//@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    private CustomResp resp;

    @Autowired
    private TestRecordService testRecordService;

    @Autowired
    private MachineRecordService machineRecordService;

    @Autowired
    private StringRedisTemplate redisTemplate;

//    @PostMapping(value = "/machineState", produces = "text/html;charset=UTF-8")
//    public String machineState(){
//        LocalDateTime nextHour = Func.getNextHour();
//        LocalDateTime thisHour = Func.getThisHour();
//        resp.setSuccess(CustomResp.SUCCESS);
//        resp.setMessage("");
//        return JSONObject.toJSONString(resp);
//    }
//
//    @PostMapping(value = "/getAll", produces = "text/html;charset=UTF-8")
//    public String siLineState(){// 传过来的是form形式时，用@RequestParam
//        List<TestRecordVO> temp = testRecordService.getRecordsFromITMS(1, 1, LocalDateTime.parse("2020-04-09T12:00:00"), LocalDateTime.parse("2020-04-09T13:00:00"));
//        for (Object obj: temp){
//            System.out.println(obj);
//        }
//        resp.setSuccess(CustomResp.SUCCESS);
//        resp.setMessage("");
//        return JSONObject.toJSONString(resp);
//    }

//    @PostMapping(value = "/GetLineTotalData/MachineState", produces = "text/html;charset=UTF-8")
//    public String totalLineState(@RequestParam Map<String, String> params){
//        params.forEach((k, v) -> {
//            System.out.println("k = " + k + ",v = " + v);
//        });
//        resp.setSuccess(CustomResp.SUCCESS);
//        resp.setMessage("");
//        return JSONObject.toJSONString(resp);
//    }

    /**
     * 首页部分由SFC机台数据得出的“各状态下的机台数量”、“PTH（SI）三条线的目标达成量”
     * @param request
     * @return
     */
    @PostMapping(
            value = {"/GetLineTotalData/MachineState", "/GetLineTotalData/PTHOutputData","/GetLineTotalData/SIOutputData"},
            produces = "text/html;charset=UTF-8"
    )
    public String machineStatusCountAndTarget(HttpServletRequest request){
        String requestUrl = request.getServletPath();
        Map<String, Object> backData = new LinkedHashMap<>();
        LocalDateTime now = LocalDateTime.now().withNano(0);

        if ("/GetLineTotalData/PTHOutputData".equals(requestUrl)){
            //PTH三条线的目标达成量
            Long seconds = Duration.between(Func.startAndEndOfClass(now)[0], now).toMillis() / 1000;
            Long[] target = {Math.round(200*(seconds / 3600.0)), Math.round(200*(seconds / 3600.0)), Math.round(200*(seconds / 3600.0))};
            String[] line = {"P1", "P2", "P3"};
            backData.put("Target", target);
            backData.put("Line", line);
        }else if ("/GetLineTotalData/SIOutputData".equals(requestUrl)){
            //SI三条线的目标达成量
            Long seconds = Duration.between(Func.startAndEndOfClass(now)[0], now).toMillis() / 1000;
            Long[] target = {Math.round(200*(seconds / 3600.0)), Math.round(200*(seconds / 3600.0)), Math.round(200*(seconds / 3600.0))};
            String[] line = {"S1", "S2", "S3"};
            backData.put("Target", target);
            backData.put("Line", line);
        }else {
            //“各状态下的机台数量”
            backData.put("run", 0);
            backData.put("wait", 0);
            backData.put("stop", 0);
            backData.put("off", 0);
            backData.put("shut", 0);

            Map<String, Object> totalStatus = machineRecordService.machineStatus();
            for (Map.Entry<String, Object> entry :totalStatus.entrySet()){
                Map<String, Integer> lineStatus = (LinkedHashMap)entry.getValue();
                for (Map.Entry<String, Integer> entry1 : lineStatus.entrySet()){
                    String key = null;
                    int statusCode = entry1.getValue();
                    if (statusCode == 1)
                        key = "run";
                    else if (statusCode == 2)
                        key = "wait";
                    else if (statusCode == 3)
                        key = "stop";
                    else if (statusCode == 4)
                        key = "off";
                    else
                        key = "shut";
                    Integer num = (Integer)backData.get(key);
                    backData.put(key, num == null ? 1: num + 1);
                }
            }
        }

        resp.setSuccess(CustomResp.SUCCESS);
        resp.setMessage(" ");
        resp.setData(backData);
        return JSONObject.toJSONString(resp);
    }

    @PostMapping(value = {"/GetLineState/lineState", "/GetSILineState/lineState"},
        produces = "text/html;charset=UTF-8"
    )
    public String machineStatus(HttpServletRequest request){
        String requestUrl = request.getServletPath();
        Map<String, Object> totalStatus = machineRecordService.machineStatus();

        List<Object> pcbList = new ArrayList<>();
        List<Object> fstList = new ArrayList<>();
        int lineIndex = 1;
        for (Map.Entry<String, Object> entry: totalStatus.entrySet()){
            Map<String, Integer> lineStatus = (LinkedHashMap)entry.getValue();
            for(Map.Entry<String, Integer> entry1: lineStatus.entrySet()){
                if ("PCB".contains(entry1.getKey())){

                }
            }
        }
        if ("/GetLineState/lineState".equals(requestUrl)){
            // PCB工段（其实也就是ICT 与 BST）的设备状态

        }
        else{
        }

        return null;
    }

}
