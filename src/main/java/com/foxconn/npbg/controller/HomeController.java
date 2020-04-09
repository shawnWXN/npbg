package com.foxconn.npbg.controller;

import com.alibaba.fastjson.JSONObject;
import com.foxconn.npbg.bean.CustomResp;
import com.foxconn.npbg.common.Func;
import com.foxconn.npbg.pojo.TestRecordVO;
import com.foxconn.npbg.service.TestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    private CustomResp resp;

    @Autowired
    private TestRecordService testRecordService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping(value = "/machineState", produces = "text/html;charset=UTF-8")
    public String machineState(){
        LocalDateTime nextHour = Func.getNextHour();
        LocalDateTime thisHour = Func.getThisHour();
        resp.setSuccess(CustomResp.SUCCESS);
        resp.setMessage("");
        return JSONObject.toJSONString(resp);
    }

    @PostMapping(value = "/getAll", produces = "text/html;charset=UTF-8")
    public String siLineState(){// 传过来的是form形式时，用@RequestParam
        List<TestRecordVO> temp = testRecordService.getRecordsFromITMS(1, 1, LocalDateTime.parse("2020-04-09T12:00:00"), LocalDateTime.parse("2020-04-09T13:00:00"));
        for (Object obj: temp){
            System.out.println(obj);
        }
        resp.setSuccess(CustomResp.SUCCESS);
        resp.setMessage("");
        return JSONObject.toJSONString(resp);
    }

    @PostMapping(value = "/GetLineTotalData/MachineState", produces = "text/html;charset=UTF-8")
    public String totalLineState(@RequestParam Map<String, String> params){
        params.forEach((k, v) -> {
            System.out.println("k = " + k + ",v = " + v);
        });
        resp.setSuccess(CustomResp.SUCCESS);
        resp.setMessage("");
        return JSONObject.toJSONString(resp);
    }

}
