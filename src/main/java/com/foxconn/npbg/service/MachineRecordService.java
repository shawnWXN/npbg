package com.foxconn.npbg.service;

import java.util.List;

public interface MachineRecordService {

    /**
     * 根据机台名获得它所属的工段
     * @param machineName
     * @return
     */
    public String belongToSection(String machineName);
}
