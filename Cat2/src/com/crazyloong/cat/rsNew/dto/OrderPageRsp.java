package com.crazyloong.cat.rsNew.dto;

import lombok.Data;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/18 10:47
 * @Description :
 */
@Data
public class OrderPageRsp {
    private String userOrderId;
    private String orderTime;
    private String statusName;
    private String realPrice;
    private String userName;
    private String address;
    private String mobile;
    private String goodsNames;
    private String quantitys;
    private String merchantNum;
    private Integer total;



}
