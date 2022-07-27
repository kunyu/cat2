package com.crazyloong.cat.rsNew.constant;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/17 18:19
 * @Description :
 */
public enum PhoneFlag {
    RS("RS","日上会员"),
    RS_NEW("RSN","中免日上"),
    HN("HN","海南会员"),
    ;

    public String code;
    public String desc;

    PhoneFlag(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }
}
