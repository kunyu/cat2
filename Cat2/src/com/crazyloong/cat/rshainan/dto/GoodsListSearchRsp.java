package com.crazyloong.cat.rshainan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/13 21:44
 * @Description : 日上海南查询商品类别返回
 */
@NoArgsConstructor
@Data
public class GoodsListSearchRsp {

    private List<CategoryDataListDTO> categoryDataList;
    private List<BrandDataListDTO> brandDataList;

    @NoArgsConstructor
    @Data
    public static class CategoryDataListDTO {
        private String name;
        private String categoryId;
    }

    @NoArgsConstructor
    @Data
    public static class BrandDataListDTO {
        private String name;
        private String brandId;
    }
}
