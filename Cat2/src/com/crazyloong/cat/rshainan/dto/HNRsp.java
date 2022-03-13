package com.crazyloong.cat.rshainan.dto;

import lombok.Data;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/13 21:33
 * @Description : 日上海南统一封装返回
 */
@Data
public class HNRsp<T> {
    /* 错误码 */
    private int code;
    /* 错误信息 */
    private String message;
    /* 数据对象 */
    private T data;
}
