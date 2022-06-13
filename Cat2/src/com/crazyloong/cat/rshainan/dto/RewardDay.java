package com.crazyloong.cat.rshainan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/5/24 10:54
 * @Description :
 */
@NoArgsConstructor
@Data
public class RewardDay {
    private List<ListDTO> list;
    private Integer total;

    @NoArgsConstructor
    @Data
    public static class ListDTO {
        private String id;
        // 日期
        private String time;
        // 支付金额
        private Double payAmount;
        // 预计奖励金额
        private Double giveAmount;
    }

}
