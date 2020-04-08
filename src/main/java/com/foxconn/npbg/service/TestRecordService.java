package com.foxconn.npbg.service;

import com.foxconn.npbg.pojo.TestRecordVO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TestRecordService {

    /**
     * 查看所有配置
     */
    public List<TestRecordVO> getRecordsFromITMS(int lineId,int stationId,LocalDateTime startTime,LocalDateTime endTime);
}
