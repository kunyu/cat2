package com.crazyloong.cat.rsNew.service;

import com.crazyloong.cat.rsNew.dto.*;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/14 22:19
 * @Description : 中免日上服务类
 */
public interface RSNewService {

    /**
    * 功能描述：
    * @Param:  中免日上设备登录
    * @Return: com.crazyloong.cat.rsNew.dto.DeviceLoginRsp
    * @Author:
    * @Date: 2022/6/14 22:31
    * @Description:
    */
    void deviceLogin();

    /**
     * 功能描述：
     * @Param:  中免日上登录
     * @Return: com.crazyloong.cat.rsNew.dto.DeviceLoginRsp
     * @Author:
     * @Date: 2022/6/14 22:31
     * @Description:
     */
    RSNewUserInfo login(String phone);

    /**
     * 功能描述：获取优惠券网址
     * @param
     * @Return: com.crazyloong.cat.rsNew.dto.HintRsp
     * @Author:
     * @Date: 2022/6/17 0:04
     * @Description:
     */
    HintRsp hint();

    /**
     * 功能描述：获取优惠券数据
     * @param
     * @Return: com.crazyloong.cat.rsNew.dto.HintRsp
     * @Author:
     * @Date: 2022/6/17 0:04
     * @Description:
     */
    CouponsJson getContent(String contentId);

    /**
    * 功能描述：领取优惠券
    * @param getUserCouponReq
    * @Return: com.crazyloong.cat.rsNew.dto.GetUserCouponRsp
    * @Author:
    * @Date: 2022/6/17 0:26
    * @Description:
    */
    void getUserCoupon(GetUserCouponReq getUserCouponReq,String token);


    /**
    * 功能描述： 中免日上校验错误信息
     *   @param rsNewReturnRsp
    * @Return: java.lang.Boolean
    * @Author:
    * @Date: 2022/6/14 23:02
    * @Description:
    */
    Boolean checkError(RSNewReturnRsp<?> rsNewReturnRsp,String message);



    /**
    * 功能描述： 获取订单列表
    * @Param: [orderRep]
    * @Return: com.crazyloong.cat.rsNew.dto.OrderListRsp
    * @Author:
    * @Date: 2022/6/17 20:31
    * @Description:
    */
    OrderListRsp getOrderList(OrderRep orderRep);


    /**
    * 功能描述：根据订单编号获取订单详情
    * @Param: [orderId]
    * @Return: com.crazyloong.cat.rsNew.dto.OrderDetail
    * @Author:
    * @Date: 2022/6/17 20:33
    * @Description:
    */
    OrderDetailRsp getOrderDetail(String orderId, String phone);



    /**
    * 功能描述： 获取用户优惠券
    * @Param: [phone]
    * @Return: com.crazyloong.cat.rsNew.dto.MyCouponRsp
    * @Author:
    * @Date: 2022/6/18 12:22
    * @Description:
    */
    MyCouponRsp getMyCoupon(String phone);

    /**
    * 功能描述： 获取公钥
    * @Param: [phone]
    * @Return: com.crazyloong.cat.rsNew.dto.PublicKeyRsp
    * @Author:
    * @Date: 2022/6/18 18:28
    * @Description:
    */
    PublicKeyRsp getPublicKey(String phone);

    /**
    * 功能描述： 修改密码
    * @Param: [phone, newPassword, oldPassword]
    * @Return: com.crazyloong.cat.rsNew.dto.BindKeyRsp
    * @Author:
    * @Date: 2022/6/18 18:31
    * @Description:
    */
    BindKeyRsp changePassword(ChangePasswordRep changePasswordRep);

    /**
    * 功能描述：根据商品ID获取商品详情
    * @Param: [goodsId]
    * @Return: com.crazyloong.cat.rsNew.dto.SearchGoodsRsp
    * @Author:
    * @Date: 2022/6/19 21:04
    * @Description:
    */
    SearchGoodsRsp searchGoods(String goodsId,String phone);

    /**
    * 功能描述： 确认订单
    * @Param: [confirmOrderRep, phone]
    * @Return: com.crazyloong.cat.rsNew.dto.ConfirmOrderRep
    * @Author:
    * @Date: 2022/6/19 21:08
    * @Description:
    */
    ConfirmOrderRsp comfirmOrder(ConfirmOrderRep confirmOrderRep,String phone);


    /**
    * 功能描述： 提交订单
    * @Param: [createOrderRep, phone]
    * @Return: com.crazyloong.cat.rsNew.dto.CreateOrderRep
    * @Author:
    * @Date: 2022/6/19 21:11
    * @Description:
    */
    CreatOrderRsp createOrder(CreateOrderRep createOrderRep,String phone);


    /**
    * 功能描述：更新地址
    * @Param: [updateAddressRep, phone]
    * @Return: java.lang.String
    * @Author:
    * @Date: 2022/6/19 21:12
    * @Description:
    */
    String updateAddress(UpdateAddressRep updateAddressRep,String phone);

    /**
    * 功能描述： 获取用户地址
    * @Param: []
    * @Return: java.util.List<com.crazyloong.cat.rsNew.dto.AddressRsp>
    * @Author:
    * @Date: 2022/6/25 15:36
    * @Description:
    */
    List<AddressRsp> addressBookList(String phone);

    /**
     * 功能描述：添加购物车
     * @Param: [addCartReq]
     * @Return: void
     * @Author:
     * @Date: 2022/7/27 21:58
     * @Description:
     */
    void addCart(AddCartReq addCartReq,String phone);



}
