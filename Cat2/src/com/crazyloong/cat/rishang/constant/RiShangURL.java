package com.crazyloong.cat.rishang.constant;

public enum RiShangURL {

    RISHANG_ADDCART("/goods/shopcart","添加购物车URL"),
    RISHANG_CREATE_ORDER("/user/prevsubmitorder","生成订单URL"),
    RISHANG_authtraveller3("/user/authtraveller3","-------"),
    RISHANG_SUBMITORDER("/user/submitorder","提交订单URL"),
    RISHANG_PAY(" /order/pay","付款URL"),
    RISHANG_CART("/user/cart","生成订单URL"),
    RISHANG_loginv3("/user/loginv3","登录URL"),
    RISHANG_WISHS("/user/wishs","获取我的订单信息URL"),
    RISHANG_GETVIPCOUPONV2("/rights/getvipcouponv2","获取优惠券相关信息URL"),
    RISHANG_MALLNEW("/order/mallnew","获取订单信息URL"),
    RISHANG_GETVIPCOD("/rights/getvipcoupons","获取当前登录用户的优惠券URL");

    public String code;
    public String name;

    RiShangURL(String code,String name) {
        this.code = code;
        this.name = name;
    }
}
