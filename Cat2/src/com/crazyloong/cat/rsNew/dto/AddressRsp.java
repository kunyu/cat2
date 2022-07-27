package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/25 15:34
 * @Description :
 */
@NoArgsConstructor
@Data
public class AddressRsp {
    private String aid;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String address;
    private String phone;
    private String linkName;
    private Boolean isDefault;
    private Long timestamp;
    private Object logisticsControl;
    private Object isLimitOrderConfirm;

}
