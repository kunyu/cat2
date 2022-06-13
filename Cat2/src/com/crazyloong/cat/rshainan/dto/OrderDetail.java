package com.crazyloong.cat.rshainan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/5/28 21:57
 * @Description : 订单地址明细
 */
@NoArgsConstructor
@Data
public class OrderDetail {

    private Integer afterStatus;
    private Integer cancelStatus;
    private Integer payStatus;
    private String mainOrderId;
    private String createTime;
    private String payTime;
    private String statusName;
    private String receiveName;
    private String receiveProvince;
    private String receiveCity;
    private String receiveDistrict;
    private String receiveAddress;
    private String receivePhone;
    private String traceName;
    private String traceTime;
    private Double dealAmount;
    private Double needPayAmount;
    private Double discountAmount;
    private Double usePointAmount;
    private Double useCouponAmount;
    private Integer packageCount;
    private Double useMemberCouponAmount;
    private List<ListDTO> list;
    private List<?> giftList;
    private Integer invoiceStatus;

    @NoArgsConstructor
    @Data
    public static class ListDTO {
        private String name;
        private String smallImage;
        private String goodsId;
        private Integer count;
        private Double price;
    }

}
