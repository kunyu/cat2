package com.crazyloong.cat.rsNew.dto;

import lombok.Data;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/18 18:32
 * @Description :
 */
@Data
public class ChangePasswordRep {
    private String phone;
    private String newPassword;
    private String originalPassword;
    private String publicKey;
}
