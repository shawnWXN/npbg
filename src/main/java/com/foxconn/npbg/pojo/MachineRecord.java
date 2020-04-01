package com.foxconn.npbg.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MachineRecord implements Serializable {

    private LocalDateTime logTime;

    private String machineName;

    private Integer lineId;

    private String section;

    private List<Integer> plcNum;//硬编码，默认从D550至D560共11个字段
}
