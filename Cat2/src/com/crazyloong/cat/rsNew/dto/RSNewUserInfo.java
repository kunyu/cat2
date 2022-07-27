package com.crazyloong.cat.rsNew.dto;

import lombok.Data;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/16 23:47
 * @Description : 中免日上會員信息
 */
@Data
public class RSNewUserInfo {

    private String accessToken;

    private String userId;

    private String phone;

    private String memberCode;


}
