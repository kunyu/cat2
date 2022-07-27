package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/17 20:25
 * @Description :
 */
@NoArgsConstructor
@Data
public class OrderListRsp {

    private Integer page;
    private Integer pageSize;
    private Integer totalPage;
    private Integer totalCounts;
    private List<ListDTO> list;

    @NoArgsConstructor
    @Data
    public static class ListDTO {
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
        private Object cancelType;
        private Integer prePaidCardStatus;
        private Integer quantity;
        private String outOfStockInfo;
        private OperationInfoDTO operationInfo;
        private List<?> outOfStockList;
        private Object noParticalRefundNote;
        private String tip;
        private Object clearanceInfo;
        private Object returnCoupon;
        private List<GoodsListDTO> goodsList;
        private String goodsNames;
        private String quantitys;
        private String merchantNum;
        private String userName;
        private String mobile;
        private String address;
        private String orderTime;
        private String statusName;
        private Double price;

        @NoArgsConstructor
        @Data
        public static class OperationInfoDTO {
            private Boolean canCancel;
            private Boolean canPay;
            private Boolean canConfirmReceipt;
            private Boolean canDelayReceipt;
            private Boolean canCheckLogistics;
            private Boolean guideRefund;
            private Object canRefund;
            private Boolean showRefund;
            private Object canDrawLottery;
        }

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
            private Object refundStatus;
            private Object isDiscount;
            private String orderId;
            private String merchantId;
            private String merchantType;
            private String merchantText;
            private Object giveaway;
            private Object linkedActivities;
            private Object linkedGoods;
            private String leFoxID;
        }
    }

}
