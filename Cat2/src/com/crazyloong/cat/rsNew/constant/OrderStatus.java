package com.crazyloong.cat.rsNew.constant;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/17 20:21
 * @Description :
 */
public enum OrderStatus {

    all("all","全部"),
    prepay("prepay","待支付"),
    delivery("delivery","待发货"),
    receive("receive","待收货"),
    refund("refund","退款"),
    finish("finish","已完成"),
    close("close","订单关闭"),
    ;

    public String code;
    public String desc;

    OrderStatus(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static String getDescByCode(String code) {
        return OrderStatus.valueOf(code).desc;
    }
}
