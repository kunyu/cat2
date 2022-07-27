package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/18 18:27
 * @Description :
 */
@NoArgsConstructor
@Data
public class PublicKeyRsp {

    private String publicKey;
    private String expiresAt;
}
