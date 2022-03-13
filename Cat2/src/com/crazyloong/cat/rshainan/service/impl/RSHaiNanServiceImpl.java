package com.crazyloong.cat.rshainan.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.crazyloong.cat.execption.RiBizExecption;
import com.crazyloong.cat.pojo.GetBody;
import com.crazyloong.cat.rshainan.constant.RishangHNURL;
import com.crazyloong.cat.rshainan.dto.*;
import com.crazyloong.cat.rshainan.service.RSHaiNanService;
import com.crazyloong.cat.util.HttpUtil;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static String RISHANG_HOST = "service.hndutyfree.com.cn";

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
        paramters.put("stockId","6868");
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody);
            if (entityStr != null) {
                HNRsp<LoginRsp> loginRspHNRsp =  JSONObject.parseObject(entityStr,new TypeReference<HNRsp<LoginRsp>>(){});
                return loginRspHNRsp.getData().getToken();
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
        getBody.setAPI(RishangHNURL.RSHN_FINDGOODSLIST.code);
        getBody.setHost(RISHANG_HOST);
        getBody.setAuthorization(hnReq.getToken());
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("categoryId",hnReq.getCategoryId());
        paramters.put("pageSize",hnReq.getPageSize());
        if(StringUtils.isNullOrEmpty(hnReq.getGoodsId())) {
            paramters.put("goodsId",hnReq.getGoodsId());
        }
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody);
            if (entityStr != null) {
                HNRsp<GoodsListRsp> goodsListRspHNRsp =  JSONObject.parseObject(entityStr,new TypeReference<HNRsp<GoodsListRsp>>(){});
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
        getBody.setAPI(RishangHNURL.RSHN_FINDGOODSLISTSEARCH.code);
        getBody.setHost(RISHANG_HOST);
        getBody.setAuthorization(hnReq.getToken());
        Map<String, Object> paramters = new HashMap<>();
        paramters.put("categoryId",hnReq.getCategoryId());
        paramters.put("pageSize",hnReq.getPageSize());
        if(StringUtils.isNullOrEmpty(hnReq.getGoodsId())) {
            paramters.put("goodsId",hnReq.getGoodsId());
        }
        getBody.setParamters(paramters);
        try {
            String entityStr = httpUtil.doGet(getBody);
            if (entityStr != null) {
                HNRsp<GoodsListSearchRsp> goodsListSearchRsp =  JSONObject.parseObject(entityStr,new TypeReference<HNRsp<GoodsListSearchRsp>>(){});
                return goodsListSearchRsp;
            }
        } catch (Exception e){
            logger.error("日上海南获取商品列表 失败",e);
            throw new RiBizExecption("日上海南获取商品列表 失败",e);
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
    public HNRsp<List<CategoryRsp>> findCategoryList() {
        GetBody getBody = new GetBody();
        getBody.setAPI(RishangHNURL.RSHN_FINDCATEGORYLIST.code);
        getBody.setHost(RISHANG_HOST);
        try {
            String entityStr = httpUtil.doGet(getBody);
            if (entityStr != null) {
                HNRsp<List<CategoryRsp>> categoryListRsp =  JSONObject.parseObject(entityStr,new TypeReference<HNRsp<List<CategoryRsp>>>(){});
                return categoryListRsp;
            }
        } catch (Exception e){
            logger.error("日上海南获取商品列表 失败",e);
            throw new RiBizExecption("日上海南获取商品列表 失败",e);
        }
        return null;
    }
}
