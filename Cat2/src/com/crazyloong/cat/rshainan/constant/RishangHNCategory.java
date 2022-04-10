package com.crazyloong.cat.rshainan.constant;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/15 22:17
 * @Description : 商品分类美剧
 */
public enum RishangHNCategory {
    SKINCARE(1,"护肤"),
    PERFUME(41,"彩妆"),
    COLOURS(66,"香水"),;

    public Integer code;
    public String name;

    RishangHNCategory(Integer code,String name) {
        this.code = code;
        this.name = name;
    }
}
