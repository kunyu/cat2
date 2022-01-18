package com.crazyloong.cat.dto;

import lombok.Data;

/**
 * @author YPLI
 * @description 日上app返回对象
 * @date 2022/1/8 0008 20:29
 **/
@Data
public class RiReturnRsp<T> {
    /* 成功标识 */
    private Boolean success;
    /* 错误原因 */
    private String error;
    /* 错误码 */
    private Integer code;
    /* 数据对象 */
    private T data;
}
