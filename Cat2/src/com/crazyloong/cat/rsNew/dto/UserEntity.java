package com.crazyloong.cat.rsNew.dto;

import lombok.Data;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/16 23:56
 * @Description :
 */
@Data
public class UserEntity {

    public UserEntity(RSNewUserInfo userInfo){
        this.userInfo = userInfo;
        lastPutTime = System.currentTimeMillis();
    }
    private RSNewUserInfo userInfo;
    private Long lastPutTime;
}
