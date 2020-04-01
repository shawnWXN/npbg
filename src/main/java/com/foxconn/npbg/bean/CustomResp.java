package com.foxconn.npbg.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 自定义的响应类
 */
@Component
@Data
public class CustomResp {

    public static final String SUCCESS = "true";

    public static final String ERROR= "false";

    private String success;

    private String message;

    private Map<String, String> data;
}
