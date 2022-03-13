package com.crazyloong.cat.rshainan.dto;

import lombok.Data;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/13 21:05
 * @Description : 日上海南请求参数
 */
@Data
public class HNReq {
    /*  账号  */
    private String phone;
    /*  密码  */
    private String password;
    /*  token   */
    private String token;
    /*  分类 */
    private String categoryId;
    /*  查询数量  */
    private String pageSize;
    /*  品牌ID  */
    private String brandId;
    /*  最后一件商品ID  */
    private String goodsId;
}
