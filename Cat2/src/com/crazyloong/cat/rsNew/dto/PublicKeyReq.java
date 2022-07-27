package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/18 18:35
 * @Description :
 */
@NoArgsConstructor
@Data
public class PublicKeyReq {

    private String sign;
    private Long timeStamp;
}
