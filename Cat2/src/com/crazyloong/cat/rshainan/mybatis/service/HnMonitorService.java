package com.crazyloong.cat.rshainan.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.crazyloong.cat.rshainan.mybatis.entity.HnMonitor;

import java.util.List;

/**
 * 日上海南监控(HnMonitor)表服务接口
 *
 * @author makejava
 * @since 2022-03-17 21:44:40
 */
public interface HnMonitorService extends IService<HnMonitor> {

    /**
    * 功能描述： 获取生效的监控任务
    * @Param:
    * @Return: com.crazyloong.cat.rshainan.mybatis.entity.HnMonitor
    * @Author:
    * @Date: 2022/3/19 18:43
    * @Description:
    */
    List<HnMonitor> getEffect();

    /**
    * 功能描述：根据商品编号获取监控任务
    * @Param:
     * @param goodsId
    * @Return: com.crazyloong.cat.rshainan.mybatis.entity.HnMonitor
    * @Author:
    * @Date: 2022/3/23 21:40
    * @Description:
    */
    HnMonitor getByGoodsId(String goodsId);

    /**
    * 功能描述：更新监控账号
    * @Param:
     * @param hnMonitor
    * @Return: java.lang.Boolean
    * @Author:
    * @Date: 2022/4/3 22:15
    * @Description:
    */
    Boolean updateAll(HnMonitor hnMonitor);

    boolean myUpdateById(HnMonitor hnMonitor);
}

