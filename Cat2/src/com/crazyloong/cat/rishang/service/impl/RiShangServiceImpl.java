package com.crazyloong.cat.rishang.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.crazyloong.cat.rishang.constant.PlaceOrderType;
import com.crazyloong.cat.rishang.constant.RiShangURL;
import com.crazyloong.cat.rishang.dto.*;
import com.crazyloong.cat.execption.RiBizExecption;
import com.crazyloong.cat.pojo.GetBody;
import com.crazyloong.cat.pojo.PostBody;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderAddress;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderConvolutionCode;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPlaced;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderAddressService;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderConvolutionCodeService;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderPlacedService;
import com.crazyloong.cat.rishang.service.RiShangService;
import com.crazyloong.cat.util.HttpUtil;
import org.apache.http.HttpEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RiShangServiceImpl implements RiShangService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // 日上app 地址
    //@Value("${rishang.host}")
    private static String RISHANG_HOST = "srmemberapp2.srgow.com";
    @Autowired
    private RiOrderConvolutionCodeService riOrderConvolutionCodeService;
    @Autowired
    private RiOrderAddressService addressService;
    @Autowired
    private RiOrderPlacedService riOrderPlacedService;
    @Autowired
    private HttpUtil httpUtil;


    /**
     * 添加购物车  制定商品编号 添加数量 添加用户
     * @param abiId
     * @param num
     */
    @Override
    public boolean addCart(int abiId,int num,String pwd) {
        PostBody<Map<String,Object>> postBody = new PostBody();
        postBody.setAuthorization(pwd);
        postBody.setAPI(RiShangURL.RISHANG_ADDCART.code);
        postBody.setHost(RISHANG_HOST);
        Map<String,Object> paramters = new HashMap<>();
        paramters.put("abiid",abiId);
        paramters.put("num",num);
        postBody.setParamters(paramters);
        try {
            String entity = httpUtil.doPost(postBody);
            if (entity != null) {
                return true;
            }
        } catch (Exception e){
            logger.error("生成订单处理异常:",e);
            throw new RiBizExecption("生成订单处理异常 失败",e);
        }
        return false;
    }

    /**
     * @Description: 生成订单
     * @Author: YPLI
     * @Date: 2022/1/8 0008 22:00
     * [req]
     * com.crazyloong.cat.rishang.dto.RiReturnRsp<com.crazyloong.cat.rishang.dto.CreateOrderRsp>
     **/
    @Override
    public CreateOrderRsp createOrder(String token,CreateOrderReq req) {
        PostBody<CreateOrderReq> postBody = new PostBody<>();
        postBody.setAuthorization(token);
        postBody.setAPI(RiShangURL.RISHANG_CREATE_ORDER.code);
        postBody.setHost(RISHANG_HOST);
        postBody.setParamters(req);
        try {
            String entityStr = httpUtil.doPost(postBody);
            if (entityStr != null) {
                RiReturnRsp<CreateOrderRsp> createOrderRsp = JSONObject.parseObject(entityStr,new TypeReference<RiReturnRsp<CreateOrderRsp>>(){});
                CheckFail(createOrderRsp);
                return createOrderRsp.getData();
            }
        } catch (Exception e){
            logger.error("生成订单处理异常:",e);
            throw new RiBizExecption("生成订单处理异常 失败",e);
        }
        return null;
    }

    /**
     *
     * @Description: 获取优惠券相关信息
     * @Author: YPLI
     * @Date:
     *
     **/
    @Override
    public VipCodeRsp getVipCode(String token,String code){
        GetBody getBody = new GetBody();
        getBody.setAuthorization(token);
        getBody.setAPI(RiShangURL.RISHANG_GETVIPCOUPONV2.code);
        getBody.setHost(RISHANG_HOST);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("code",code);
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody);
            if (entityStr != null) {
                RiReturnRsp<VipCodeRsp> vipcode =  JSONObject.parseObject(entityStr,new TypeReference<RiReturnRsp<VipCodeRsp>>(){});
                return vipcode.getData();
            }
        } catch (Exception e){
            logger.error("获取优惠券信息 失败",e);
            throw new RiBizExecption("获取优惠券信息 失败",e);
        }
        return null;
    }

    /**
     * 获取当前登录用户的优惠券
     * @return
     */
    public VipCodeRsp getVipCodeMyself(String token,String preferentialSum){
        GetBody getBody = new GetBody();
        getBody.setAuthorization(token);
        getBody.setAPI(RiShangURL.RISHANG_GETVIPCOD.code);
        getBody.setHost(RISHANG_HOST);
        try {
            String entityStr = httpUtil.doGet(getBody);
            if (entityStr != null) {
                RiReturnRsp<List<VipCodeRsp>> vipcode =  JSONObject.parseObject(entityStr,new TypeReference<RiReturnRsp<List<VipCodeRsp>>>(){});
                List<VipCodeRsp> vipCodeRspList = vipcode.getData();
                if (vipCodeRspList != null) {
                    for (int i = 0; i < vipCodeRspList.size(); i++) {
                        if (vipCodeRspList.get(i).getTitle().contains(preferentialSum)) {
                            return vipCodeRspList.get(i);
                        }
                    }
                }
            }
        } catch (Exception e){
            logger.error("获取优惠券信息 失败",e);
            throw new RiBizExecption("获取优惠券信息 失败",e);
        }
        return null;
    }

    /**
     *
     * @Description: 获取订单信息
     * @Author: YPLI
     * @Date:
     *
     **/
    @Override
    public PlacedOrderRsp getPlacedOrder(String token,String wishid){
        GetBody getBody = new GetBody();
        getBody.setAuthorization(token);
        getBody.setAPI(RiShangURL.RISHANG_MALLNEW.code+wishid);
        getBody.setHost(RISHANG_HOST);
        try {
            String entityStr = httpUtil.doGet(getBody);
            if (entityStr != null) {
                RiReturnRsp<PlacedOrderRsp> placedOrderReturn =  JSONObject.parseObject(entityStr,new TypeReference<RiReturnRsp<PlacedOrderRsp>>(){});
                return placedOrderReturn.getData();
            }
        } catch (Exception e){
            logger.error("获取订单信息 失败",e);
            throw new RiBizExecption("获取订单信息 失败",e);
        }
        return null;
    }

    @Override
    public OrderRsp getOrder(Integer orderId) {
        return null;
    }

    /**
     * @Description: 提交订单
     * @Author: YPLI
     * @Date: 2022/1/16 0016 14:21
     * [token, req]
     * java.lang.Integer
     **/
    @Override
    public Integer submitOrder(String token, SubmitOrderReq req) {
        PostBody<SubmitOrderReq> postBody = new PostBody();
        postBody.setAuthorization(token);
        postBody.setAPI(RiShangURL.RISHANG_SUBMITORDER.code);
        postBody.setHost(RISHANG_HOST);
        postBody.setParamters(req);
        try {
            String entityStr = httpUtil.doPost(postBody);
            if (entityStr != null) {
                RiReturnRsp<Integer> submiReturn = JSONObject.parseObject(entityStr,RiReturnRsp.class);
                CheckFail(submiReturn);
                return submiReturn.getData();
            }
        } catch (Exception e){
            logger.error("提交订单 失败",e);
            throw new RiBizExecption("提交订单 失败",e);
        }
        return null;
    }

    /**
     * @Description: 获取我的订单信息 0-未提交 1-已提交 2-已完成
     * @Author: YPLI
     * @Date: 2022/1/8 0008 22:37
     * [token, state]
     * com.crazyloong.cat.rishang.dto.RiReturnRsp<com.crazyloong.cat.rishang.dto.WishRsp>
     **/
    @Override
    public RiReturnRsp<List<Object>> getWishs(String token, Integer state) {
        GetBody getBody = new GetBody();
        getBody.setAuthorization(token);
        getBody.setAPI(RiShangURL.RISHANG_WISHS.code);
        getBody.setHost(RISHANG_HOST);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("state",state);
        getBody.setParamters(paramters);
        HttpEntity entity;
        try {
            String entityStr = httpUtil.doGet(getBody);
            if (entityStr != null) {
                RiReturnRsp<List<Object>> wishRsp = JSONObject.parseObject(entityStr,RiReturnRsp.class);
                CheckFail(wishRsp);
                return wishRsp;
            }
        } catch (Exception e){
            logger.error("获取我的订单信息 失败",e);
            throw new RiBizExecption("获取我的订单信息 失败",e);
        }
        return null;
    }

    /**
     * @Description: 日上app登陆
     * @Author: YPLI
     * @Date: 2022/1/16 0016 14:20
     * [user, pwd]
     * java.lang.String
     **/
    @Override
    public String login(String user,String pwd){
        PostBody postBody = new PostBody();
        postBody.setAuthorization(pwd);
        postBody.setAPI(RiShangURL.RISHANG_loginv3.code);
        postBody.setHost(RISHANG_HOST);
        Map<String,Object> paramters = new HashMap<String,Object>();
        paramters.put("tel",user);
        paramters.put("pwd", "E10ADC3949BA59ABBE56E057F20F883E");
        paramters.put("code","");
        paramters.put("devcode","");
        paramters.put("devmodel","SM-N976N");
        paramters.put("devsys","Android_7.1.2_zh-CN");
        paramters.put("clientid","");
        postBody.setParamters(paramters);
        HttpEntity entity;
        String tokenStr = "";
        try {
            String entityStr = httpUtil.doPost(postBody);
            if (entityStr != null) {
                JSONObject jb = JSONObject.parseObject(entityStr);
                JSONObject token = (JSONObject)((JSONObject)jb.get("data")).get("token");
                tokenStr = (String)token.get("token");
            }
        } catch (Exception e){
            logger.error("登陆失败",e);
            throw new RiBizExecption("登陆失败",e);
        }
        return tokenStr;
    }

    /**
     * @Description: 校验返回结果
     * @Author: YPLI
     * @Date: 2022/1/16 0016 14:20
     * [riReturnRsp]
     * void
     **/
    private void CheckFail(RiReturnRsp riReturnRsp) {
        if (riReturnRsp == null) {
            throw new RiBizExecption("日上接口返回为空！");
        }
        if (!riReturnRsp.getSuccess()) {
            throw new RiBizExecption("日上接口返回失败:"+riReturnRsp.getError());
        }
    }

    /**
    * 功能描述：
    * @Param: 异步下订单
     * @param riOrderReq
    * @Return: void
    * @Author:
    * @Date: 2022/3/13 13:58
    * @Description:
    */
    @Override
    @Async("taskExecutor")
    public void placeOrderByCode(RiOrderReq riOrderReq) throws InterruptedException {
        RiOrderAddress address = addressService.getById(riOrderReq.getAddressId());
        SubmitOrderReq submitOrderReq = new SubmitOrderReq();
        submitOrderReq.setName(address.getUserName());
        submitOrderReq.setAddress(address.getAddress());
        submitOrderReq.setTel(address.getUserPhone());
        submitOrderReq.setIssue("0");
        submitOrderReq.setType(2);
        submitOrderReq.setRightscode("-999");
        WishPageRsp wishPageRsp = riOrderReq.getWishPageRsp();
        // 根据订单数量 循环下单
        for (int i = 0; i < riOrderReq.getOrderNum(); i++) {
            List<Integer> abiids = new ArrayList<>();
            // 加入购物车
            List<String> abiidList = Arrays.asList(wishPageRsp.getAbiid().split(","));
            List<String> numList = Arrays.asList(wishPageRsp.getNum().split(","));
            for (int j = 0; j < abiidList.size(); j++) {
                abiids.add(Integer.parseInt(abiidList.get(j)));
                this.addCart(Integer.parseInt(abiidList.get(j)), Integer.parseInt(numList.get(j)), riOrderReq.getToken());
            }
            VipCodeRsp vipCode = null;
            if (PlaceOrderType.publicCode.code.equals(riOrderReq.getType())) {
                // 获取优惠券信息
                RiOrderConvolutionCode selectCase = new RiOrderConvolutionCode();
                selectCase.setIsInuse(0);
                selectCase.setIsUsed(0);
                List<RiOrderConvolutionCode> codeList = riOrderConvolutionCodeService.listCodes(selectCase);
                for (RiOrderConvolutionCode code : codeList) {
                    vipCode = this.getVipCode(riOrderReq.getToken(), code.getCode());
                    // 如果优惠码无效则更新状态继续查询
                    code.setIsInuse(1);
                    if (vipCode == null) {
                        riOrderConvolutionCodeService.saveOrUpdate(code);
                    } else {
                        code.setIsUsed(1);
                        riOrderConvolutionCodeService.saveOrUpdate(code);
                        break;
                    }
                }
            } else {
                // 如果下单方式为用户优惠券则获取用户自己的优惠券
                vipCode = this.getVipCodeMyself(riOrderReq.getToken(), riOrderReq.getPreferentialSum());
            }

            if (vipCode == null) {
                throw new RuntimeException("无可用优惠券！");
            }
            // 生成订单
            CreateOrderReq createOrderReq = new CreateOrderReq();
            createOrderReq.setType(2);
            createOrderReq.setAbiids(abiids);
            createOrderReq.setCouponscode(vipCode.getCode());
            createOrderReq.setRightscode("-999");
            CreateOrderRsp createOrderRsp = this.createOrder(riOrderReq.getToken(), createOrderReq);
            if (createOrderRsp == null) {
                throw new RuntimeException("生成订单失败！");
            }
            // 提交订单
            submitOrderReq.setCouponscode(vipCode.getCode());
            submitOrderReq.setAbiids(abiids);
            submitOrderReq.setWishid(createOrderRsp.getWishid());
            Integer riReturnRsp = this.submitOrder(riOrderReq.getToken(), submitOrderReq);
            PlacedOrderRsp placedOrderRsp = this.getPlacedOrder(riOrderReq.getToken(), String.valueOf(riReturnRsp));
            RiOrderPlaced riOrderPlaced = new RiOrderPlaced();
            riOrderPlaced.setOrderCode(placedOrderRsp.getSjcode());
            riOrderPlaced.setOrderUser(riOrderReq.getPhone());
            riOrderPlaced.setAddressName(placedOrderRsp.getContacts());
            riOrderPlaced.setAddressPhone(placedOrderRsp.getTel());
            riOrderPlaced.setAddress(placedOrderRsp.getAddress());
            riOrderPlaced.setGoodsName(wishPageRsp.getAbname());
            riOrderPlaced.setGoodsNum(wishPageRsp.getNum());
            riOrderPlaced.setGoodsPrice(String.valueOf(placedOrderRsp.getPrices()));
            riOrderPlaced.setGoodsOprice(String.valueOf(placedOrderRsp.getOprices()));
            riOrderPlacedService.save(riOrderPlaced);
            Thread.sleep(1000);
        }
    }
}
