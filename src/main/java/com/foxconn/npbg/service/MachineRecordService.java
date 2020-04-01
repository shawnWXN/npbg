package com.foxconn.npbg.service;

import com.alibaba.fastjson.JSONObject;
import com.foxconn.npbg.pojo.MachineRecord;

import java.util.List;

public interface MachineRecordService {

    /**
     * 根据机台名获得它所属的工段
     * @param machineName
     * @return
     */
    public String belongToSection(String machineName);

    /**
     * 将JSONObject对象转为MachineRecord
     * @param js
     * @return
     */
    public MachineRecord jsonToObject(JSONObject js);

    /**
     * 将一组MachineRecord列表存入指定key
     * @param key
     * @param list
     * @return
     */
    public Integer setAdd(String key, List<String> list);
}
