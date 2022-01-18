package com.crazyloong.cat.dto;

import lombok.Data;

/**
 * @author YPLI
 * @description 优惠码 返回对象
 * @date 2022/1/8 0008 20:54
 **/
@Data
public class VipCodeRsp {

    /**
     * id : 8218119.0
     * start_time : 2021-12-10
     * end_time : 2022-02-01
     * timestr : 2021-12-10至2022-02-01
     * code : 9F00E9FD329716390728699264
     * showcode : ****699264
     * title : 盛.礼券.H.A.P58
     * tip : 优
     * msg : 最低金额:800,最多商品数量:12
     * states : 0
     * statedesc : 未使用
     * update : 10
     * updatetip : *可升级优选券可享订单95折
     * updatemsg : *重新选择优选券刷新优惠信息
     * max_amount : 0.0
     */

    private double id;
    private String start_time;
    private String end_time;
    private String timestr;
    private String code;
    private String showcode;
    private String title;
    private String tip;
    private String msg;
    private int states;
    private String statedesc;
    private String update;
    private String updatetip;
    private String updatemsg;
    private double max_amount;

}
