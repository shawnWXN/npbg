package com.foxconn.npbg.controller;

import com.alibaba.fastjson.JSONObject;
import com.foxconn.npbg.bean.CustomResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@RestController()
@RequestMapping(value = "/Home")
public class HomeController {

    @Autowired
    private CustomResp resp;

    @PostMapping(value = "/GetLineState/lineState", produces = "text/html;charset=UTF-8")
    public String pthLineState(@RequestParam Map<String, String> params){
        params.forEach((k, v) -> {
            System.out.println("k = " + k + ",v = " + v);
        });
        resp.setSuccess(CustomResp.SUCCESS);
        resp.setMessage("");
        return JSONObject.toJSONString(resp);
    }

    @PostMapping(value = "/GetSILineState/lineState", produces = "text/html;charset=UTF-8")
    public String siLineState(@RequestParam Map<String, String> params){
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
