package com.crazyloong.cat.rshainan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/19 17:06
 * @Description : 下单准备返回信息
 */
@NoArgsConstructor
@Data
public class PrepareOrderRsp {


    private Integer code;
    private Integer totalCount;
    private String mainOrderId;
    private Double originalAmount;
    private Double dealAmount;
    private Double discountAmount;
    private Double needPayAmount;
    private Integer pointMax;
    private Integer couponAmount;
    private Integer memberCouponAmount;
    private Boolean needCheck;
    private MemberCheckDTO memberCheck;
    private MemberAddressDTO memberAddress;
    private List<?> giftList;
    private List<GoodsListDTO> goodsList;
    private String mac;
    private Integer state;

    @NoArgsConstructor
    @Data
    public static class MemberCheckDTO {
        private String id;
        private String memberId;
        private String certNumber;
        private String certName;
        private String trafficName;
        private String trafficNumber;
        private String leaveDate;
        private String createTime;
        private String updateTime;
        private Boolean newX;
    }

    @NoArgsConstructor
    @Data
    public static class MemberAddressDTO {
        private String id;
        private String addressId;
        private String memberId;
        private String name;
        private String province;
        private String provinceAddressId;
        private String city;
        private String cityAddressId;
        private String district;
        private String districtAddressId;
        private String address;
        private String mobile;
        private Integer active;
        private String createTime;
        private String updateTime;
        private Boolean newX;
    }

    @NoArgsConstructor
    @Data
    public static class GoodsListDTO {
        private String goodsId;
        private Integer count;
        private String productName;
        private String smallImage;
        private Double price;
        private String activityId;
    }
}
