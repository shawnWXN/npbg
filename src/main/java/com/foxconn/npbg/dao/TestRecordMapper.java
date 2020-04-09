package com.foxconn.npbg.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.foxconn.npbg.pojo.TestRecordVO;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TestRecordMapper {

    // 要使接口方法与xml中的语句建立映射
    // 1.接口全名与xml中的namespace一致
    // 2.接口方法与xml中sql语句的id一致
    // 3.pom.xml中build中加入<resource>标签，把XXXMapper.xml文件加进去
    // 4.application.yml中配置mybatis.mapper-locations: classpath:xxx/xxx/xxx/*.xml

    @DS("itms")

    List<TestRecordVO> getTestRecordsFromITMS(
            @Param("stationName") String stationName,
            @Param("controllersStr") String controllersStr,
            @Param("startStamp") Long startStamp,
            @Param("endStamp") Long endStamp
    );
}
