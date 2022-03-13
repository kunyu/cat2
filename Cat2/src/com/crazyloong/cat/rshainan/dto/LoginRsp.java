package com.crazyloong.cat.rshainan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/13 21:41
 * @Description :
 */
@NoArgsConstructor
@Data
public class LoginRsp {

    private String token;
    private String phone;
    private String bindId;
}
