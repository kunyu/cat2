package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/17 20:24
 * @Description :
 */
@NoArgsConstructor
@Data
public class OrderRep {
    private Integer page;
    private Integer pageSize;
    private String status;
    private String phone;
}
