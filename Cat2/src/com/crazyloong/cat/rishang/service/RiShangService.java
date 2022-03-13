package com.crazyloong.cat.rishang.service;

import com.crazyloong.cat.rishang.dto.*;
import com.crazyloong.cat.pojo.PostBody;

import java.util.List;
import java.util.Map;


public interface RiShangService {
    // 添加购物车
    boolean addCart(int abiId,int num,String user);

    /**
     * @Description:生成订单
     * @Author: YPLI
     * @Date: 2022/1/8 0008 21:58
     * [req]
     * com.crazyloong.cat.rishang.dto.RiReturnRsp<com.crazyloong.cat.rishang.dto.CreateOrderRsp>
     **/
    CreateOrderRsp createOrder(String token,CreateOrderReq req);

    /**
     * @Description:提交订单
     * @Author: YPLI
     * @Date: 2022/1/8 0008 21:58
     * [req]
     * com.crazyloong.cat.rishang.dto.RiReturnRsp<java.lang.Integer>
     **/
    Integer submitOrder(String token,SubmitOrderReq req);

    /**
     *
     * @Description: 获取优惠券相关信息
     * @Author: YPLI
     * @Date:
     *
     **/
    VipCodeRsp getVipCode(String token,String code);

    /**
     * 获取当前登录用户的优惠券
     * @return
     */
    VipCodeRsp getVipCodeMyself(String token,String preferentialSum);

    /**
     * @Description: 获取订单信息
     * @Author: YPLI
     * @Date: 2022/1/8 0008 21:58
     * [orderId]
     * com.crazyloong.cat.rishang.dto.OrderRsp
     **/
    OrderRsp getOrder(Integer orderId);

    /**
     * @Description: 日上app登陆
     * @Author: YPLI
     * @Date: 2022/1/16 0016 14:20
     * [user, pwd]
     * java.lang.String
     **/
    String login(String user,String pwd);

    PlacedOrderRsp getPlacedOrder(String token,String wishid);

    /**
     * @Description: 获取我的订单信息 0-未提交 1-已提交 2-已完成
     * @Author: YPLI
     * @Date: 2022/1/8 0008 22:37
     * [token, state]
     * com.crazyloong.cat.rishang.dto.RiReturnRsp<com.crazyloong.cat.rishang.dto.WishRsp>
     **/
    RiReturnRsp<List<Object>> getWishs(String token, Integer state);

    /**
    * 功能描述：
    * @Param: 下订单
     * @param riOrderReq 
    * @Return: void
    * @Author: 
    * @Date: 2022/3/13 14:04
    * @Description: 
    */
    void placeOrderByCode(RiOrderReq riOrderReq) throws InterruptedException;

}
