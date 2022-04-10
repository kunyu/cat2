package com.crazyloong.cat.rshainan.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crazyloong.cat.rishang.mybatis.dao.HnMonitorDao;
import com.crazyloong.cat.rshainan.mybatis.entity.HnMonitor;
import com.crazyloong.cat.rshainan.mybatis.service.HnMonitorService;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 日上海南监控(HnMonitor)表服务实现类
 *
 * @author makejava
 * @since 2022-03-17 21:44:40
 */
@Service("hnMonitorService")
public class HnMonitorServiceImpl extends ServiceImpl<HnMonitorDao, HnMonitor> implements HnMonitorService {

    /**
     * 功能描述： 获取生效的监控任务
     * @Param:
     * @Return: com.crazyloong.cat.rshainan.mybatis.entity.HnMonitor
     * @Author:
     * @Date: 2022/3/19 18:43
     * @Description:
     */
    @Override
    public List<HnMonitor> getEffect(){
        QueryWrapper<HnMonitor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("STATUS", "0");
        return this.list(queryWrapper);
    }

    @Override
    public HnMonitor getByGoodsId(String goodsId) {
        QueryWrapper<HnMonitor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("MONITOR_GOODS_ID", goodsId);
        return this.getOne(queryWrapper);
    }

    /**
    * 功能描述：更新监控账号
    * @Param:
     * @param hnMonitor
    * @Return: java.lang.Boolean
    * @Author:
    * @Date: 2022/4/3 22:14
    * @Description:
    */
    @Override
    public Boolean updateAll(HnMonitor hnMonitor) {
        UpdateWrapper<HnMonitor> updateWrapper = new UpdateWrapper();
        if (!StringUtils.isNullOrEmpty(hnMonitor.getMonitorPhone())) {
            updateWrapper.set("monitor_phone",hnMonitor.getMonitorPhone());
        }
        if (!StringUtils.isNullOrEmpty(hnMonitor.getPlacedPhone())) {
            updateWrapper.set("placed_phone",hnMonitor.getPlacedPhone());
        }
        return this.update(updateWrapper);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public boolean myUpdateById(HnMonitor hnMonitor){
        return this.updateById(hnMonitor);
    }
}

