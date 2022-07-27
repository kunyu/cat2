package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/18 12:24
 * @Description :
 */
@NoArgsConstructor
@Data
public class MyCouponRep {

    private String couponStatus;
    private String memberCode;
    private Integer pageNumber;
    private Integer pageSize;
}
