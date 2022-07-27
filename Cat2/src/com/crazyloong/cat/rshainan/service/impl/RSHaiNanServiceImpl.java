package com.crazyloong.cat.rshainan.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.crazyloong.cat.execption.RiBizExecption;
import com.crazyloong.cat.pojo.GetBody;
import com.crazyloong.cat.rshainan.constant.RewardOrderType;
import com.crazyloong.cat.rshainan.constant.RishangHNEnum;
import com.crazyloong.cat.rshainan.constant.RishangHNURL;
import com.crazyloong.cat.rshainan.dto.*;
import com.crazyloong.cat.rshainan.service.RSHaiNanService;
import com.crazyloong.cat.util.CacheUtil;
import com.crazyloong.cat.util.HttpUtil;
import com.crazyloong.cat.util.LiuMingHttpsUtils;
import com.crazyloong.cat.util.UrlBuilder;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/13 21:04
 * @Description : 日上海南服务类
 */
@Service
public class RSHaiNanServiceImpl implements RSHaiNanService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private CacheUtil cacheUtil;
    @Autowired
    private LiuMingHttpsUtils liuMingHttpsUtils;

    private static String RISHANG_HOST = "service.hndutyfree.com.cn";
    private static String RISHANG_HOST1 = "service.cdfhnms.com";

    /**
     * 功能描述： 日上海南登录
     * @Param:
     * @param hnReq
     * @Return: java.lang.String
     * @Author:
     * @Date: 2022/3/13 21:31
     * @Description:
     */
    @Override
    public String login(HNReq hnReq) {
        GetBody getBody = new GetBody();
        getBody.setAPI(RishangHNURL.RSHN_LOGINWITHPASSWORD.code);
        getBody.setHost(RISHANG_HOST);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("phone",hnReq.getPhone());
        paramters.put("password",hnReq.getPassword());
        paramters.put("stockId","6922");
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.HN_ONCE_LOGIN);
            if (entityStr != null) {
                // 第一次登录获取token
                HNRsp<LoginRsp> loginRspHNRsp =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
                if (loginRspHNRsp.getData() != null) {
                    getBody.setAuthorization(loginRspHNRsp.getData().getToken());
                    // 进行第二次登录
                    String entityStrTwice = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
                    HNRsp<LoginRsp> loginRspHNRspTwice =  JSONObject.parseObject(entityStrTwice,new TypeReference<>(){});
                    logger.info("日上海南登录"+entityStrTwice);
                    if (loginRspHNRspTwice.getData() != null) {
                        return loginRspHNRspTwice.getData().getToken();
                    } else {
                        throw new RiBizExecption("日上海南登录 失败："+loginRspHNRspTwice.getMessage());
                    }
                } else {
                    throw new RiBizExecption("日上海南登录 失败："+loginRspHNRsp.getMessage());
                }
            }
        } catch (Exception e){
            logger.error("日上海南登录 失败",e);
            throw new RiBizExecption("日上海南登录 失败",e);
        }
        return null;
    }

    /**
     * 功能描述：根据商品分类/产品分类ID|每页数量|当前最后一笔商品ID 获取商品列表
     * @Param:
     * @param hnReq
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.GoodsListRsp>
     * @Author:
     * @Date: 2022/3/13 21:52
     * @Description:
     */
    @Override
    public HNRsp<GoodsListRsp> findGoodsList(HNReq hnReq) {
        GetBody getBody = new GetBody();
        getBody.setAuthorization(hnReq.getToken());
        getBody.setAPI(RishangHNURL.RSHN_FINDGOODSLIST.code);
        getBody.setHost(RISHANG_HOST);
        getBody.setAuthorization(hnReq.getToken());
        Map<String, Object> paramters = new HashMap<>();
        if (hnReq.getCategoryId() != null) {
            paramters.put("categoryId",hnReq.getCategoryId());
        }
        if (!StringUtils.isNullOrEmpty(hnReq.getKeyword())) {
            paramters.put("keyword",hnReq.getKeyword());
        }
        paramters.put("pageSize",hnReq.getPageSize());
        if(!StringUtils.isNullOrEmpty(hnReq.getGoodsId())) {
            paramters.put("goodsId",hnReq.getGoodsId());
        }
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody, RishangHNEnum.GetType.TOKEN);
            logger.info("日上海南获取商品列表"+entityStr);
            if (entityStr != null) {
                HNRsp<GoodsListRsp> goodsListRspHNRsp =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return goodsListRspHNRsp;
            }
        } catch (Exception e){
            logger.error("日上海南获取商品列表 失败",e);
            throw new RiBizExecption("日上海南获取商品列表 失败",e);
        }
        return null;
    }

    /**
     * 功能描述：根据商品分类ID/产品分类ID|每页数量|当前最后一笔商品ID 分类列表
     * @Param:
     * @param hnReq
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.GoodsListSearchRsp>
     * @Author:
     * @Date: 2022/3/13 21:53
     * @Description:
     */
    @Override
    public HNRsp<GoodsListSearchRsp> findGoodsListSearch(HNReq hnReq) {
        GetBody getBody = new GetBody();
        getBody.setAuthorization(hnReq.getToken());
        getBody.setAPI(RishangHNURL.RSHN_FINDGOODSLISTSEARCH.code);
        getBody.setHost(RISHANG_HOST);
        getBody.setAuthorization(hnReq.getToken());
        Map<String, Object> paramters = new HashMap<>();
        if (hnReq.getCategoryId() != null) {
            paramters.put("categoryId",hnReq.getCategoryId());
        }
        if (!StringUtils.isNullOrEmpty(hnReq.getKeyword())) {
            paramters.put("keyword",hnReq.getKeyword());
        }
        paramters.put("pageSize",hnReq.getPageSize());
        if(!StringUtils.isNullOrEmpty(hnReq.getGoodsId())) {
            paramters.put("goodsId",hnReq.getGoodsId());
        }
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
            if (entityStr != null) {
                logger.info("日上海南获取分类列表"+entityStr);
                HNRsp<GoodsListSearchRsp> goodsListSearchRsp =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return goodsListSearchRsp;
            }
        } catch (Exception e){
            logger.error("日上海南获取分类列表 失败",e);
            throw new RiBizExecption("日上海南获取分类列表 失败",e);
        }
        return null;
    }

    /**
     * 功能描述：获取所有商品分类
     * @Param:
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<java.util.List<com.crazyloong.cat.rshainan.dto.CategoryRsp>>
     * @Author:
     * @Date: 2022/3/13 21:55
     * @Description:
     */
    @Override
    public HNRsp<List<CategoryRsp>> findCategoryList(HNReq hnReq) {
        GetBody getBody = new GetBody();
        getBody.setAuthorization(hnReq.getToken());
        getBody.setAPI(RishangHNURL.RSHN_FINDCATEGORYLIST.code);
        getBody.setHost(RISHANG_HOST);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
            if (entityStr != null) {
                HNRsp<List<CategoryRsp>> categoryListRsp =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return categoryListRsp;
            }
        } catch (Exception e){
            logger.error("日上海南获取所有商品分类 失败",e);
            throw new RiBizExecption("日上海南获取所有商品分类 失败",e);
        }
        return null;
    }

    /**
     * 功能描述：根据商品ID获取商品详情
     * @Param:
     * @param hnReq
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.GoodsDetailRsp>
     * @Author:
     * @Date: 2022/3/13 23:31
     * @Description:
     */
    @Override
    public HNRsp<GoodsDetailRsp> findGoodsDetailByIdAlways(HNReq hnReq) {
        GetBody getBody = new GetBody();
        getBody.setAPI(RishangHNURL.RSHN_FINDGOODSDETAILBYIDALWAYS.code);
        getBody.setHost(RISHANG_HOST);
        getBody.setAuthorization(hnReq.getToken());
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("goodsId",hnReq.getGoodsId());
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
            if (entityStr != null) {
                //logger.info("日上海南根据商品ID获取商品详情"+entityStr);
                HNRsp<GoodsDetailRsp> GoodsDetailRsp =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return GoodsDetailRsp;
            }
        } catch (Exception e){
            logger.error("日上海南根据商品ID获取商品详情 失败",e);
            throw new RiBizExecption("日上海南根据商品ID获取商品详情 失败",e);
        }
        return null;
    }

    /**
     * 功能描述：获取首页相关信息
     * @Param:
     * @param hnReq
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.SubjectListRsp>
     * @Author:
     * @Date: 2022/3/13 23:50
     * @Description:
     */
    @Override
    public HNRsp<SubjectListRsp> findSubjectList(HNReq hnReq){
        GetBody getBody = new GetBody();
        getBody.setAuthorization(hnReq.getToken());
        getBody.setAPI(RishangHNURL.RSHN_FINDSUBJECTLIST.code);
        getBody.setHost(RISHANG_HOST);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("pageSize",hnReq.getPageSize());
        paramters.put("pageNum",hnReq.getPageNum());
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
            if (entityStr != null) {

                HNRsp<SubjectListRsp> subjectListRsp =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return subjectListRsp;
            }
        } catch (Exception e){
            logger.error("日上海南获取首页相关信息 失败",e);
            throw new RiBizExecption("日上海南获取首页相关信息 失败",e);
        }
        return null;
    }

    /**
    * 功能描述：获取订单相关信息
    * @Param:
     * @param hnReq
    * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.OrderListRsp>
    * @Author:
    * @Date: 2022/5/19 20:39
    * @Description:
    */
    @Override
    public HNRsp<OrderListRsp> findOrderList(HNReq hnReq) {
        GetBody getBody = new GetBody();
        getBody.setAuthorization(cacheUtil.getHNToken(hnReq.getPhone()));
        getBody.setAPI(RishangHNURL.RSHN_FINDORDERLIST.code);
        getBody.setHost(RISHANG_HOST);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("pageSize",hnReq.getPageSize());
        paramters.put("pageNum",hnReq.getPageNum());
        paramters.put("type",hnReq.getType());
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
            if (entityStr != null) {

                HNRsp<OrderListRsp> orderListRspHNRsp =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return orderListRspHNRsp;
            }
        } catch (Exception e){
            logger.error("日上海南获取订单信息 失败",e);
            throw new RiBizExecption("日上海南获取订单信息 失败",e);
        }
        return null;
    }

    /**
     * 功能描述：获取员工返利相关信息
     * @Param:
     * @param hnReq
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.SubjectListRsp>
     * @Author:
     * @Date: 2022/3/13 23:50
     * @Description:
     */
    @Override
    public HNRsp<RewardDay> getRewardDay(HNReq hnReq){
        GetBody getBody = new GetBody();
        getBody.setAuthorization(cacheUtil.getHNToken(hnReq.getPhone()));
        getBody.setAPI(RishangHNURL.RSHN_GETREWARDDAY.code);
        getBody.setHost(RISHANG_HOST);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("pageSize",hnReq.getPageSize());
        paramters.put("pageNum",hnReq.getPageNum());
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
            if (entityStr != null) {

                HNRsp<RewardDay> rewardDayHNRsp =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return rewardDayHNRsp;
            }
        } catch (Exception e){
            logger.error("日上海南获取订单信息 失败",e);
            throw new RiBizExecption("日上海南获取订单信息 失败",e);
        }
        return null;
    }

    /**
     * 功能描述：获取员工返利明细相关信息
     * @Param:
     * @param hnReq
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.SubjectListRsp>
     * @Author:
     * @Date: 2022/3/13 23:50
     * @Description:
     */
    @Override
    public HNRsp<RewardOrder> getRewardOrder(HNReq hnReq){
        GetBody getBody = new GetBody();
        getBody.setAuthorization(cacheUtil.getHNToken(hnReq.getPhone()));
        getBody.setAPI(RishangHNURL.RSHN_GETREWARDORDER.code);
        getBody.setHost(RISHANG_HOST1);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("pageSize","100");
        paramters.put("pageNum","1");
        paramters.put("id",hnReq.getMainOrderId());
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
            if (entityStr != null) {

                HNRsp<RewardOrder> rewardOrderHNRsp =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
                int pageSizeAll = rewardOrderHNRsp.getData().getTotal()%100 == 0 ? rewardOrderHNRsp.getData().getTotal()/100 :rewardOrderHNRsp.getData().getTotal()/100+1;
                for (int i = 2; i <= pageSizeAll; i++) {
                    getRewardOrderByPage(hnReq,i,rewardOrderHNRsp);
                }
                // 数据处理
                rewardOrderHNRsp.getData().setId(hnReq.getMainOrderId());
                // 码值转换
                for (RewardOrder.ListDTO dto: rewardOrderHNRsp.getData().getList()) {
                    dto.setTypeName(RewardOrderType.getDescByCode(String.valueOf(dto.getType())));
                }
                return rewardOrderHNRsp;
            }
        } catch (Exception e){
            logger.error("日上海南获取订单信息 失败",e);
            throw new RiBizExecption("日上海南获取订单信息 失败",e);
        }
        return null;
    }
    private void getRewardOrderByPage(HNReq hnReq,int pageNum,HNRsp<RewardOrder> rewardOrderHNRsp){
        GetBody getBody = new GetBody();
        getBody.setAuthorization(cacheUtil.getHNToken(hnReq.getPhone()));
        getBody.setAPI(RishangHNURL.RSHN_GETREWARDORDER.code);
        getBody.setHost(RISHANG_HOST1);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("pageSize","100");
        paramters.put("pageNum",pageNum);
        paramters.put("id",hnReq.getMainOrderId());
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
            if (entityStr != null) {
                HNRsp<RewardOrder> rewardOrderHNRsp1 =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
                rewardOrderHNRsp.getData().getList().addAll(rewardOrderHNRsp1.getData().getList());

            }
        } catch (Exception e){
            logger.error("日上海南获取订单信息 失败",e);
            throw new RiBizExecption("日上海南获取订单信息 失败",e);
        }
    }



    /**
     * 功能描述：获取订单地址等明细信息
     * @Param:
     * @param hnReq
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.SubjectListRsp>
     * @Author:
     * @Date: 2022/3/13 23:50
     * @Description:
     */
    public HNRsp<OrderDetail> findOrderDetail(HNReq hnReq){
        GetBody getBody = new GetBody();
        getBody.setAuthorization(cacheUtil.getHNToken(hnReq.getPhone()));
        getBody.setAPI(RishangHNURL.RSHN_FINDORDERDETAIL.code);
        getBody.setHost(RISHANG_HOST);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("mainOrderId",hnReq.getMainOrderId());
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
            if (entityStr != null) {

                HNRsp<OrderDetail> orderDetailHNRsp =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
                return orderDetailHNRsp;
            }
        } catch (Exception e){
            logger.error("日上海南获取订单信息 失败",e);
            throw new RiBizExecption("日上海南获取订单信息 失败",e);
        }
        return null;
    }

    /**
    * 功能描述：下单
    * @Param:
     * @param placeOrderReq
    * @Return: java.lang.Boolean
    * @Author:
    * @Date: 2022/3/19 18:10
    * @Description:
    */
    @Override
    public Boolean placeOrder(PlaceOrderReq placeOrderReq) {
        GetBody getBody = new GetBody();
        getBody.setAuthorization(placeOrderReq.getToken());
        getBody.setAPI(RishangHNURL.RSHN_PREPAREORDER.code);
        getBody.setHost(RISHANG_HOST);
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("count",placeOrderReq.getCount());
        paramters.put("goodsId",placeOrderReq.getGoodsId());
        paramters.put("point",placeOrderReq.getPoint());
        paramters.put("couponAmount",placeOrderReq.getCouponAmount());
        paramters.put("pointRemain",placeOrderReq.getPointRemain());
        getBody.setParamters(paramters);
        HNRsp<PrepareOrderRsp> prepareOrderRsp = null;
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
            if (entityStr != null) {
                prepareOrderRsp =  JSONObject.parseObject(entityStr,new TypeReference<>(){});
            }
        } catch (Exception e){
            logger.error("日上海南获取首页相关信息 失败",e);
            throw new RiBizExecption("日上海南下单前准备 失败",e);
        }
        getBody.setAPI(RishangHNURL.RSHN_GETMEMBERPOINTCLOSED.code);
        paramters = new HashMap<>();
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
            if (entityStr == null) {
                throw new RiBizExecption("日上海南下单前准备 失败");
            }
        } catch (Exception e){
            logger.error("日上海南获取首页相关信息 失败",e);
            throw new RiBizExecption("日上海南下单前准备 失败",e);
        }

        getBody.setAPI(RishangHNURL.RSHN_ORDERMEMBERCOUPON.code);
        paramters = new HashMap<>();
        paramters.put("count",placeOrderReq.getCount());
        paramters.put("goodsId",placeOrderReq.getGoodsId());
        paramters.put("mainOrderId",prepareOrderRsp.getData().getMainOrderId());
        paramters.put("memberCoupons",placeOrderReq.getMemberCoupons());
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody,RishangHNEnum.GetType.TOKEN);
            if (entityStr == null) {
                throw new RiBizExecption("日上海南下单前准备 失败");
            }
        } catch (Exception e){
            logger.error("日上海南获取首页相关信息 失败",e);
            throw new RiBizExecption("日上海南下单前准备 失败",e);
        }

        GetBody getBody1 = new GetBody();
        getBody1.setAuthorization(placeOrderReq.getToken());
        getBody1.setAPI(RishangHNURL.RSHN_CONFIRMORDER.code);
        getBody1.setHost(RISHANG_HOST);
        paramters = new LinkedHashMap<>();
        paramters.put("prov",prepareOrderRsp.getData().getMemberAddress().getProvince());
        paramters.put("city",prepareOrderRsp.getData().getMemberAddress().getCity());
        paramters.put("area",prepareOrderRsp.getData().getMemberAddress().getDistrict());
        paramters.put("receiveAddress",prepareOrderRsp.getData().getMemberAddress().getAddress());
        paramters.put("receiveName",prepareOrderRsp.getData().getMemberAddress().getName());
        paramters.put("receivePhone",prepareOrderRsp.getData().getMemberAddress().getMobile());
        if (prepareOrderRsp.getData().getNeedCheck()) {
            paramters.put("certName",prepareOrderRsp.getData().getMemberCheck().getCertName());
            paramters.put("certNo",prepareOrderRsp.getData().getMemberCheck().getCertNumber());
            paramters.put("flightNo",prepareOrderRsp.getData().getMemberCheck().getTrafficNumber());
            paramters.put("flightDate",prepareOrderRsp.getData().getMemberCheck().getLeaveDate());
            paramters.put("levelType",prepareOrderRsp.getData().getMemberCheck().getTrafficName());
        }
        paramters.put("stockId",placeOrderReq.getStockId());
        paramters.put("point",placeOrderReq.getPoint());
        paramters.put("mainOrderId",prepareOrderRsp.getData().getMainOrderId());
        paramters.put("goodsId",placeOrderReq.getGoodsId());
        paramters.put("count",placeOrderReq.getCount());
        paramters.put("memberCoupons",placeOrderReq.getMemberCoupons());
        paramters.put("mac",prepareOrderRsp.getData().getMac());
        getBody1.setParamters(paramters);
        UrlBuilder urlBuilder = new UrlBuilder(getBody1);
        String urlstr = urlBuilder.toString();
        try {
            String entity = liuMingHttpsUtils.httpRequest(urlstr, placeOrderReq.getToken());
            if (entity != null) {
                HNRsp<Boolean> booleanrsp = JSONObject.parseObject(entity,new TypeReference<>(){});
                if (booleanrsp.getData()) {
                    return true;
                } else {
                    throw new RiBizExecption(booleanrsp.getMessage());
                }
            }
        } catch (Exception e){
            logger.error("生成订单处理异常:",e);
            throw new RiBizExecption("生成订单处理异常 失败",e);
        }

        return null;
    }

    /**
    * 功能描述： 校验token 是否过期
    * @Param:
    * @Return: java.lang.Boolean
    * @Author:
    * @Date: 2022/3/21 20:33
    * @Description:
    */
    @Override
    public Boolean checkToken(String token){
        HNReq hnReq = new HNReq();
        hnReq.setToken(token);
        hnReq.setKeyword("科颜氏");
        hnReq.setPageSize("10");
        HNRsp<GoodsListRsp> goodsListRsp = this.findGoodsList(hnReq);
        return checkError(goodsListRsp);
    }

    /**
    * 功能描述： 海南日上校验错误信息
    * @Param:
     * @param hnRsp
    * @Return: java.lang.Boolean
    * @Author:
    * @Date: 2022/3/19 16:29
    * @Description:
    */
    @Override
    public Boolean checkError(HNRsp<?> hnRsp){
        if (1024 == hnRsp.getCode()) {
            return false;
        }
        if (0 != hnRsp.getCode()) {
            throw new RiBizExecption(hnRsp.getMessage());
        }
        return true;
    }
}
