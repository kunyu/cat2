package com.crazyloong.cat.rishang.service;

import com.crazyloong.cat.rishang.dto.*;

import java.util.List;


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
    RiReturnRsp<List<Object>> getWishs(WishReq wishReq);

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

    /**
    * 功能描述： 获取日上app首页内容
    * @Param:
    * @Return: com.crazyloong.cat.rishang.dto.RiReturnRsp<java.util.List<com.crazyloong.cat.rishang.dto.GoodsRsp>>
    * @Author:
    * @Date: 2022/4/11 20:41
    * @Description:
    */
    RiReturnRsp<List<GoodsRsp>> getTops(String token);

    /**
    * 功能描述： 查询商品
    * @Param:
     * @param key
    * @Return: com.crazyloong.cat.rishang.dto.RiReturnRsp<java.util.List<com.crazyloong.cat.rishang.dto.GoodsRsp>>
    * @Author:
    * @Date: 2022/4/11 20:41
    * @Description:
    */
    RiReturnRsp<List<GoodsRsp>> searchGoods(String token,String key);

    /**
    * 功能描述：校验token 是否过期
    * @Param:
    * @Return: java.lang.Boolean
    * @Author:
    * @Date: 2022/4/11 21:05
    * @Description:
    */
    Boolean checkToken(String token);

    /**
     * 功能描述： 海南日上校验错误信息
     * @Param:
     * @param riReturnRsp
     * @Return: java.lang.Boolean
     * @Author:
     * @Date: 2022/3/19 16:29
     * @Description:
     */
    Boolean checkError(RiReturnRsp<?> riReturnRsp);
}
