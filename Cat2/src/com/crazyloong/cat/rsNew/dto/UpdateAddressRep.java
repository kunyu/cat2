package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/19 21:12
 * @Description :
 */
@NoArgsConstructor
@Data
public class UpdateAddressRep {

    private String address;
    private String aid;
    private String areaName;
    private String cityName;
    private Boolean isDefault;
    private Boolean isLimitOrderConfirm;
    private String linkName;
    private String phone;
    private String provinceName;
    private Integer timestamp;
}
