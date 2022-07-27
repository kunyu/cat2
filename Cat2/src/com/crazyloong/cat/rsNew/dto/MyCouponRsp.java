package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/18 12:19
 * @Description :
 */
@Data
public class MyCouponRsp {
    private ResponseHeadDTO responseHead;
    private List<MyCouponListDTO> myCouponList;
    private Integer amount;

    @NoArgsConstructor
    @Data
    public static class ResponseHeadDTO {
        private Boolean isSuccess;
        private String resultMessage;
        private String latestVersion;
        private Integer code;
        private String traceId;
    }

    @NoArgsConstructor
    @Data
    public static class MyCouponListDTO {
        private String phone;
        private Integer id;
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
    public MyCouponRsp() {
        responseHead = new ResponseHeadDTO();
        myCouponList = new ArrayList<>();
        amount =0;
    }
}
