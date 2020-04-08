package com.foxconn.npbg.service.impl;

import com.foxconn.npbg.dao.UdtsDataBseDao;
import com.foxconn.npbg.service.TestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestRecordServiceImpl implements TestRecordService {

    @Autowired
    private UdtsDataBseDao udtsDao;

    @Override
    public List<Object> showAll() {
        return udtsDao.findAll();
    }
}
