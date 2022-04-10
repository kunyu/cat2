package com.crazyloong.cat.rshainan.controller;

import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.crazyloong.cat.rshainan.constant.RishangHNCategory;
import com.crazyloong.cat.rshainan.constant.RishangHNSuccess;
import com.crazyloong.cat.rshainan.dto.*;
import com.crazyloong.cat.rshainan.service.RSHaiNanService;
import com.crazyloong.cat.util.CacheUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/13 22:23
 * @Description :
 */
@RestController
@RequestMapping("rihainan")
public class RSHaiNanController extends ApiController {

    private static final String PHONE = "19912122212";
    @Autowired
    private RSHaiNanService rsHaiNanService;
    @Resource
    private CacheUtil cacheUtil;

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
    public R<PageGoodsListRsp> findGoodsList(@RequestBody HNReq hnReq) {
        hnReq.setToken(cacheUtil.getHNToken(PHONE));
        PageGoodsListRsp pageGoodsListRsp = new PageGoodsListRsp();
        List<PageGoodsListRsp.PageGoods> sell_hot = new ArrayList<>();

        HNRsp<GoodsListRsp> goodsListRsp = rsHaiNanService.findGoodsList(hnReq);
        goodsListRsp.getData().getList().forEach(listDTO -> {
            PageGoodsListRsp.PageGoods pageGoods = new PageGoodsListRsp.PageGoods();
            BeanUtils.copyProperties(listDTO,pageGoods);
            sell_hot.add(pageGoods);
        });
        pageGoodsListRsp.setSell_hot(sell_hot);
        pageGoodsListRsp.setSkincare(sell_hot);
        pageGoodsListRsp.setPerfume(sell_hot);
        pageGoodsListRsp.setColours(sell_hot);
        return success(pageGoodsListRsp);
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
    @PostMapping("/findAllGoodsList")
    public R<PageGoodsListRsp> findAllGoodsList(@RequestBody HNReq hnReq) {
        hnReq.setToken(cacheUtil.getHNToken(PHONE));
        PageGoodsListRsp pageGoodsListRsp = new PageGoodsListRsp();
        List<PageGoodsListRsp.PageGoods> sell_hot = new ArrayList<>();
        List<PageGoodsListRsp.PageGoods> skincare = new ArrayList<>();
        List<PageGoodsListRsp.PageGoods> perfume = new ArrayList<>();
        List<PageGoodsListRsp.PageGoods> colours = new ArrayList<>();
        // 热卖商品
        HNRsp<SubjectListRsp> subjectList = rsHaiNanService.findSubjectList(hnReq);
        if (!rsHaiNanService.checkError(subjectList)) {
            hnReq.setToken(cacheUtil.getHNToken(PHONE));
            subjectList = rsHaiNanService.findSubjectList(hnReq);
        }
        subjectList.getData().getList().forEach(listDTO ->{
            PageGoodsListRsp.PageGoods pageGoods = new PageGoodsListRsp.PageGoods();
            BeanUtils.copyProperties(listDTO,pageGoods);
            sell_hot.add(pageGoods);
        });
        // 护肤
        hnReq.setCategoryId(RishangHNCategory.SKINCARE.code);
        HNRsp<GoodsListRsp> goodsListRsp = rsHaiNanService.findGoodsList(hnReq);
        goodsListRsp.getData().getList().forEach(listDTO -> {
            PageGoodsListRsp.PageGoods pageGoods = new PageGoodsListRsp.PageGoods();
            BeanUtils.copyProperties(listDTO,pageGoods);
            skincare.add(pageGoods);
        });
        // 彩妆
        hnReq.setCategoryId(RishangHNCategory.PERFUME.code);
        goodsListRsp = rsHaiNanService.findGoodsList(hnReq);
        goodsListRsp.getData().getList().forEach(listDTO -> {
            PageGoodsListRsp.PageGoods pageGoods = new PageGoodsListRsp.PageGoods();
            BeanUtils.copyProperties(listDTO,pageGoods);
            perfume.add(pageGoods);
        });
        // 香水
        hnReq.setCategoryId(RishangHNCategory.COLOURS.code);
        goodsListRsp = rsHaiNanService.findGoodsList(hnReq);
        goodsListRsp.getData().getList().forEach(listDTO -> {
            PageGoodsListRsp.PageGoods pageGoods = new PageGoodsListRsp.PageGoods();
            BeanUtils.copyProperties(listDTO,pageGoods);
            colours.add(pageGoods);
        });
        pageGoodsListRsp.setSell_hot(sell_hot);
        pageGoodsListRsp.setSkincare(skincare);
        pageGoodsListRsp.setPerfume(perfume);
        pageGoodsListRsp.setColours(colours);
        return success(pageGoodsListRsp);

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
        return failed("获取分类列表失败："+goodsListSearch.getMessage());
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
    public R<List<CategoryRsp>> findCategoryList(HNReq hnReq) {
        HNRsp<List<CategoryRsp>> categoryList = rsHaiNanService.findCategoryList(hnReq);
        if (RishangHNSuccess.SUCCESS.code == categoryList.getCode()) {
            return success(categoryList.getData());
        }
        return failed("获取所有商品分类："+categoryList.getMessage());
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
    @PostMapping("/findGoodsDetailByIdAlways")
    public R<GoodsDetailRsp> findGoodsDetailByIdAlways(@RequestBody HNReq hnReq) {
        HNRsp<GoodsDetailRsp> categoryList = rsHaiNanService.findGoodsDetailByIdAlways(hnReq);
        if (RishangHNSuccess.SUCCESS.code == categoryList.getCode()) {
            return success(categoryList.getData());
        }
        return failed("根据商品ID获取商品详情："+categoryList.getMessage());
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
    @PostMapping("/findSubjectList")
    public R<SubjectListRsp> findSubjectList(@RequestBody HNReq hnReq) {
        HNRsp<SubjectListRsp> categoryList = rsHaiNanService.findSubjectList(hnReq);
        if (RishangHNSuccess.SUCCESS.code == categoryList.getCode()) {
            return success(categoryList.getData());
        }
        return failed("获取首页相关信息："+categoryList.getMessage());
    }
}
