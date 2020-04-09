package com.foxconn.npbg.service.impl;

import com.foxconn.npbg.common.Func;
import com.foxconn.npbg.dao.TestRecordMapper;
import com.foxconn.npbg.pojo.TestRecordVO;
import com.foxconn.npbg.service.TestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestRecordServiceImpl implements TestRecordService {

    @Autowired
    private TestRecordMapper testRecordMapper;

    @Autowired
    private LinkedHashMap<String, Object> lineConfig;

    @Override
    public List<TestRecordVO> getRecordsFromITMS(
            int lineId,
            int stationId,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        Long startStamp = startTime.toEpochSecond(ZoneOffset.ofHours(8));
        Long endStamp = endTime.toEpochSecond(ZoneOffset.ofHours(8));
        String stationName = "";
        String hostnameStr = null;
        StringBuffer controllersStr = new StringBuffer("");
        int lineIndex = 1;
        for (Map.Entry<String, Object> entry: lineConfig.entrySet()){
            if (lineIndex == lineId){
                LinkedHashMap<String, Object> lineDetail = (LinkedHashMap)entry.getValue();
                int stationIndex = 1;
                for (Map.Entry<String, Object> entry1: lineDetail.entrySet()){
                    if (stationIndex == stationId){
                        stationName = entry1.getKey();
                        LinkedHashMap<String, Object>  stationDetail = (LinkedHashMap)entry1.getValue();
                        hostnameStr = (String)stationDetail.get("hostname");
                        break;
                    }
                    stationIndex ++;
                }
            }
        }
        if (hostnameStr != null){
            controllersStr.append("'").append(hostnameStr.replace(",", "','")).append("'");
        }
        return testRecordMapper.getTestRecordsFromITMS(stationName, controllersStr.toString(), startStamp, endStamp);
    }

}