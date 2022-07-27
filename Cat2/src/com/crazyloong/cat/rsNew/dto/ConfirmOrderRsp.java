package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/19 21:07
 * @Description :
 */
@NoArgsConstructor
@Data
public class ConfirmOrderRsp {


    private ResponseHeadDTO responseHead;
    private List<MerchantListDTO> merchantList;
    private RecipientDTO recipient;
    private Object platformCouponList;
    private List<PlatFormSubsidyDTO> platFormSubsidy;
    private List<PlatformPromotionListDTO> platformPromotionList;
    private GiftCartDTO giftCart;
    private String toTalAmount;
    private Integer quantity;
    private Boolean isMinimumCharge;
    private Object orderModify;
    private List<PurchaseTypeListDTO> purchaseTypeList;
    private String minimumChargeText;
    private String platFormInfoUrl;
    private ActivityIDDTO activityID;

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
    public static class GiftCartDTO {
        private String giftCardAmount;
        private String giftCardTitle;
        private String giftCardSubTitle;
    }

    @NoArgsConstructor
    @Data
    public static class ActivityIDDTO {
        private List<Integer> $61a985b2cd881a000194f7bd;
    }

    @NoArgsConstructor
    @Data
    public static class MerchantListDTO {
        private String merchantID;
        private String merchantName;
        private List<MerchantCouponListDTO> merchantCouponList;
        private List<MerchantGiftListDTO> merchantGiftList;
        private Object merchantIntegralList;
        private List<ItemListDTO> itemList;
        private Object secKillItemList;
        private Object suitItemList;
        private OrderInfoDTO orderInfo;

        @NoArgsConstructor
        @Data
        public static class OrderInfoDTO {
            private String shippingFee;
            private String taxFee;
            private InvoiceDTO invoice;
            private String remark;
            private Integer quantity;
            private String subTotal;
            private String merchantActualPayment;
            private List<PromotionListDTO> promotionList;

            @NoArgsConstructor
            @Data
            public static class InvoiceDTO {
                private String type;
                private String company;
                private String idNumber;
                private String email;
                private String remark;
                private Boolean isShowInvoice;
                private Boolean isShowInvoiceCell;
                private String invoiceText;
            }

            @NoArgsConstructor
            @Data
            public static class PromotionListDTO {
                private String title;
                private String subTitle;
                private String name;
                private String remark;
                private String amount;
                private String amountColor;
                private Boolean isCoupon;
                private Boolean isSubsidy;
                private Boolean isGift;
                private Boolean isIntegral;
                private Boolean isCouponGive;
                private Object image;
            }
        }
        @NoArgsConstructor
        @Data
        public static class MerchantCouponListDTO {
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

        @NoArgsConstructor
        @Data
        public static class MerchantGiftListDTO {
            private String activityID;
            private Boolean isOrder;
            private String giftType;
            private String giftName;
            private Integer amount;
            private String detail;
            private Boolean hasGoods;
            private String giftText;
            private List<GiftListDTO> giftList;

            @NoArgsConstructor
            @Data
            public static class GiftListDTO {
                private String giftKeyID;
                private String giftID;
                private String giftSaleType;
                private String giftName;
                private String giftPic;
                private String giftPrice;
                private Boolean isSelected;
                private Integer giftAmount;
                private Integer giftCount;
                private Integer stock;
                private Boolean onSale;
                private Integer order;
            }
        }

        @NoArgsConstructor
        @Data
        public static class ItemListDTO {
            private String cartID;
            private String goodsID;
            private String goodsCode;
            private String brandCode;
            private Integer goodsType;
            private String categoryCode;
            private String merchantID;
            private String merchantName;
            private String merchantShortName;
            private String merchantIMId;
            private List<String> picList;
            private String smallPic;
            private String price;
            private String taxFreePrice;
            private String originalPrice;
            private String costPrice;
            private String buyPrice;
            private String buyPriceText;
            private String buyPriceInAdvance;
            private String buyPriceInAdvanceText;
            private Object merchantCouponList;
            private String chineseBrandName;
            private String englishBrandName;
            private String goodsName;
            private String goodsSubName;
            private List<SpecificationsDTO> specifications;
            private Object termSpecifications;
            private String specificationStringWithDefault;
            private String specificationString;
            private List<DescDTO> desc;
            private List<String> showPic;
            private String notice;
            private String noticeToBuy;
            private String goodsVersion;
            private Integer stock;
            private StockListDTO stockList;
            private Integer availableStock;
            private Integer limitStock;
            private Object limitStockList;
            private String limitStockRule;
            private Integer quantity;
            private PurchaseTypeDTO purchaseType;
            private List<PurchaseTypeListDTO> purchaseTypeList;
            private Boolean onSale;
            private VolumeDTO volume;
            private WeightDTO weight;
            private Boolean isGift;
            private Boolean isLimit;
            private String limitGoodsLabel;
            private String limitGoodsText;
            private List<SubItemDTO> subItem;
            private Object sameItem;
            private String refundText;
            private Integer leFoxID;
            private String newCustomerTip;
            private Integer order;
            private String termTermID;
            private Boolean hasGift;
            private String promotionLogo;
            private GiftModelDTO giftModel;
            private String itemType;
            private Object listBanner;
            private Object giftItem;

            @NoArgsConstructor
            @Data
            public static class StockListDTO {
                private Integer $1;
            }

            @NoArgsConstructor
            @Data
            public static class PurchaseTypeDTO {
                private String purchaseModeType;
                private String purchaseModeName;
            }

            @NoArgsConstructor
            @Data
            public static class VolumeDTO {
                private String unit;
                private String longX;
                private String width;
                private String height;
                private String tip;
            }

            @NoArgsConstructor
            @Data
            public static class WeightDTO {
                private String unit;
                private String weight;
                private String tip;
            }

            @NoArgsConstructor
            @Data
            public static class GiftModelDTO {
                private String giftImage;
                private String giftName;
            }

            @NoArgsConstructor
            @Data
            public static class SpecificationsDTO {
                private String specificationType;
                private String specificationValue;
                private Boolean isSoldOut;
            }

            @NoArgsConstructor
            @Data
            public static class DescDTO {
                private String specificationType;
                private String specificationValue;
                private Boolean isSoldOut;
            }

            @NoArgsConstructor
            @Data
            public static class PurchaseTypeListDTO {
                private String purchaseModeType;
                private String purchaseModeName;
            }

            @NoArgsConstructor
            @Data
            public static class SubItemDTO {
                private String goodsID;
                private String merchantID;
                private String merchantName;
                private String order;
            }
        }
    }

    @NoArgsConstructor
    @Data
    public static class PlatFormSubsidyDTO {
        private String subsidyName;
        private String isSubsidy;
        private String subsidyAmount;
        private String remark;
        private Boolean isSelected;
        private Integer amount;
        private String available;
        private Integer point;
        private String issuerName;
        private String name;
        private List<RuleDTO> rule;

        @NoArgsConstructor
        @Data
        public static class RuleDTO {
            private String formula;
            private String name;
            private String priority;
            private String type;
        }
    }

    @NoArgsConstructor
    @Data
    public static class PlatformPromotionListDTO {
        private String title;
        private String subTitle;
        private String name;
        private String remark;
        private String amount;
        private String amountColor;
        private Boolean isCoupon;
        private Boolean isSubsidy;
        private Boolean isGift;
        private Boolean isIntegral;
        private Boolean isCouponGive;
        private Object image;
    }

    @NoArgsConstructor
    @Data
    public static class PurchaseTypeListDTO {
        private String purchaseTypeName;
        private String purchaseTypeID;
        private Integer goodsCount;
        private Boolean isSelected;
        private List<MerchantGoodsDTO> merchantGoods;

        @NoArgsConstructor
        @Data
        public static class MerchantGoodsDTO {
            private String merchantID;
            private List<MerchantGoodsListDTO> merchantGoods;

            @NoArgsConstructor
            @Data
            public static class MerchantGoodsListDTO {
                private String goodsID;
                private Integer quantity;
                private String price;
                private String costPrice;
                private PurchaseTypeDTO purchaseType;
                private Boolean isGift;
                private String giftKeyID;

                @NoArgsConstructor
                @Data
                public static class PurchaseTypeDTO {
                    private String purchaseModeType;
                    private String purchaseModeName;
                }
            }
        }
    }
}
