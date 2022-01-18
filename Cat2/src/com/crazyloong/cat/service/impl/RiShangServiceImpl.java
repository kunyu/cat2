package com.crazyloong.cat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crazyloong.cat.dto.*;
import com.crazyloong.cat.execption.RiBizExecption;
import com.crazyloong.cat.pojo.GetBody;
import com.crazyloong.cat.pojo.PostBody;
import com.crazyloong.cat.service.RiShangService;
import com.crazyloong.cat.util.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RiShangServiceImpl implements RiShangService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Environment environment;

    /**
     * 添加购物车  制定商品编号 添加数量 添加用户
     * @param abiId
     * @param num
     */
    @Override
    public boolean addCart(int abiId,int num,String pwd) {
        HttpUtil httpUtil = new HttpUtil();
        PostBody<Map<String,Object>> postBody = new PostBody();
        postBody.setAuthorization(pwd);
        postBody.setAPI(environment.getProperty("rishang.url.addCart"));
        postBody.setHost(environment.getProperty("rishang.host"));
        Map<String,Object> paramters = new HashMap<String,Object>();
        paramters.put("abiid",abiId);
        paramters.put("num",num);
        postBody.setParamters(paramters);
        HttpEntity entity;
        try {
            entity = httpUtil.doPost(postBody);
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
     * com.crazyloong.cat.dto.RiReturnRsp<com.crazyloong.cat.dto.CreateOrderRsp>
     **/
    @Override
    public CreateOrderRsp createOrder(String token,CreateOrderReq req) {
        HttpUtil httpUtil = new HttpUtil();
        PostBody<CreateOrderReq> postBody = new PostBody<>();
        postBody.setAuthorization(token);
        postBody.setAPI(environment.getProperty("rishang.url.createOrder"));
        postBody.setHost(environment.getProperty("rishang.host"));
        postBody.setParamters(req);
        HttpEntity entity;
        try {
            entity = httpUtil.doPost(postBody);
            if (entity != null) {
                String  entityStr= EntityUtils.toString(entity,"utf-8");
                logger.debug("entityStr:"+entityStr);
                RiReturnRsp<JSONObject> createOrderRsp = JSONObject.parseObject(entityStr,RiReturnRsp.class);
                CheckFail(createOrderRsp);
                return createOrderRsp.getData().toJavaObject(CreateOrderRsp.class);
            }
        } catch (Exception e){
            logger.error("生成订单处理异常:",e);
            throw new RiBizExecption("生成订单处理异常 失败",e);
        }
        return null;
    }

    @Override
    public boolean auth(String pwd) {
        HttpUtil httpUtil = new HttpUtil();
        PostBody postBody = new PostBody();
        postBody.setAuthorization(pwd);
        postBody.setAPI(environment.getProperty("rishang.url.auth"));
        postBody.setHost(environment.getProperty("rishang.host"));
        Map<String,Object> paramters = new HashMap<String,Object>();
        paramters.put("flightno","K1186");
        paramters.put("seat","");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        paramters.put("travellertime",sdf.format(new Date()));
        paramters.put("type",4);
        postBody.setParamters(paramters);
        HttpEntity entity;
        try {
            entity = httpUtil.doPost(postBody);
            if (entity != null) {
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
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
        HttpUtil httpUtil = new HttpUtil();
        GetBody getBody = new GetBody();
        getBody.setAuthorization(token);
        getBody.setAPI(environment.getProperty("rishang.url.getvipcouponv2"));
        getBody.setHost(environment.getProperty("rishang.host"));
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("code",code);
        getBody.setParamters(paramters);
        HttpEntity entity;
        try {
            entity = httpUtil.doGet(getBody);
            if (entity != null) {
                String  entityStr= EntityUtils.toString(entity,"utf-8");
                logger.debug("entityStr:"+entityStr);
                RiReturnRsp<JSONObject> vipcode =  JSONObject.parseObject(entityStr,RiReturnRsp.class);
                JSONObject vipJsonObject = vipcode.getData();
                if (vipJsonObject != null) {
                    return vipJsonObject.toJavaObject(VipCodeRsp.class);
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
     * @Description: 获取优惠券相关信息
     * @Author: YPLI
     * @Date:
     *
     **/
    @Override
    public PlacedOrderRsp getPlacedOrder(String token,String wishid){
        HttpUtil httpUtil = new HttpUtil();
        GetBody getBody = new GetBody();
        getBody.setAuthorization(token);
        getBody.setAPI(environment.getProperty("rishang.url.mallnew")+wishid);
        getBody.setHost(environment.getProperty("rishang.host"));
        HttpEntity entity;
        try {
            entity = httpUtil.doGet(getBody);
            if (entity != null) {
                String  entityStr= EntityUtils.toString(entity,"utf-8");
                logger.debug("entityStr:"+entityStr);
                RiReturnRsp<JSONObject> placedOrderReturn =  JSONObject.parseObject(entityStr,RiReturnRsp.class);
                JSONObject orderJsonObject = placedOrderReturn.getData();
                if (orderJsonObject != null) {
                    return orderJsonObject.toJavaObject(PlacedOrderRsp.class);
                }

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
        HttpUtil httpUtil = new HttpUtil();
        PostBody<SubmitOrderReq> postBody = new PostBody();
        postBody.setAuthorization(token);
        postBody.setAPI(environment.getProperty("rishang.url.submitOrder"));
        postBody.setHost(environment.getProperty("rishang.host"));
        postBody.setParamters(req);
        HttpEntity entity;
        try {
            entity = httpUtil.doPost(postBody);
            if (entity != null) {
                String  entityStr= EntityUtils.toString(entity,"utf-8");
                logger.debug("entityStr:"+entityStr);
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

    @Override
    public boolean pay(String pwd,String wishid) {
        HttpUtil httpUtil = new HttpUtil();
        PostBody postBody = new PostBody();
        postBody.setAuthorization(pwd);
        postBody.setAPI(environment.getProperty("rishang.url.pay")+wishid);
        postBody.setHost(environment.getProperty("rishang.host"));
        HttpEntity entity;
        try {
            entity = httpUtil.doPost(postBody);
            if (entity != null) {
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean buyGoods(String user, PostBody postBody) {
        return false;
    }

    /**
     * @Description: 获取我的订单信息 0-未提交 1-已提交 2-已完成
     * @Author: YPLI
     * @Date: 2022/1/8 0008 22:37
     * [token, state]
     * com.crazyloong.cat.dto.RiReturnRsp<com.crazyloong.cat.dto.WishRsp>
     **/
    @Override
    public RiReturnRsp<List<Object>> getWishs(String token, Integer state) {
        HttpUtil httpUtil = new HttpUtil();
        GetBody getBody = new GetBody();
        getBody.setAuthorization(token);
        getBody.setAPI(environment.getProperty("rishang.url.wishs"));
        getBody.setHost(environment.getProperty("rishang.host"));
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("state",state);
        getBody.setParamters(paramters);
        HttpEntity entity;
        try {
            entity = httpUtil.doGet(getBody);
            if (entity != null) {
                String  entityStr= EntityUtils.toString(entity,"utf-8");
                logger.debug("entityStr:"+entityStr);
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

    public Map<String,Integer> getCart(String pwd){
        HttpUtil httpUtil = new HttpUtil();
        PostBody postBody = new PostBody();
        postBody.setAuthorization(pwd);
        postBody.setAPI(environment.getProperty("rishang.url.getCart"));
        postBody.setHost(environment.getProperty("rishang.host"));
        Map<String,Object> paramters = new HashMap<String,Object>();
        paramters.put("v","v4");
        paramters.put("sys","Android");
        postBody.setParamters(paramters);
        HttpEntity entity;
        try {
            entity = httpUtil.doPost(postBody);
            if (entity != null) {
                String  entityStr= EntityUtils.toString(entity,"utf-8");
                System.out.println(entityStr);
                JSONObject jb = JSONObject.parseObject(entityStr);
                JSONObject bonded = (JSONObject)((JSONObject)jb.get("data")).get("bonded");
                JSONArray goodInfos = (JSONArray)bonded.get("details");
                Map<String,Integer> goods = new HashMap<>();
                for (Iterator iterator = goodInfos.iterator(); iterator.hasNext();) {
                    JSONObject job=(JSONObject)iterator.next();
                    String abiid = job.get("abiid").toString();
                    Integer num = (Integer)job.get("num");
                    goods.put(abiid,num);
                }
                return goods;
            }
        } catch (Exception e){
            e.printStackTrace();
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
        HttpUtil httpUtil = new HttpUtil();
        PostBody postBody = new PostBody();
        postBody.setAuthorization(pwd);
        postBody.setAPI(environment.getProperty("rishang.url.login"));
        postBody.setHost(environment.getProperty("rishang.host"));
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
            entity = httpUtil.doPost(postBody);
            if (entity != null) {
                String entityStr = EntityUtils.toString(entity,"utf-8");
                System.out.println(entityStr);
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

}
