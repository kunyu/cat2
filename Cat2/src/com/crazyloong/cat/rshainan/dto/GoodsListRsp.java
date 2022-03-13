package com.crazyloong.cat.rshainan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/13 21:46
 * @Description : 日上海南查询商品返回
 */
@NoArgsConstructor
@Data
public class GoodsListRsp {

    private String title;
    private Integer last;
    private List<ListDTO> list;

    @NoArgsConstructor
    @Data
    public static class ListDTO {
        private String goodsId;
        private String smallImage;
        private Double salesPrice;
        private Integer isOnSale;
        private String productName;
        private List<String> labels;
        private Integer count;
    }
}
