package com.crazyloong.cat.rshainan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/5/24 20:03
 * @Description :
 */
@NoArgsConstructor
@Data
public class RewardOrder {

    private List<ListDTO> list;
    private Integer total;
    private String id;

    @NoArgsConstructor
    @Data
    public static class ListDTO {
        // id
        private String id;
        // 时间
        private String time;
        // 支付金额
        private Double payAmount;
        // 订单ID
        private String orderId;
        // 预计奖励金额
        private Double giveAmount;
        // 类型 1-订单支付   3- 订单取消
        private Integer type;
        private String typeName;
    }

}
