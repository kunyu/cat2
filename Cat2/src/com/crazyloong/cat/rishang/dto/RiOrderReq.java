package com.crazyloong.cat.rishang.dto;

import lombok.Data;

@Data
public class RiOrderReq extends RiReq{
    /**
     * 下订单的用户Id
     */
    private String phoneId;
    /**
     * 下订单的地址Id
     */
    private String addressId;
    /*
     * 下单数量
     **/
    private int orderNum;
    /*
     * 下单数量
     **/
    private WishPageRsp wishPageRsp;
    /**
     * 是否使用优惠券
     **/
    private boolean useCode;
}
