package com.crazyloong.cat.rsNew.service;

import com.crazyloong.cat.rishang.dto.RiOrderReq;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone;
import com.crazyloong.cat.rsNew.dto.DrawCouponReq;
import com.crazyloong.cat.rsNew.dto.MyCouponRsp;
import com.crazyloong.cat.rsNew.dto.SubmitOrderRep;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/19 21:21
 * @Description :中免日上服务类 使用类
 */
public interface RSNUseService {

    /**
     * 功能描述： 领取优惠券
     * @Param:
     * @Return: void
     * @Author:
     * @Date: 2022/6/17 18:44
     * @Description:
     */
    void drawCoupon(List<RiOrderPhone> phoneList, DrawCouponReq drawCouponReq);

    /**
     * 功能描述： 获取用户优惠券
     * @Param: [phone]
     * @Return: com.crazyloong.cat.rsNew.dto.MyCouponRsp
     * @Author:
     * @Date: 2022/6/18 12:22
     * @Description:
     */
    MyCouponRsp getAllMyCoupon(String phone);

    /**
    * 功能描述： 生成用户订单
    * @Param: []
    * @Return: void
    * @Author:
    * @Date: 2022/6/19 21:37
    * @Description:
    */
    void submitOrder(SubmitOrderRep submitOrderRep);

    /**
     * 功能描述： 下单
     * @Param: [riOrderReq]
     * @Return: void
     * @Author:
     * @Date: 2022/6/29 18:56
     * @Description:
     */
    void submitBatchOrder(RiOrderReq riOrderReq);

    /**
     * 功能描述：添加购物车及地址
     * @Param: [riOrderReq]
     * @Return: void
     * @Author:
     * @Date: 2022/7/27 22:24
     * @Description:
     */
    void addCarAndAddress(RiOrderReq riOrderReq);
}
