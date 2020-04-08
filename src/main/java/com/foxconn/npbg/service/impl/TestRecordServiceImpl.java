package com.foxconn.npbg.service.impl;

import com.foxconn.npbg.dao.TestRecordDao;
import com.foxconn.npbg.pojo.TestRecordVO;
import com.foxconn.npbg.service.TestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TestRecordServiceImpl implements TestRecordService {

    @Autowired
    private TestRecordDao testRecordDao;

    @Autowired
    private Map<String, Map<String, Map<String, Object>>> lineConfig;

    @Override
    public List<TestRecordVO> getRecordsFromITMS(
            int lineId,
            int stationId,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        return testRecordDao.getRecordsFromITMS(lineConfig, lineId, stationId, startTime, endTime);
    }

}