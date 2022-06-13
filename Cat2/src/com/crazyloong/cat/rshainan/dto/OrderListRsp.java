package com.crazyloong.cat.rshainan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/5/19 20:28
 * @Description :
 */
@NoArgsConstructor
@Data
public class OrderListRsp {

    private List<Order> list;
    private Integer total;

    @NoArgsConstructor
    @Data
    public static class Order {
        private List<ListDTO> list;
        private Integer count;
        private Double totalAmount;
        private Double paidAmount;
        private Double needPayAmount;
        private String mainOrderId;
        private String time;
        private String statusName;
        private Integer cancelStatus;
        private Integer payStatus;
        private Integer invoiceStatus;
        private String receiveName;
        private String receiveAddress;
        private String receivePhone;
        private String goodsName;
        private String goodsCount;

        @NoArgsConstructor
        @Data
        public static class ListDTO {
            private String name;
            private String smallImages;
            private String goodsId;
        }
    }
}
