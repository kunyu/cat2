package com.crazyloong.cat.rshainan.constant;

import org.apache.commons.lang.StringUtils;

/**
 * @Author : crazyloongcat
 * @Date :2022/5/29 23:20
 * @Description :
 */
public enum RewardOrderType {
    PAYED("1","订单支付"),
    CANSUL("3","订单取消"),;

    public String code;
    public String desc;

    RewardOrderType(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (RewardOrderType value : values()) {
            if (value.code.equals(code)) {
                return value.desc;
            }
        }
        return code;
    }
}
