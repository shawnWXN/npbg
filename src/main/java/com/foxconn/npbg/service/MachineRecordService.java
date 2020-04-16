package com.foxconn.npbg.service;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
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

    /**
     * 各机台的运行状况
     * 1运行，2待机，3机故，4断网，5关机
     * @return
     */
    public Map<String, Map<String, Integer>> machineStatus();
}
