package com.crazyloong.cat.rshainan.dto;

import lombok.Data;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/21 21:05
 * @Description :
 */
@Data
public class TokenEntity {
    public TokenEntity(String token){
        this.token = token;
        lastPutTime = System.currentTimeMillis();
    }
    private String token;
    private Long lastPutTime;
}
