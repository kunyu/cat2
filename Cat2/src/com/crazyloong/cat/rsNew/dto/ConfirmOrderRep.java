package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/19 21:05
 * @Description :
 */
@Data
public class ConfirmOrderRep {

    private Boolean isUseGiftCardPay;
    private String memberCode;
    private List<MerchantListDTO> merchantList;
    private List<?> platFormSubsidy;
    private List<?> platformCouponList;
    private String purchaseTypeID;
    private String userBalance;

    public ConfirmOrderRep() {
        isUseGiftCardPay = false;
        platformCouponList = new ArrayList<>();
        platFormSubsidy = new ArrayList<>();
        purchaseTypeID = "";
        userBalance = "";
        List<MerchantListDTO> merchantListDTOS = new ArrayList<>();
        merchantListDTOS.add(new MerchantListDTO());
        merchantList = merchantListDTOS;
    }



    @Data
    public static class MerchantListDTO {
        private List<ItemListDTO> itemList;
        private List<?> merchantCouponList;
        private List<?> merchantGiftList;
        private String merchantID;
        private List<?> merchantIntegralList;
        private OrderInfoDTO orderInfo;

        public MerchantListDTO() {
            merchantCouponList = new ArrayList<>();
            merchantGiftList = new ArrayList<>();
            merchantID = "cdfsunrise";
            merchantIntegralList = new ArrayList<>();
            orderInfo = new OrderInfoDTO();
            itemList = new ArrayList<>();
        }


        @Data
        public static class OrderInfoDTO {
            private String merchantActualPayment;
            private List<?> promotionList;
            private Integer quantity;
            private String remark;
            private String shippingFee;
            private String subTotal;
            private String taxFee;

            public OrderInfoDTO() {
                merchantActualPayment = "";
                promotionList = new ArrayList<>();
                quantity = 0;
                remark = "";
                shippingFee = "";
                subTotal = "";
                taxFee = "";
            }
        }


        @Data
        public static class ItemListDTO {
            private String giftKeyID;
            private String goodsID;
            private Boolean isGift;
            private Integer leFoxID;
            private String price;
            private PurchaseTypeDTO purchaseType;
            private Integer quantity;

            public ItemListDTO () {
                giftKeyID = "";
                isGift = false;
                price = "";
                purchaseType = new PurchaseTypeDTO();
            }

            @Data
            public static class PurchaseTypeDTO {
                private String purchaseModeName;
                private String purchaseModeType;
                public PurchaseTypeDTO() {
                    purchaseModeName = "国内购";
                    purchaseModeType = "1";
                }
            }
        }
    }
}
