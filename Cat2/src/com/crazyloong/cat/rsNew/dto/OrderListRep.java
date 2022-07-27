package com.crazyloong.cat.rsNew.dto;

import lombok.Data;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/17 20:39
 * @Description :
 */
@Data
public class OrderListRep {
    private Integer page;
    private Integer pageSize;
    private String status;
}
