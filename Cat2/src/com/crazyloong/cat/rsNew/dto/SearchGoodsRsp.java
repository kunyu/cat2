package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/19 21:00
 * @Description :
 */
@NoArgsConstructor
@Data
public class SearchGoodsRsp {

    private ResponseHeadDTO responseHead;
    private List<GoodsListDTO> goodsList;
    private Object termTermList;
    private Object specificationList;
    private BrandInfoDTO brandInfo;
    private String termTermID;
    private String brandID;
    private String serverTime;
    private Integer serverTimeSeconds;
    private GiftMapDTO giftMap;
    private Integer addCartNum;

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
    public static class BrandInfoDTO {
    }

    @NoArgsConstructor
    @Data
    public static class GiftMapDTO {
    }

    @NoArgsConstructor
    @Data
    public static class GoodsListDTO {
        private String goodsID;
        private String price;
        private String originalPrice;
        private String costPrice;
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
        private PurchaseTypeMapDTO purchaseTypeMap;
        private String chineseBrandName;
        private String englishBrandName;
        private String goodsName;
        private String goodsSubName;
        private Object specifications;
        private Object termSpecifications;
        private String specificationStringWithDefault;
        private String specificationString;
        private List<DescDTO> desc;
        private Object showPic;
        private String notice;
        private String goodsVersion;
        private Integer quantity;
        private PurchaseTypeDTO purchaseType;
        private List<PurchaseTypeListDTO> purchaseTypeList;
        private Boolean onSale;
        private VolumeDTO volume;
        private WeightDTO weight;
        private Boolean isGift;
        private String giftPrice;
        private String giftLabel;
        private List<SubItemDTO> subItem;
        private Object sameItem;
        private String refundText;
        private Integer leFoxID;
        private ShareModelDTO shareModel;
        private BannerModelDTO bannerModel;

        @NoArgsConstructor
        @Data
        public static class PurchaseTypeMapDTO {
            private _$1DTO $1;

            @NoArgsConstructor
            @Data
            public static class _$1DTO {
                private String price;
                private String originalPrice;
                private String costPrice;
                private String promotionPrice;
                private String buyPrice;
                private String buyPriceInAdvance;
                private String buyPriceInAdvanceText;
                private String buyPriceText;
                private List<BuyPriceListDTO> buyPriceList;
                private Object buyPriceInAdvanceList;
                private List<MerchantCouponListDTO> merchantCouponList;
                private Boolean hasPromotion;
                private Integer stock;
                private Integer availableStock;
                private Integer limitStock;
                private String limitStockRule;
                private Boolean isLimit;
                private String limitGoodsLabel;
                private String limitGoodsText;
                private Boolean isFlashSale;
                private String flashSaleStartTime;
                private Integer flashSaleStartTimeSeconds;
                private String flashSaleEndTime;
                private Integer flashSaleEndTimeSeconds;
                private Boolean isPromotion;
                private PromotionConfigDTO promotionConfig;

                @NoArgsConstructor
                @Data
                public static class PromotionConfigDTO {
                    private String bg;
                    private String buyPrice;
                    private String buyPriceInAdvance;
                    private String buyPriceInAdvanceBg;
                    private String price;
                    private String time;
                    private String logo;
                    private String listLogo;
                    private String unitImg;
                    private String unitImg1;
                }

                @NoArgsConstructor
                @Data
                public static class BuyPriceListDTO {
                    private String activityName;
                    private String activityAmount;
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
            }
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
        public static class ShareModelDTO {
            private String shareType;
            private String title;
            private String weChatLink;
            private String h5Link;
        }

        @NoArgsConstructor
        @Data
        public static class BannerModelDTO {
            private String image;
            private String name;
            private String linkUrl;
            private String wxLinkUrl;
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
