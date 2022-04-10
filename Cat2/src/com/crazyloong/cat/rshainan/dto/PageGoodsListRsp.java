package com.crazyloong.cat.rshainan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/15 22:05
 * @Description : 页面返回对象
 */
@Data
public class PageGoodsListRsp {
    /**
    * 热卖
    */
    private List<PageGoods> sell_hot;
    /**
     * 护肤
     */
    private List<PageGoods> skincare;
    /**
     * 香水
     */
    private List<PageGoods> perfume;
    /**
     * 彩妆
     */
    private List<PageGoods> colours;


    @NoArgsConstructor
    @Data
    public static class PageGoods {
        private String goodsId;
        private Double salesPrice;
        private String productName;
        private Integer count;
    }

}
