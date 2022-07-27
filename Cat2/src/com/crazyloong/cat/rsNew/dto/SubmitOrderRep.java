package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author : crazyloongcat
 * @Date :2022/6/19 22:38
 * @Description :
 */
@NoArgsConstructor
@Data
public class SubmitOrderRep {

    private String address;
    private String city;
    private String district;
    private String name;
    private String phone;
    private String province;
    private String orderPhone;
    // 优惠券形式
    private String couponContant;
    private OrderDetailRsp orderDetailRsp;
    private String couponID;
}
