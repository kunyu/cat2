package com.crazyloong.cat.rshainan.service.impl;

import com.crazyloong.cat.execption.RiBizExecption;
import com.crazyloong.cat.rshainan.dto.GoodsDetailRsp;
import com.crazyloong.cat.rshainan.dto.HNReq;
import com.crazyloong.cat.rshainan.dto.HNRsp;
import com.crazyloong.cat.rshainan.dto.PlaceOrderReq;
import com.crazyloong.cat.rshainan.mybatis.entity.HnMonitor;
import com.crazyloong.cat.rshainan.mybatis.service.HnMonitorService;
import com.crazyloong.cat.rshainan.service.RSHaiNanService;
import com.crazyloong.cat.rshainan.service.RSHaiNanTaskService;
import com.crazyloong.cat.util.CacheUtil;
import com.crazyloong.cat.util.MailUtil;
import com.crazyloong.cat.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/19 16:11
 * @Description : 海南定时任务
 */
@Service
public class RSHaiNanTaskServiceImpl implements RSHaiNanTaskService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RSHaiNanService rsHaiNanService;
    @Autowired
    private CacheUtil cacheUtil;
    @Autowired
    private HnMonitorService hnMonitorService;
    @Autowired
    private MailUtil mailUtil;

    /**
    * 功能描述： 执行监控
    * @Param:
     * @param hnMonitor
    * @Return: void
    * @Author:
    * @Date: 2022/3/19 18:34
    * @Description:
    */
    @Async("taskExecutor")
    @Transactional
    public void monitor(HnMonitor hnMonitor) {
        HNReq hnReq = new HNReq();
        hnReq.setToken(cacheUtil.getHNToken(hnMonitor.getMonitorPhone()));
        hnReq.setGoodsId(hnMonitor.getMonitorGoodsId());
        logger.debug(hnMonitor.getMonitorGoodsName() + " 检查库存");
        // 查询库存
        HNRsp<GoodsDetailRsp> goodsDetailRsp =  rsHaiNanService.findGoodsDetailByIdAlways(hnReq);
        logger.debug(hnMonitor.getMonitorGoodsName() + " 库存:"+goodsDetailRsp.getData().getCount());
        if (!rsHaiNanService.checkError(goodsDetailRsp)) {
            cacheUtil.hnReLogin(hnMonitor.getMonitorPhone());
            return;
        }
        Double labelDiscount = ObjectUtil.getLowestDiscount(goodsDetailRsp.getData().getActivityLabels());
        // 如果监测到库存进行下单
        if (goodsDetailRsp.getData().getCount() > 0 && hnMonitor.getDiscount() >= labelDiscount) {
            logger.debug(hnMonitor.getMonitorGoodsName() + " 存在库存，开始下单");
            mailUtil.sendSimpleMail(hnMonitor.getMonitorGoodsName() + "已有货，注意下单情况");
            hnMonitor.setStatus("1");
            hnMonitorService.updateById(hnMonitor);
            // 下单
            try {
                // 登录账号
                hnReq.setToken(cacheUtil.getHNToken(hnMonitor.getPlacedPhone()));
                PlaceOrderReq placeOrderReq = PlaceOrderReq.builder().build();
                BeanUtils.copyProperties(hnReq,placeOrderReq);
                // 下单数量
                Integer placedNum = Integer.valueOf(hnMonitor.getPlacedNum());
                placeOrderReq.setCount(placedNum);
                // 一单数量
                Integer placedOnceNum = Integer.valueOf(hnMonitor.getPlacedOnceNum());
                for (int i = 0; i < placedNum / placedOnceNum; i++) {
                    rsHaiNanService.placeOrder(placeOrderReq);
                    // 等待五秒在下单
                    Thread.sleep(5000);
                }
                hnMonitor.setMonitorStatus("2");
                hnMonitorService.updateById(hnMonitor);
                logger.debug(hnMonitor.getMonitorGoodsName() + " 下单成功");
                // 通知
                mailUtil.sendSimpleMail(hnMonitor.getMonitorGoodsName() + "已成功下单，请检查！");
            } catch (Exception e){
                hnMonitor.setMonitorStatus("3");
                hnMonitorService.updateById(hnMonitor);
                mailUtil.sendSimpleMail(hnMonitor.getMonitorGoodsName() + "下单失败");
                logger.info(hnMonitor.getMonitorGoodsName() + "下单失败",e);
            }
        }
    }
}
