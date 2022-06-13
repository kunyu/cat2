package com.crazyloong.cat.rshainan.constant;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/13 22:05
 * @Description : 日上海南url美剧
 */
public enum RishangHNURL {

    RSHN_LOGINWITHPASSWORD("/mini/loginWithPassword","登录"),
    RSHN_FINDGOODSLIST("/mini/findGoodsList","查找商品"),
    RSHN_FINDGOODSLISTSEARCH("/mini/findGoodsListSearch","查找分类"),
    RSHN_FINDCATEGORYLIST("/mini/findCategoryList","查找所有分类"),
    RSHN_FINDGOODSDETAILBYIDALWAYS("/mini/findGoodsDetailByIdAlways","获取商品详情"),
    RSHN_FINDSUBJECTLIST("/mini/findSubjectList","获取商品首页"),
    RSHN_PREPAREORDER("/mini/getPrepareOrderWithGoods","下单前准备"),
    RSHN_GETMEMBERPOINTCLOSED("/mini/getMemberPointClosed","下单前准备1"),
    RSHN_GETORDERCOUPON("/infrastructure/getOrderCouponWithGoods","下单前准备2"),
    RSHN_ORDERMEMBERCOUPON("/mini/getOrderMemberCouponListWithGoods","下单前准备3"),
    RSHN_CONFIRMORDER("/core/confirmOrderWithGoods","下单"),
    RSHN_FINDORDERLIST("/infrastructure/findOrderList","获取订单列表"),
    RSHN_GETREWARDDAY("/order/getRewardDay","获取员工订单"),
    RSHN_GETREWARDORDER("/order/getRewardOrder","获取员工订单明细"),
    RSHN_FINDORDERDETAIL("/infrastructure/findOrderDetail","获取订单地址等明细信息"),
    ;

    public String code;
    public String desc;

    RishangHNURL(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }

}
