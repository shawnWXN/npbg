package com.foxconn.npbg.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface MachineRecordService {


    /**
     * 增加机台记录
     * @param acceptData
     * @return
     * @throws Exception
     */
    public Map<String, String> addMachineRecords(JSONObject acceptData) throws Exception;

    /**
     * 根据机台名获得它所属的工段
     * @param machineName
     * @return
     * @throws Exception
     */
    public String belongToSection(String machineName);
}
