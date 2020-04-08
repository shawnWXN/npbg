package com.foxconn.npbg.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.foxconn.npbg.pojo.TestRecordVO;
import com.foxconn.npbg.sql.TestRecordSqlProvider;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface TestRecordDao {

    @SelectProvider(type = TestRecordSqlProvider.class, method = "getRecordsFromITMS")
    @DS("itms")
    @Results({
            @Result(property = "station", column = "station"),
            @Result(property = "hostname", column = "controller"),
            @Result(property = "receiveTime", column = "tnt"),
            @Result(property = "serialNumber", column = "sn"),
            @Result(property = "partNumber", column = "pn"),
            @Result(property = "result", column = "status"),
            @Result(property = "slot", column = "slot"),
            @Result(property = "seqInStation", column = "seqInStation"),
            @Result(property = "testDetail", column = "testDetail"),
    })
    List<TestRecordVO> getRecordsFromITMS(Map<String, Map<String, Map<String, Object>>> lineConfig,
                                          int lineId,
                                          int stationId,
                                          LocalDateTime startTime,
                                          LocalDateTime endTime
    );
}
