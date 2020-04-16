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
    private LinkedHashMap<String, LinkedHashMap<String, String>> lineConfig;

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
        return testRecordMapper.getTestRecordsFromITMS(stationName, "", startStamp, endStamp);
    }

}