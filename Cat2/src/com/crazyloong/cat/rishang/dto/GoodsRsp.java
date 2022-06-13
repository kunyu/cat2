package com.crazyloong.cat.rishang.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/4/11 20:38
 * @Description :
 */
@NoArgsConstructor
@Data
public class GoodsRsp {

    private Integer abiid;
    private String mainname;
    private String subtitle;
    private String brandid;
    private Object brandname;
    private Object categoryid;
    private Object categoryname;
    private Integer price;
    private Integer realprice;
    private Integer show360;
    private String imgUrl;
    private String stock;
    private Object property1;
    private Object property2;
    private Object property3;
    private Integer taxType;
    private Integer intstock;
    private Integer integrate;
    private Object saleitems;
    private List<?> prometion;
    private String prometionTitle;
    private String integrateTitle;
}
