package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/17 20:32
 * @Description :
 */
@NoArgsConstructor
@Data
public class OrderDetailRsp {
    private String orderId;
    private String userOrderId;
    private String payOrderId;
    private String ancestorOrderId;
    private String merchantId;
    private String merchantName;
    private String buyMode;
    private String status;
    private String totalPrice;
    private String realPrice;
    private String discountPrice;
    private String prePaidCardAmount;
    private String cdfIntegralAmount;
    private Integer cancelType;
    private Integer prePaidCardStatus;
    private Integer quantity;
    private String outOfStockInfo;
    private OperationInfoDTO operationInfo;
    private List<?> outOfStockList;
    private String noParticalRefundNote;
    private String tip;
    private Object clearanceInfo;
    private Object returnCoupon;
    private String merchantPhone;
    private String kefuId;
    private String merchantType;
    private String merchantText;
    private String merchantNum;
    private String orderTime;
    private String confirmTime;
    private String payTime;
    private String systemTimestamp;
    private String userName;
    private String mobile;
    private String address;
    private String orderNote;
    private String invoice;
    private String fare;
    private String closedReason;
    private String buyUrl;
    private Integer deliveryStatus;
    private PickupInfoDTO pickupInfo;
    private List<DetailListDTO> detailList;
    private String statusTitle;
    private String statusSubtitle;
    private Boolean isLimitOrderConfirm;

    @NoArgsConstructor
    @Data
    public static class OperationInfoDTO {
        private Boolean canCancel;
        private Boolean canPay;
        private Boolean canConfirmReceipt;
        private Boolean canDelayReceipt;
        private Boolean canCheckLogistics;
        private Boolean guideRefund;
        private Boolean canRefund;
        private Boolean showRefund;
        private Boolean canDrawLottery;
    }

    @NoArgsConstructor
    @Data
    public static class PickupInfoDTO {
        private Object pickCode;
        private String addressId;
        private String country;
        private String province;
        private String city;
        private String district;
        private String detailedAddress;
        private String flightNumber;
    }

    @NoArgsConstructor
    @Data
    public static class DetailListDTO {
        private String merchantId;
        private String merchantName;
        private String merchantNum;
        private Object goodsIdList;
        private Object logisticsControl;
        private List<GoodsListDTO> goodsList;

        @NoArgsConstructor
        @Data
        public static class GoodsListDTO {
            private String goodsId;
            private String goodsName;
            private Object brandId;
            private String brandCode;
            private String tag;
            private String picture;
            private String originalPrice;
            private String discountPrice;
            private String realPrice;
            private Integer quantity;
            private Object shortageQuantity;
            private Object refundNo;
            private String refundStatus;
            private Object isDiscount;
            private String orderId;
            private String merchantId;
            private String merchantType;
            private String merchantText;
            private Boolean giveaway;
            private List<LinkedActivitiesDTO> linkedActivities;
            private List<LinkedGoodsDTO> linkedGoods;
            private String leFoxID;

            @NoArgsConstructor
            @Data
            public static class LinkedActivitiesDTO {
                private String activityId;
                private String activityName;
            }

            @NoArgsConstructor
            @Data
            public static class LinkedGoodsDTO {
                private String goodsId;
            }
        }
    }

}
