package com.crazyloong.cat.rshainan.controller;

import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.crazyloong.cat.rshainan.constant.RishangHNSuccess;
import com.crazyloong.cat.rshainan.dto.*;
import com.crazyloong.cat.rshainan.service.RSHaiNanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/13 22:23
 * @Description :
 */
@RestController
@RequestMapping("rihainan")
public class RSHaiNanController extends ApiController {

    @Autowired
    private RSHaiNanService rsHaiNanService;
    /**
     * 功能描述： 日上海南登录
     * @Param:
     * @param hnReq
     * @Return: java.lang.String
     * @Author:
     * @Date: 2022/3/13 21:31
     * @Description:
     */
    @PostMapping("/login")
    public R<String> login(@RequestBody HNReq hnReq) {
        return success(rsHaiNanService.login(hnReq));
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
    @PostMapping("/findGoodsList")
    public R<GoodsListRsp> findGoodsList(@RequestBody HNReq hnReq) {
        HNRsp<GoodsListRsp> goodsListRsp = rsHaiNanService.findGoodsList(hnReq);
        if (RishangHNSuccess.SUCCESS.code == goodsListRsp.getCode()) {
            return success(goodsListRsp.getData());
        }
        return failed("获取商品列表失败："+goodsListRsp.getMessage());
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
    @PostMapping("/findGoodsListSearch")
    public R<GoodsListSearchRsp> findGoodsListSearch(@RequestBody HNReq hnReq) {
        HNRsp<GoodsListSearchRsp> goodsListSearch = rsHaiNanService.findGoodsListSearch(hnReq);
        if (RishangHNSuccess.SUCCESS.code == goodsListSearch.getCode()) {
            return success(goodsListSearch.getData());
        }
        return failed("获取商品列表失败："+goodsListSearch.getMessage());
    }

    /**
     * 功能描述：获取所有商品分类
     * @Param:
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<java.util.List<com.crazyloong.cat.rshainan.dto.CategoryRsp>>
     * @Author:
     * @Date: 2022/3/13 21:55
     * @Description:
     */
    @PostMapping("/findCategoryList")
    public R<List<CategoryRsp>> findCategoryList() {
        HNRsp<List<CategoryRsp>> categoryList = rsHaiNanService.findCategoryList();
        if (RishangHNSuccess.SUCCESS.code == categoryList.getCode()) {
            return success(categoryList.getData());
        }
        return failed("获取商品列表失败："+categoryList.getMessage());
    }
}
