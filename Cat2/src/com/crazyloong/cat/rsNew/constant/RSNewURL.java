package com.crazyloong.cat.rsNew.constant;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/14 22:24
 * @Description : 中免日上url枚举
 */
public enum RSNewURL {
    RSN_deviceLogin("/restfulapi/Account/deviceLogin","设备登录"),
    RSN_userPasswordLogin("/restfulapi/Account/userPasswordLogin","登录"),
    RSN_getCouponsDetail("/restapi/activity/getCouponsDetail","获取优惠券详情"),
    RSN_hint("/restapi/search/hint","获取优惠券网址"),
    CDN_content("/content/","获取优惠券数据"),
    RSN_getUserCoupon("/restapi/activity/getUserCoupon","领取优惠券"),
    RSN_orderDetail("/restfulapi/Order/orderDetail","获取订单详情"),
    RSN_orderList("/restfulapi/Order/orderList","获取订单列表"),
    RSN_getMyCoupon("/restapi/promotion/getMyCoupon","获取订单优惠券"),
    RSN_getPublicKey("/restfulapi/Account/getPublicKey","获取公钥"),
    RSN_changePassword("/restfulapi/Account/changePassword","修改密码"),
    RSN_updateAddress("/restfulapi/Account/addressBook/updateAddress","更新地址"),
    RSN_createOrder("/restapi/order/createOrder","提交订单"),
    RSN_confirmOrder("/restapi/order/confirmOrder","确认订单"),
    RSN_searchItem("/restapi/search/item/v2","查询商品详情"),
    RSN_addressBook("/restfulapi/Account/addressBook/List","获取用户地址"),
    RSN_addCart("/restapi/cart/addCart","添加购物车"),
    ;

    public String code;
    public String desc;

    RSNewURL(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }
}
