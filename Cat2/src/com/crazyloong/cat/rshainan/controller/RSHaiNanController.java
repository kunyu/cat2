package com.crazyloong.cat.rshainan.controller;

import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.crazyloong.cat.rshainan.constant.RishangHNCategory;
import com.crazyloong.cat.rshainan.constant.RishangHNSuccess;
import com.crazyloong.cat.rshainan.dto.*;
import com.crazyloong.cat.rshainan.service.RSHaiNanService;
import com.crazyloong.cat.util.CacheUtil;
import com.crazyloong.cat.util.ExcelUtil;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
        return success(cacheUtil.getHNToken(hnReq.getPhone()));
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

    /**
    * 功能描述：获取订单相关信息
    * @Param:
     * @param hnReq
    * @Return: com.crazyloong.cat.api.R<com.crazyloong.cat.rshainan.dto.SubjectListRsp>
    * @Author:
    * @Date: 2022/5/19 20:38
    * @Description:
    */
    @PostMapping("/findOrderList")
    public R<OrderListRsp> findOrderList(@RequestBody HNReq hnReq) {
        HNRsp<OrderListRsp> orderList = rsHaiNanService.findOrderList(hnReq);
        if (RishangHNSuccess.SUCCESS.code == orderList.getCode()) {
            for (OrderListRsp.Order order : orderList.getData().getList()) {
                hnReq.setMainOrderId(order.getMainOrderId());
                HNRsp<OrderDetail> orderDetailHNRsp = rsHaiNanService.findOrderDetail(hnReq);
                order.setReceiveName(orderDetailHNRsp.getData().getReceiveName());
                order.setReceivePhone(orderDetailHNRsp.getData().getReceivePhone());
                order.setReceiveAddress(orderDetailHNRsp.getData().getReceiveProvince()
                        +orderDetailHNRsp.getData().getReceiveCity()
                        +orderDetailHNRsp.getData().getReceiveDistrict()
                        +orderDetailHNRsp.getData().getReceiveAddress());
                String goodsName = "";
                String goodsCount = "";
                for (int i = 0; i < orderDetailHNRsp.getData().getList().size(); i++) {
                    goodsName = goodsName + orderDetailHNRsp.getData().getList().get(i).getName()+",";
                    goodsCount = goodsCount + orderDetailHNRsp.getData().getList().get(i).getCount()+",";
                }
                goodsName = goodsName.substring(0,goodsName.length()-1);
                goodsCount = goodsCount.substring(0,goodsCount.length()-1);
                order.setGoodsName(goodsName);
                order.setGoodsCount(goodsCount);
            }
            return success(orderList.getData());
        }
        return failed("获取订单相关信息："+orderList.getMessage());
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
    @PostMapping("/getRewardDay")
    public R<RewardDay> getRewardDay(@RequestBody HNReq hnReq){
        HNRsp<RewardDay> rewardDay = rsHaiNanService.getRewardDay(hnReq);
        if (RishangHNSuccess.SUCCESS.code == rewardDay.getCode()) {
            return success(rewardDay.getData());
        }
        return failed("获取订单相关信息："+rewardDay.getMessage());
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
    @PostMapping("/getRewardOrder")
    public R<RewardOrder> getRewardOrder(@RequestBody HNReq hnReq){
        HNRsp<RewardOrder> rewardOrder = rsHaiNanService.getRewardOrder(hnReq);
        if (RishangHNSuccess.SUCCESS.code == rewardOrder.getCode()) {
            return success(rewardOrder.getData());
        }
        return failed("获取订单相关信息："+rewardOrder.getMessage());
    }

    @PostMapping("/exportRewardOrder")
    public R<List<RewardOrder>> exportRewardOrder(@RequestBody HNReq hnReq,HttpServletResponse response){
        List<RewardOrder> rewardOrderList = new ArrayList<>();
        for (RewardDay.ListDTO mainOrderId: hnReq.getMainOrderIds()) {
            if (mainOrderId != null) {
                hnReq.setMainOrderId(mainOrderId.getId());
                HNRsp<RewardOrder> rewardOrder = rsHaiNanService.getRewardOrder(hnReq);
                rewardOrderList.add(rewardOrder.getData());
            }
        }
        return success(rewardOrderList);
    }

}
