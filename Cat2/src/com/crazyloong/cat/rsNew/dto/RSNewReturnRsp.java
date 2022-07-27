package com.crazyloong.cat.rsNew.dto;

import lombok.Data;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/14 22:44
 * @Description : 中免日上公共返回对象
 */
@Data
public class RSNewReturnRsp<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private String note;
    private String msg;
    private Integer exec_Time;
    private String TraceId;
    private T data;
}
