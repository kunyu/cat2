package com.crazyloong.cat.dto;

import lombok.Data;

/**
 * @author YPLI
 * @description 日上产品对象
 * @date 2022/1/8 0008 22:59
 **/
@Data
public class GoodsBean {
    private int id;
    private int abiid;
    private int num;
    private String abname;
    private Object abname_en;
    private int oprice;
    private int price;
    private int pricetype;
    private int limit;
    private int stock;
}
