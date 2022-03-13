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
    HNRsp<List<CategoryRsp>> findCategoryList();


}
