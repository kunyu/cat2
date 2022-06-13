package com.crazyloong.cat.rshainan.dto;

import lombok.Data;

import java.util.List;

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
    private Integer categoryId;
    /*  查询数量  */
    private String pageSize;
    /*  查询第几页  */
    private String pageNum;
    /*  品牌ID  */
    private String brandId;
    /*  最后一件商品ID  */
    private String goodsId;
    private String keyword;
    /* 订单类型  0 - 全部 1 - 待支付 2 - 代发货 3 - 待收货 4 - 已完成*/
    private String type;
    private String mainOrderId;
    private List<RewardDay.ListDTO> mainOrderIds;

}
