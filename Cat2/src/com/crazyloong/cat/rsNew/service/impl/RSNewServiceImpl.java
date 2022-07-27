package com.crazyloong.cat.rsNew.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.crazyloong.cat.execption.RiBizExecption;
import com.crazyloong.cat.pojo.GetBody;
import com.crazyloong.cat.pojo.PostBody;
import com.crazyloong.cat.rsNew.constant.OrderStatus;
import com.crazyloong.cat.rsNew.constant.RSNewURL;
import com.crazyloong.cat.rsNew.dto.*;
import com.crazyloong.cat.rsNew.service.RSNewService;
import com.crazyloong.cat.rshainan.constant.RishangHNEnum;
import com.crazyloong.cat.util.CacheUtil;
import com.crazyloong.cat.util.HttpUtil;
import com.crazyloong.cat.util.RSAUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.PublicKey;
import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/14 22:20
 * @Description :中免日上服务类
 */
@Service
public class RSNewServiceImpl implements RSNewService {

    // 中免日上 地址
    private static String RSN_HOST = "api.cdfsunrise.com";
    // 中免日上 地址
    private static String CDN_HOST = "data-library-cdn.cdfsunrise.com";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private CacheUtil cacheUtil;


    /**
     * 功能描述：中免日上设备登录 登录结果放入缓存中
     * @Param:
     * @Return: com.crazyloong.cat.rsNew.dto.DeviceLoginRsp
     * @Author:
     * @Date: 2022/6/14 22:31
     * @Description:
     */
    @Override
    public void deviceLogin() {
        DeviceLoginReq deviceLoginReq = new DeviceLoginReq();
        PostBody<DeviceLoginReq> postBody = new PostBody();
        postBody.setAPI(RSNewURL.RSN_deviceLogin.code);
        postBody.setHost(RSN_HOST);
        postBody.setParamters(deviceLoginReq);
        try {
            String entityStr = httpUtil.doPost(postBody, RishangHNEnum.GetType.AUTHORIZATION);
            if (entityStr != null) {
                logger.info("中免日上设备登录"+entityStr);
                RSNewReturnRsp<DeviceLoginRsp> deviceLoginRsp = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                checkError(deviceLoginRsp,"");
                cacheUtil.put("deviceToken",deviceLoginRsp.getData().getAccessToken());
            }
        } catch (Exception e){
            logger.info("中免日上设备登录 失败",e);
            throw new RiBizExecption("中免日上设备登录 失败"+e.getMessage(),e);
        }
    }

    /**
     * 功能描述：中免日上登录 登录结果放入缓存中
     * @Param:  phone
     * @Return: com.crazyloong.cat.rsNew.dto.DeviceLoginRsp
     * @Author:
     * @Date: 2022/6/14 22:31
     * @Description:
     */
    @Override
    public RSNewUserInfo login(String phone) {
        DeviceLoginReq deviceLoginReq = new DeviceLoginReq();
        deviceLoginReq.setUserName(phone);
        PostBody<DeviceLoginReq> postBody = new PostBody();
        String accessToken = cacheUtil.get("deviceToken",String.class);
        if (StringUtils.isEmpty(accessToken)) {
            deviceLogin();
            accessToken = cacheUtil.get("deviceToken",String.class);
        }
        postBody.setAuthorization(accessToken);
        postBody.setAPI(RSNewURL.RSN_userPasswordLogin.code);
        postBody.setHost(RSN_HOST);
        postBody.setParamters(deviceLoginReq);
        try {
            String entityStr = httpUtil.doPost(postBody, RishangHNEnum.PostType.RS_NEW);
            if (entityStr != null) {
                logger.info("中免日上登录"+entityStr);
                RSNewReturnRsp<LoginRsp> loginRsp = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                checkError(loginRsp,"");
                RSNewUserInfo rsNewUserInfo = new RSNewUserInfo();
                rsNewUserInfo.setAccessToken(loginRsp.getData().getTokenInfo().getAccessToken());
                rsNewUserInfo.setUserId(loginRsp.getData().getUserId());
                for (LoginRsp.MemberListDTO memberListDTO : loginRsp.getData().getMemberList()) {
                    if ("日上会员".equals(memberListDTO.getMemberType())) {
                        rsNewUserInfo.setMemberCode(memberListDTO.getMemberCode());
                        break;
                    }
                }
                return rsNewUserInfo;
            }
        } catch (Exception e){
            logger.info("中免日上登录 失败",e);
            try {
                Thread.sleep(8000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            throw new RiBizExecption("中免日上登录 失败"+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 功能描述：获取优惠券网址
     * @param
     * @Return: com.crazyloong.cat.rsNew.dto.HintRsp
     * @Author:
     * @Date: 2022/6/17 0:04
     * @Description:
     */
    public HintRsp hint() {
        PostBody<DeviceLoginReq> postBody = new PostBody();
        RSNewUserInfo userInfo = cacheUtil.getRSNewToken("17600133533");
        postBody.setAuthorization(userInfo.getAccessToken());
        postBody.setAPI(RSNewURL.RSN_hint.code);
        postBody.setHost(RSN_HOST);
        try {
            String entityStr = httpUtil.doPost(postBody, RishangHNEnum.PostType.RS_NEW);
            if (entityStr != null) {
                logger.info("中免日上获取优惠券网址"+entityStr);
                HintRsp hintRsp = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return hintRsp;
            }
        } catch (Exception e){
            logger.info("中免日上获取优惠券网址 失败",e);
            throw new RiBizExecption("中免日上获取优惠券网址 失败"+e.getMessage(),e);
        }
        return null;
    }


    /**
     * 功能描述：获取优惠券数据
     * @param
     * @Return: com.crazyloong.cat.rsNew.dto.HintRsp
     * @Author:
     * @Date: 2022/6/17 0:04
     * @Description:
     */
    @Override
    public CouponsJson getContent(String contentId) {
        GetBody getBody = new GetBody();
        getBody.setAPI(RSNewURL.CDN_content.code+contentId+".json");
        getBody.setHost(CDN_HOST);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.NONE);
            if (entityStr != null) {
                logger.info("中免日上获取优惠券"+entityStr);
                CouponsJson couponsJson =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return couponsJson;
            }
        } catch (Exception e){
            logger.info("中免日上获取优惠券 失败",e);
            throw new RiBizExecption("中免日上获取优惠券 失败",e);
        }
        return null;
    }

    /**
     * 功能描述：领取优惠券
     * @param getUserCouponReq
     * @Return: com.crazyloong.cat.rsNew.dto.GetUserCouponRsp
     * @Author:
     * @Date: 2022/6/17 0:26
     * @Description:
     */
    public void getUserCoupon(GetUserCouponReq getUserCouponReq,String token) {
        PostBody<GetUserCouponReq> postBody = new PostBody();
        postBody.setAuthorization(token);
        postBody.setAPI(RSNewURL.RSN_getUserCoupon.code);
        postBody.setHost(RSN_HOST);
        postBody.setParamters(getUserCouponReq);
        try {
            String entityStr = httpUtil.doRSNPost(postBody);
            if (entityStr != null) {
                logger.info("领取优惠券"+entityStr);
            }
        } catch (Exception e){
            logger.info("领取优惠券 失败",e);
            throw new RiBizExecption("领取优惠券 失败"+e.getMessage(),e);
        }
    }



    /**
     * 功能描述： 中免日上校验错误信息
     *   @param rsNewReturnRsp
     * @Return: java.lang.Boolean
     * @Author:
     * @Date: 2022/6/14 23:02
     * @Description:
     */
    @Override
    public Boolean checkError(RSNewReturnRsp<?> rsNewReturnRsp,String message) {
        if (!rsNewReturnRsp.getSuccess()) {
            if (StringUtils.isNotEmpty(message)) {
                throw new RiBizExecption(message);
            }
            if (rsNewReturnRsp.getMessage() != null) {
                throw new RiBizExecption(rsNewReturnRsp.getMessage());
            } else {
                throw new RiBizExecption(rsNewReturnRsp.getMsg());
            }
        }
        return true;
    }

    /**
     * 功能描述： 获取订单列表
     * @Param: [orderRep]
     * @Return: com.crazyloong.cat.rsNew.dto.OrderListRsp
     * @Author:
     * @Date: 2022/6/17 20:31
     * @Description:
     */
    public OrderListRsp getOrderList(OrderRep orderRep) {
        PostBody<OrderListRep> postBody = new PostBody();
        OrderListRep orderListRep = new OrderListRep();
        BeanUtils.copyProperties(orderRep,orderListRep);
        postBody.setAuthorization(cacheUtil.getRSNewToken(orderRep.getPhone()).getAccessToken());
        postBody.setAPI(RSNewURL.RSN_orderList.code);
        postBody.setHost(RSN_HOST);
        postBody.setParamters(orderListRep);
        try {
            String entityStr = httpUtil.doPost(postBody, RishangHNEnum.PostType.RS_NEW);
            if (entityStr != null) {
                RSNewReturnRsp<OrderListRsp> orderListRsp = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                for (OrderListRsp.ListDTO listDTO:orderListRsp.getData().getList()) {
                    String goodsNames = "";
                    String quantitys = "";
                    for (OrderListRsp.ListDTO.GoodsListDTO goodsListDTO :listDTO.getGoodsList()) {
                        goodsNames = goodsNames + goodsListDTO.getGoodsName()+",";
                        quantitys = quantitys + goodsListDTO.getQuantity()+",";
                    }
                    listDTO.setGoodsNames(goodsNames);
                    listDTO.setQuantitys(quantitys);
                    OrderDetailRsp orderDetail = getOrderDetail(listDTO.getOrderId(), orderRep.getPhone());
                    listDTO.setAddress(orderDetail.getAddress());
                    listDTO.setUserName(orderDetail.getUserName());
                    listDTO.setMobile(orderDetail.getMobile());
                    listDTO.setMerchantNum(orderDetail.getDetailList().get(0).getMerchantNum());
                    listDTO.setOrderTime(orderDetail.getPayTime());
                    listDTO.setStatusName(OrderStatus.getDescByCode(listDTO.getStatus()));
                    listDTO.setPrice(Double.valueOf(listDTO.getRealPrice()));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return orderListRsp.getData();
            }
        } catch (Exception e){
            logger.info("获取订单列表 失败",e);
            throw new RiBizExecption("获取订单列表 失败"+e.getMessage(),e);
        }
        return null;
    }


    /**
     * 功能描述：根据订单编号获取订单详情
     * @Param: [orderId]
     * @Return: com.crazyloong.cat.rsNew.dto.OrderDetail
     * @Author:
     * @Date: 2022/6/17 20:33
     * @Description:
     */
    public OrderDetailRsp getOrderDetail(String orderId, String phone) {
        PostBody<OrderDetailReq> postBody = new PostBody();
        OrderDetailReq orderDetailReq = new OrderDetailReq();
        orderDetailReq.setOrderId(orderId);
        postBody.setAuthorization(cacheUtil.getRSNewToken(phone).getAccessToken());
        postBody.setAPI(RSNewURL.RSN_orderDetail.code);
        postBody.setHost(RSN_HOST);
        postBody.setParamters(orderDetailReq);
        try {
            String entityStr = httpUtil.doPost(postBody, RishangHNEnum.PostType.RS_NEW);
            if (entityStr != null) {
                RSNewReturnRsp<OrderDetailRsp> orderDetailRsp = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return orderDetailRsp.getData();
            }
        } catch (Exception e){
            logger.info("获取订单详情 失败",e);
            throw new RiBizExecption("获取订单详情 失败"+e.getMessage(),e);
        }
        return null;
    }





    /**
     * 功能描述： 获取用户优惠券
     * @Param: [phone]
     * @Return: com.crazyloong.cat.rsNew.dto.MyCouponRsp
     * @Author:
     * @Date: 2022/6/18 12:22
     * @Description:
     */
    public MyCouponRsp getMyCoupon(String phone) {
        PostBody<MyCouponRep> postBody = new PostBody();
        RSNewUserInfo rsNewUserInfo = cacheUtil.getRSNewToken(phone);
        MyCouponRep myCouponRep = new MyCouponRep();
        myCouponRep.setCouponStatus("1");
        myCouponRep.setMemberCode(rsNewUserInfo.getMemberCode());
        myCouponRep.setPageNumber(1);
        myCouponRep.setPageSize(100);
        postBody.setAuthorization(rsNewUserInfo.getAccessToken());
        postBody.setAPI(RSNewURL.RSN_getMyCoupon.code);
        postBody.setHost(RSN_HOST);
        postBody.setUserID(cacheUtil.getRSNewToken(phone).getUserId());
        postBody.setMobile(phone);
        postBody.setParamters(myCouponRep);
        try {
            String entityStr = httpUtil.doRSNPost(postBody);
            if (entityStr != null) {
                logger.info("中免日上用户优惠券"+entityStr);
                MyCouponRsp myCouponRsp = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                if (myCouponRsp != null && myCouponRsp.getMyCouponList() != null ) {
                    for(MyCouponRsp.MyCouponListDTO myCouponListDTO: myCouponRsp.getMyCouponList()) {
                        myCouponListDTO.setPhone(phone);
                    }
                }
                return myCouponRsp;
            }
        } catch (Exception e){
            logger.info("中免日上用户优惠券 失败",e);
            throw new RiBizExecption("中免日上用户优惠券 失败"+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 功能描述： 获取公钥
     * @Param: [phone]
     * @Return: com.crazyloong.cat.rsNew.dto.PublicKeyRsp
     * @Author:
     * @Date: 2022/6/18 18:28
     * @Description:
     */
    public PublicKeyRsp getPublicKey(String phone) {
        PostBody<PublicKeyReq> postBody = new PostBody();
        PublicKeyReq publicKeyReq = new PublicKeyReq();
        publicKeyReq.setSign("md5sign");
        publicKeyReq.setTimeStamp(System.currentTimeMillis());
        postBody.setAuthorization(cacheUtil.getRSNewToken(phone).getAccessToken());
        postBody.setAPI(RSNewURL.RSN_getPublicKey.code);
        postBody.setHost(RSN_HOST);
        postBody.setParamters(publicKeyReq);
        try {
            String entityStr = httpUtil.doPost(postBody, RishangHNEnum.PostType.RS_NEW);
            if (entityStr != null) {
                logger.info("中免日上获取公钥"+entityStr);
                RSNewReturnRsp<PublicKeyRsp> publicKeyRsp = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return publicKeyRsp.getData();
            }
        } catch (Exception e){
            logger.info("中免日上获取公钥 失败",e);
            throw new RiBizExecption("中免日上获取公钥 失败"+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 功能描述： 修改密码
     * @Param: [phone, newPassword, oldPassword]
     * @Return: com.crazyloong.cat.rsNew.dto.BindKeyRsp
     * @Author:
     * @Date: 2022/6/18 18:31
     * @Description:
     */
    public BindKeyRsp changePassword(ChangePasswordRep changePasswordRep) {
        /*PublicKeyRsp publicKey = getPublicKey(changePasswordRep.getPhone());*/
        PublicKey publicKey1 = RSAUtil.getPublicKey("MCkCIgbOJPW4CKmohJqM2oafIgFIUunG4ozKX8rw4C+A8ZDdGOUCAwEAAQ==");
        try {
            changePasswordRep.setOriginalPassword(RSAUtil.encrypt(changePasswordRep.getOriginalPassword(),publicKey1));
            changePasswordRep.setNewPassword(RSAUtil.encrypt(changePasswordRep.getNewPassword(),publicKey1));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RiBizExecption("加密异常",e);
        }
        PostBody<ChangePasswordRep> postBody = new PostBody();
        postBody.setAuthorization(cacheUtil.getRSNewToken(changePasswordRep.getPhone()).getAccessToken());
        postBody.setAPI(RSNewURL.RSN_changePassword.code);
        postBody.setHost(RSN_HOST);
        postBody.setParamters(changePasswordRep);
        try {
            String entityStr = httpUtil.doPost(postBody, RishangHNEnum.PostType.RS_NEW);
            if (entityStr != null) {
                logger.info("中免日上修改密码"+entityStr);
                RSNewReturnRsp<BindKeyRsp> publicKeyRsp = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return publicKeyRsp.getData();
            }
        } catch (Exception e){
            logger.info("中免日上修改密码 失败",e);
            throw new RiBizExecption("中免日上修改密码 失败"+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 功能描述：根据商品ID获取商品详情
     * @Param: [goodsId]
     * @Return: com.crazyloong.cat.rsNew.dto.SearchGoodsRsp
     * @Author:
     * @Date: 2022/6/19 21:04
     * @Description:
     */
    public SearchGoodsRsp searchGoods(String goodsId,String phone) {
        PostBody<SearchGoodsRep> postBody = new PostBody();
        postBody.setAuthorization(cacheUtil.getRSNewToken(phone).getAccessToken());
        postBody.setAPI(RSNewURL.RSN_searchItem.code);
        postBody.setHost(RSN_HOST);
        SearchGoodsRep searchGoodsRep = new SearchGoodsRep();
        searchGoodsRep.setGoodsID(goodsId);
        postBody.setParamters(searchGoodsRep);
        try {
            String entityStr = httpUtil.doPost(postBody, RishangHNEnum.PostType.RS_NEW);
            if (entityStr != null) {
                logger.info("中免日上获取商品详情"+entityStr);
                SearchGoodsRsp searchGoodsRsp = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return searchGoodsRsp;
            }
        } catch (Exception e){
            logger.info("获取商品详情 失败",e);
            throw new RiBizExecption("获取商品详情 失败"+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 功能描述： 确认订单
     * @Param: [confirmOrderRep, phone]
     * @Return: com.crazyloong.cat.rsNew.dto.ConfirmOrderRep
     * @Author:
     * @Date: 2022/6/19 21:08
     * @Description:
     */
    public ConfirmOrderRsp comfirmOrder(ConfirmOrderRep confirmOrderRep,String phone) {
        PostBody<ConfirmOrderRep> postBody = new PostBody();
        postBody.setAuthorization(cacheUtil.getRSNewToken(phone).getAccessToken());
        postBody.setAPI(RSNewURL.RSN_confirmOrder.code);
        postBody.setHost(RSN_HOST);
        postBody.setParamters(confirmOrderRep);
        postBody.setUserID(cacheUtil.getRSNewToken(phone).getUserId());
        postBody.setMobile(phone);
        try {
            String entityStr = httpUtil.doRSNPost(postBody);
            if (entityStr != null) {
                logger.info("中免日上确认订单"+entityStr);
                ConfirmOrderRsp confirmOrderRsp = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return confirmOrderRsp;
            }
        } catch (Exception e){
            logger.info("中免日上确认订单 失败",e);
            throw new RiBizExecption("中免日上确认订单 失败"+e.getMessage(),e);
        }
        return null;
    }


    /**
     * 功能描述： 提交订单
     * @Param: [createOrderRep, phone]
     * @Return: com.crazyloong.cat.rsNew.dto.CreateOrderRep
     * @Author:
     * @Date: 2022/6/19 21:11
     * @Description:
     */
    public CreatOrderRsp createOrder(CreateOrderRep createOrderRep,String phone) {
        PostBody<CreateOrderRep> postBody = new PostBody();
        postBody.setAuthorization(cacheUtil.getRSNewToken(phone).getAccessToken());
        postBody.setAPI(RSNewURL.RSN_createOrder.code);
        postBody.setHost(RSN_HOST);
        postBody.setParamters(createOrderRep);
        postBody.setUserID(cacheUtil.getRSNewToken(phone).getUserId());
        postBody.setMobile(phone);
        try {
            String entityStr = httpUtil.doRSNPost(postBody);
            if (entityStr != null) {
                logger.info("中免日上提交订单"+entityStr);
                CreatOrderRsp creatOrderRsp = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return creatOrderRsp;
            }
        } catch (Exception e){
            logger.info("中免日上提交订单 失败",e);
            throw new RiBizExecption("中免日上提交订单 失败"+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 功能描述：更新地址
     * @Param: [updateAddressRep, phone]
     * @Return: java.lang.String
     * @Author:
     * @Date: 2022/6/19 21:12
     * @Description:
     */
    public String updateAddress(UpdateAddressRep updateAddressRep,String phone) {
        List<AddressRsp> addressRsps = this.addressBookList(phone);
        for (AddressRsp addressRsp: addressRsps) {
            if (updateAddressRep.getAddress().equals(addressRsp.getAddress())
                    && updateAddressRep.getPhone().equals(addressRsp.getPhone())
                    && updateAddressRep.getLinkName().equals(addressRsp.getLinkName())) {
                if (!addressRsp.getIsDefault()) {
                    updateAddressRep.setAid(addressRsp.getAid());
                    updateAddressOnly(updateAddressRep,phone);
                }
                return addressRsp.getAid();
            }

        }
        return updateAddressOnly(updateAddressRep,phone);
    }


    /**
     * 功能描述：更新地址
     * @Param: [updateAddressRep, phone]
     * @Return: java.lang.String
     * @Author:
     * @Date: 2022/6/19 21:12
     * @Description:
     */
    public String updateAddressOnly(UpdateAddressRep updateAddressRep,String phone) {
        List<AddressRsp> addressRsps = this.addressBookList(phone);
        for (AddressRsp addressRsp: addressRsps) {
            if (updateAddressRep.getAddress().equals(addressRsp.getAddress())
                    && updateAddressRep.getPhone().equals(addressRsp.getPhone())
                    && updateAddressRep.getLinkName().equals(addressRsp.getLinkName())) {
                if (!addressRsp.getIsDefault()) {

                }
               return addressRsp.getAid();
            }

        }

        PostBody<UpdateAddressRep> postBody = new PostBody();
        postBody.setAuthorization(cacheUtil.getRSNewToken(phone).getAccessToken());
        postBody.setAPI(RSNewURL.RSN_updateAddress.code);
        postBody.setHost(RSN_HOST);
        postBody.setParamters(updateAddressRep);
        try {
            String entityStr = httpUtil.doPost(postBody, RishangHNEnum.PostType.RS_NEW);
            if (entityStr != null) {
                logger.info("中免日上更新地址"+entityStr);
                RSNewReturnRsp<String> rsNewReturnRsp = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return rsNewReturnRsp.getData();
            }
        } catch (Exception e){
            logger.info("中免日上更新地址 失败",e);
            throw new RiBizExecption("中免日上更新地址 失败"+e.getMessage(),e);
        }
        return null;
    }

    /**
     * 功能描述：获取用户地址
     * @Param: [updateAddressRep, phone]
     * @Return: java.lang.String
     * @Author:
     * @Date: 2022/6/19 21:12
     * @Description:
     */
    public List<AddressRsp> addressBookList(String phone) {
        PostBody<AddressReq> postBody = new PostBody();
        postBody.setAuthorization(cacheUtil.getRSNewToken(phone).getAccessToken());
        postBody.setAPI(RSNewURL.RSN_addressBook.code);
        postBody.setHost(RSN_HOST);
        postBody.setParamters(new AddressReq());
        try {
            String entityStr = httpUtil.doPost(postBody, RishangHNEnum.PostType.RS_NEW);
            if (entityStr != null) {
                logger.info("获取用户地址"+entityStr);
                RSNewReturnRsp<List<AddressRsp>> addressList = JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return addressList.getData();
            }
        } catch (Exception e){
            logger.info("获取用户地址 失败",e);
            throw new RiBizExecption("获取用户地址 失败"+e.getMessage(),e);
        }
        return null;
    }

    /**
    * 功能描述：添加购物车
    * @Param: [addCartReq]
    * @Return: void
    * @Author:
    * @Date: 2022/7/27 21:58
    * @Description:
    */
    public void addCart(AddCartReq addCartReq,String phone) {
        PostBody<AddCartReq> postBody = new PostBody();
        postBody.setAuthorization(cacheUtil.getRSNewToken(phone).getAccessToken());
        postBody.setAPI(RSNewURL.RSN_createOrder.code);
        postBody.setHost(RSN_HOST);
        postBody.setParamters(addCartReq);
        postBody.setUserID(cacheUtil.getRSNewToken(phone).getUserId());
        postBody.setMobile(phone);
        try {
            String entityStr = httpUtil.doRSNPost(postBody);
            if (entityStr != null) {
                logger.info("中免日上提交订单"+entityStr);
            }
        } catch (Exception e){
            logger.info("中免日上提交订单 失败",e);
            throw new RiBizExecption("中免日上提交订单 失败"+e.getMessage(),e);
        }
    }


}
