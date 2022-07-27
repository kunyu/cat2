package com.crazyloong.cat.rishang.dto;

import lombok.Data;

@Data
public class RiOrderReq extends RiReq{
    /**
     * 下订单的用户Id
     */
    private String phoneId;
    /**
    * 下订单的用户
    */
    private String phone;
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
    /**
     *  1-使用数据库公共优惠券 2-使用登录用户自己的优惠券
     */
    private String type;

    /**
     * 券码类型
     */
    private String preferentialSum;
    /**
    * 认证码
    */
    private String firstToken;

    private String myOrderId;

    private int orderNumLimit;
}
