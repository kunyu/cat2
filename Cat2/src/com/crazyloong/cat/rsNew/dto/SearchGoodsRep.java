package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/19 21:04
 * @Description :
 */
@NoArgsConstructor
@Data
public class SearchGoodsRep {

    private String goodsID;
    private String merchantID;
    private String purchaseType;
}
