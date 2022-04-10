package com.crazyloong.cat.rshainan.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/19 17:09
 * @Description : 下单参数
 */
@Builder
@Data
public class PlaceOrderReq  extends HNReq{
    /**
    * 数量
    */
    private Integer count;
    /**
     * 数量
     */
    private String goods;
    /**
     * 省
     */
    private String prov;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String area;
    /**
     * 收货地址
     */
    private String receiveAddress;
    /**
     * 收货人
     */
    private String receiveName;
    /**
     * 收货手机号
     */
    private String receivePhone;

    /**
    * 订单编号
    */
    private String ainOrderId;
    /**
     * mac地址
     */
    private Integer mac;


    @Builder.Default
    private String point = "0";
    @Builder.Default
    private String couponAmount = "0";
    @Builder.Default
    private String pointRemain = "0";
    @Builder.Default
    private String memberCoupons = "";
    @Builder.Default
    private String stockId = "6922";
}
