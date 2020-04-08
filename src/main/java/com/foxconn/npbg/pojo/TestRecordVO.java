package com.foxconn.npbg.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestRecordVO {

    private String station; // 工站名

    private String hostname; // 主机(server)名

    private LocalDateTime receiveTime; // 记录达到数据库的时间

    private String serialNumber; // sn

    private String partNumber; // uuttype

    private String result; // 测试结果。一般都是单个字母，如S或F或P

    private String slot; // 插槽号。每个server都有数量不等的插槽

    private Integer seqInStation; // 该插槽在整个工站中的位置。因为一个工站可能有N个server

    private String testDetail; // 测试详情。一般测试结果=F才会有详情，否则就是一个空串
}
