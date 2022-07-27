package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/17 0:25
 * @Description :
 */
@NoArgsConstructor
@Data
public class GetUserCouponRsp {
    private String userId;
    private Long couponId;
    private String couponCode;
    private Boolean success;
    private String message;

}
