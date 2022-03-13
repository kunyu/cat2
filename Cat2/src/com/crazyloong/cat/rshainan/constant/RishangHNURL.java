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
    RSHN_FINDCATEGORYLIST("/mini/findCategoryList","查找所有分类");

    public String code;
    public String name;

    RishangHNURL(String code,String name) {
        this.code = code;
        this.name = name;
    }

}
