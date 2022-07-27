package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author : crazyloongcat
 * @Date :2022/6/16 21:14
 * @Description :
 */
@NoArgsConstructor
@Data
public class CouponsDetailRsp {
    private Long id;
    private String couponID;
    private String label;
    private Integer activityType;
    private String activityID;
    private String couponVersion;
    private String couponName;
    private String couponFullName;
    private String couponAmount;
    private String expireDay;
    private String expireDayV2;
    private String couponType;
    private Boolean isNeedReceive;
    private Boolean couponStatus;
    private Boolean lock;
    private String detail;
    private Object giftList;
    private Object giftIDList;
    private Boolean isAddOn;
    private Boolean isReduction;
    private Boolean isGift;
    private Boolean isDiscount;
    private Boolean isSelected;
    private Boolean hasGoods;
    private Boolean isPack;
    private Boolean isFuture;
    private Boolean isSellOff;
    private Integer status;
    private String statusText;
}
