package com.crazyloong.cat.dto;

import javax.validation.constraints.NotEmpty;

public class UserLogonParam {

    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
