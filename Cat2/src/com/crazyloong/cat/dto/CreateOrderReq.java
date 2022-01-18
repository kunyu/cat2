package com.crazyloong.cat.dto;

import lombok.Data;

import java.util.List;

/**
 * @author YPLI
 * @description  生成订单请求对象
 * @date 2022/1/8 0008 20:23
 **/
@Data
public class CreateOrderReq {
    /* 类型  2-优选会员 4-在线商城 */
    private int type;
    /* 产品ID */
    private List<Integer> abiids;
    /* 优惠券内部码 */
    private String couponscode;
    /* 不知道什么东西 */
    private String rightscode;
}
