package com.crazyloong.cat.rishang.dto;

import lombok.Data;

import java.util.List;

/**
 * @author YPLI
 * @description 提交订单请求对象
 * @date 2022/1/8 0008 21:52
 **/
@Data
public class SubmitOrderReq {

    /**
     * wishid : 46956555
     * name : 吴女士
     * tel : 13877177706
     * address : 广东省 深圳市 龙岗区 布吉街道布沙路锦绣山庄小区电话联系
     * issue : 0
     * type : 2
     * abiids : [177412,323648]
     * couponscode : tgiVgQYo
     * rightscode : -999
     */

    private int wishid;
    private String name;
    private String tel;
    private String address;
    private String issue;
    private int type;
    private String couponscode;
    private String rightscode;
    private List<Integer> abiids;
}
