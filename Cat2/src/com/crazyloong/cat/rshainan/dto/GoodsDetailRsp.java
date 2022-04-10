package com.crazyloong.cat.rshainan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/13 23:26
 * @Description : 日上海南商品详情返回
 */
@NoArgsConstructor
@Data
public class GoodsDetailRsp {

    /* 商品ID */
    private String goodsId;
    private Boolean needCheck;
    private String checkDescribe;
    /* 售价 */
    private Double salesPrice;
    /* 是否打包出售 */
    private Integer isPackage;
    /* 是否生效 */
    private Integer active;
    /* 商品名称 */
    private String productName;
    private String smallImage;
    /* 品牌对象 */
    private BrandDTO brand;
    private String pickupDescribe;
    private Integer brandCount;
    private List<PhotoListDTO> photoList;
    private List<PromotionDTO> promotion;
    private List<?> category;
    /* 商品数量 */
    private Integer count;
    private List<PropsDTO> props;
    private List<?> limitList;
    private String limitDesc;
    private String pickupTypeDesc;
    private List<String> activityLabels;

    @NoArgsConstructor
    @Data
    public static class BrandDTO {
        private String brandId;
        private String chineseName;
        private String intro;
        private String logo;
        private String smallImage;
        private String bigImage;
        private String from;
        private Integer isDiy;
        private String content;
    }

    @NoArgsConstructor
    @Data
    public static class PhotoListDTO {
        private String smallImage;
        private String bigImage;
        private String originalImage;
        private Integer sort;
        private String intro;
        private Integer active;
    }

    @NoArgsConstructor
    @Data
    public static class PromotionDTO {
        private String promotionId;
        private String shotName;
        private String longName;
        private String name;
        private Integer idType;
        private String idValue;
        private Integer urlType;
        private String urlValue;
    }

    @NoArgsConstructor
    @Data
    public static class PropsDTO {
        private String propId;
        private String name;
        private String value;
        private Integer index;
        private Integer type;
    }
}
