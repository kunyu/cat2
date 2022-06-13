package com.crazyloong.cat.rshainan.service;

import com.crazyloong.cat.rshainan.dto.*;

import java.util.List;

public interface RSHaiNanService {

    /**
    * 功能描述： 日上海南登录
    * @Param:
     * @param hnReq
    * @Return: java.lang.String
    * @Author:
    * @Date: 2022/3/13 21:31
    * @Description:
    */
    String login(HNReq hnReq);

    /**
    * 功能描述：根据商品分类/产品分类ID|每页数量|当前最后一笔商品ID 获取商品列表
    * @Param:
     * @param hnReq
    * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.GoodsListRsp>
    * @Author:
    * @Date: 2022/3/13 21:52
    * @Description:
    */
    HNRsp<GoodsListRsp> findGoodsList(HNReq hnReq);

    /**
    * 功能描述：根据商品分类ID/产品分类ID|每页数量|当前最后一笔商品ID 分类列表
    * @Param:
     * @param hnReq
    * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.GoodsListSearchRsp>
    * @Author:
    * @Date: 2022/3/13 21:53
    * @Description:
    */
    HNRsp<GoodsListSearchRsp> findGoodsListSearch(HNReq hnReq);

    /**
    * 功能描述：获取所有商品分类
    * @Param:
    * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<java.util.List<com.crazyloong.cat.rshainan.dto.CategoryRsp>>
    * @Author:
    * @Date: 2022/3/13 21:55
    * @Description:
    */
    HNRsp<List<CategoryRsp>> findCategoryList(HNReq hnReq);


    /**
    * 功能描述：根据商品ID获取商品详情
    * @Param:
     * @param hnReq
    * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.GoodsDetailRsp>
    * @Author:
    * @Date: 2022/3/13 23:31
    * @Description:
    */
    HNRsp<GoodsDetailRsp> findGoodsDetailByIdAlways(HNReq hnReq);

    /**
    * 功能描述：获取首页相关信息
    * @Param:
     * @param hnReq
    * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.SubjectListRsp>
    * @Author:
    * @Date: 2022/3/13 23:50
    * @Description:
    */
    HNRsp<SubjectListRsp> findSubjectList(HNReq hnReq);

    /**
     * 功能描述：获取订单相关信息
     * @Param:
     * @param hnReq
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.SubjectListRsp>
     * @Author:
     * @Date: 2022/3/13 23:50
     * @Description:
     */
    HNRsp<OrderListRsp> findOrderList(HNReq hnReq);

    /**
     * 功能描述：获取员工返利相关信息
     * @Param:
     * @param hnReq
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.SubjectListRsp>
     * @Author:
     * @Date: 2022/3/13 23:50
     * @Description:
     */
    HNRsp<RewardDay> getRewardDay(HNReq hnReq);

    /**
     * 功能描述：获取员工返利明细相关信息
     * @Param:
     * @param hnReq
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.SubjectListRsp>
     * @Author:
     * @Date: 2022/3/13 23:50
     * @Description:
     */
    HNRsp<RewardOrder> getRewardOrder(HNReq hnReq);

    /**
     * 功能描述：获取订单地址等明细信息
     * @Param:
     * @param hnReq
     * @Return: com.crazyloong.cat.rshainan.dto.HNRsp<com.crazyloong.cat.rshainan.dto.SubjectListRsp>
     * @Author:
     * @Date: 2022/3/13 23:50
     * @Description:
     */
    HNRsp<OrderDetail> findOrderDetail(HNReq hnReq);


    /**
    * 功能描述：下单
    * @Param:
     * @param placeOrderReq
    * @Return: java.lang.Boolean
    * @Author:
    * @Date: 2022/3/19 18:06
    * @Description:
    */
    Boolean placeOrder(PlaceOrderReq placeOrderReq);

    /**
     * 功能描述： 校验token 是否过期
     * @Param:
     * @Return: java.lang.Boolean
     * @Author:
     * @Date: 2022/3/21 20:33
     * @Description:
     */
    Boolean checkToken(String token);

    /**
     * 功能描述： 海南日上校验错误信息
     * @Param:
     * @param hnRsp
     * @Return: java.lang.Boolean
     * @Author:
     * @Date: 2022/3/19 16:29
     * @Description:
     */
    Boolean checkError(HNRsp<?> hnRsp);
}
