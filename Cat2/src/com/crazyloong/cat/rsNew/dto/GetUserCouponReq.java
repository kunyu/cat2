package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/17 0:24
 * @Description :
 */
@NoArgsConstructor
@Data
public class GetUserCouponReq {
    private String couponId;
    private String userId;
    private String memberCode;
    private Boolean showToast;
}
