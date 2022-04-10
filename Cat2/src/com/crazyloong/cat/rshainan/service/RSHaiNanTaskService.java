package com.crazyloong.cat.rshainan.service;

import com.crazyloong.cat.rshainan.mybatis.entity.HnMonitor;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/19 16:10
 * @Description : 海南定时任务
 */
public interface RSHaiNanTaskService {

    /**
     * 功能描述： 执行监控
     * @Param:
     * @param hnMonitor
     * @Return: void
     * @Author:
     * @Date: 2022/3/19 18:34
     * @Description:
     */
    void monitor(HnMonitor hnMonitor);
}
