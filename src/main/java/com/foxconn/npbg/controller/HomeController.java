package com.foxconn.npbg.controller;

import com.alibaba.fastjson.JSONObject;
import com.foxconn.npbg.bean.CustomResp;
import com.foxconn.npbg.common.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController()
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    private CustomResp resp;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping(value = "/machineState", produces = "text/html;charset=UTF-8")
    public String machineState(){
        LocalDateTime nextHour = Function.getNextHour();
        LocalDateTime thisHour = Function.getThisHour();
        resp.setSuccess(CustomResp.SUCCESS);
        resp.setMessage("");
        return JSONObject.toJSONString(resp);
    }

    @PostMapping(value = "/GetSILineState/lineState", produces = "text/html;charset=UTF-8")
    public String siLineState(@RequestParam Map<String, String> params){// 传过来的是form形式时，用@RequestParam
        params.forEach((k, v) -> {
            System.out.println("k = " + k + ",v = " + v);
        });
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
