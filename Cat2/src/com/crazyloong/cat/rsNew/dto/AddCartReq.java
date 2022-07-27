package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : crazyloongcat
 * @Date :2022/7/27 21:55
 * @Description :
 */
@NoArgsConstructor
@Data
public class AddCartReq {

    private ItemDTO item;

    @Data
    public static class ItemDTO {
        private String goodsID;
        private String goodsVersion;
        private String merchantID;
        private String price;
        private PurchaseTypeDTO purchaseType;
        private Integer quantity;
        private String termTermID;

        public ItemDTO(){
            purchaseType = new PurchaseTypeDTO();
            merchantID = "cdfsunrise";
        }

        @Data
        public static class PurchaseTypeDTO {
            private String purchaseModeName;
            private String purchaseModeType;

            public PurchaseTypeDTO(){
                purchaseModeName = "国内购";
                purchaseModeType = "1";
            }
        }
    }
}
