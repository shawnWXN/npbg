package com.foxconn.npbg.sql;

import com.foxconn.npbg.common.Function;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class TestRecordSqlProvider {

    public String getRecordsFromITMS(Map<String, Map<String, Map<String, Object>>> lineConfig, int lineId, int stationId, LocalDateTime startTime, LocalDateTime endTime){
        String lineName = Function.getNameBySeq(lineConfig, lineId);
        String stationName = Function.getNameBySeq(lineConfig, lineId, stationId);
        String startTimeStr = startTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace("T", " ");
        String endTimeStr = endTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace("T", " ");
        StringBuffer sql = new StringBuffer("SELECT '" + stationName +"' as 'station', controller, tnt, sn, pn, status," +
                "1 as 'slot', 1 as 'seqInStation', '' as 'testDetail' FROM db_log_status.ictalert WHERE controller in (");
        String controllerStr = (String)lineConfig.get(lineName).get(stationName).get("hostname");
        String[] controllers = controllerStr.split(",");
        for (String controller: controllers){
            sql.append("'").append(controller).append("',");
        }
        sql.replace(sql.length() - 1, sql.length(), ") ");

        sql.append("AND tnt > '").append(startTimeStr).append("' AND tnt < '").append(endTimeStr).append("'");
        return sql.toString();
    }
}
