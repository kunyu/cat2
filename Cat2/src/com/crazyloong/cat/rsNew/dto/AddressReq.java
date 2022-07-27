package com.crazyloong.cat.rsNew.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/25 15:33
 * @Description :
 */

@Data
public class AddressReq {

    private List<Object> merchantIds;
    public AddressReq() {
        merchantIds = new ArrayList<>();
    }
}
