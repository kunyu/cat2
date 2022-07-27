package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/19 21:10
 * @Description :
 */

@Data
public class CreateOrderRep {

    private String cdfMemberCode;
    private String giftCardAmount;
    private String memberCode;
    private List<MerchantListDTO> merchantList;
    private List<PlatFormSubsidyDTO> platFormSubsidy;
    private List<String> platformCouponList;
    private RecipientDTO recipient;
    private UserInfoDTO userInfo;
    private String userMobile;
    public CreateOrderRep() {
        cdfMemberCode = "";
        giftCardAmount = "";
        List<MerchantListDTO> merchantListDTOS = new ArrayList<>();
        merchantListDTOS.add(new MerchantListDTO());
        merchantList = merchantListDTOS;
        // 企业福利额度
        platFormSubsidy = new ArrayList<>();
        // 优惠券
        platformCouponList = new ArrayList<>();
        // 地址信息
        recipient = new RecipientDTO();
        userInfo= new UserInfoDTO();
    }

    @Data
    public static class RecipientDTO {
        private String name;
        private String phone;
        private String fullAddress;
        private String addressId;
        private String country;
        private String city;
        private String province;
        private String district;
        private String address;
    }

    @NoArgsConstructor
    @Data
    public static class UserInfoDTO {
    }


    @Data
    public static class MerchantListDTO {
        private List<ItemListDTO> itemList;
        private List<MerchantCouponListDTO> merchantCouponList;
        private List<String> merchantGiftList;
        private String merchantID;
        private OrderInfoDTO orderInfo;

        public MerchantListDTO () {
            merchantGiftList = new ArrayList<>();
            merchantID = "cdfsunrise";
            orderInfo = new OrderInfoDTO();
            merchantCouponList = new ArrayList<>();
        }

        @NoArgsConstructor
        @Data
        public static class OrderInfoDTO {
            private String merchantActualPayment;
            private List<PromotionListDTO> promotionList;
            private Integer quantity;
            private String remark;
            private String shippingFee;
            private String subTotal;
            private String taxFee;

            @NoArgsConstructor
            @Data
            public static class PromotionListDTO {
                private String amount;
                private String amountColor;
                private Boolean isCoupon;
                private Boolean isCouponGive;
                private Boolean isGift;
                private Boolean isIntegral;
                private Boolean isSubsidy;
                private String name;
                private String remark;
                private String subTitle;
                private String title;
            }
        }

        @NoArgsConstructor
        @Data
        public static class ItemListDTO {
            private String giftKeyID;
            private String goodsID;
            private Boolean isGift;
            private Integer leFoxID;
            private String price;
            private PurchaseTypeDTO purchaseType;
            private Integer quantity;

            @NoArgsConstructor
            @Data
            public static class PurchaseTypeDTO {
                private String purchaseModeName;
                private String purchaseModeType;
            }
        }

        @NoArgsConstructor
        @Data
        public static class MerchantCouponListDTO {
            private String activityID;
            private String couponAmount;
            private String couponFullName;
            private String couponID;
            private String couponName;
            private Boolean couponStatus;
            private String couponType;
            private String couponVersion;
            private String detail;
            private String expireDay;
            private String expireDayV2;
            private Boolean hasGoods;
            private Boolean isAddOn;
            private Boolean isDiscount;
            private Boolean isFuture;
            private Boolean isGift;
            private Boolean isNeedReceive;
            private Boolean isPack;
            private Boolean isReduction;
            private Boolean isSelected;
            private Boolean isSellOff;
            private String label;
            private Boolean lock;
        }
    }

    @NoArgsConstructor
    @Data
    public static class PlatFormSubsidyDTO {
        private Integer amount;
        private String available;
        private Boolean isSelected;
        private String isSubsidy;
        private String issuerName;
        private String name;
        private Integer point;
        private String remark;
        private List<RuleDTO> rule;
        private String subsidyAmount;
        private String subsidyName;

        @NoArgsConstructor
        @Data
        public static class RuleDTO {
            private String formula;
            private String name;
            private String priority;
            private String type;
        }
    }
}
