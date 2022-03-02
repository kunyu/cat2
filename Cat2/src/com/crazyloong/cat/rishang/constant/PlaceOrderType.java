package com.crazyloong.cat.rishang.constant;

/**
 * 下单方式
 */
public enum PlaceOrderType {
    publicCode("1","公用券码"),
    userCode("2","用户券码");

    public String code;
    public String name;

    PlaceOrderType(String code,String name) {
        this.code = code;
        this.name = name;
    }
}
