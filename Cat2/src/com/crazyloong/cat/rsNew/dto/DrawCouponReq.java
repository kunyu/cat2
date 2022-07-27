package com.crazyloong.cat.rsNew.dto;

import lombok.Data;

/**
 * @Author : crazyloongcat
 * @Date :2022/7/26 23:30
 * @Description : 领取优惠券输入对象
 */
@Data
public class DrawCouponReq {

    private Integer limitNum;
    private Boolean newFlag;
}
