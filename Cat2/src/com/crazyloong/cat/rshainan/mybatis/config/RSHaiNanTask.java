package com.crazyloong.cat.rshainan.mybatis.config;

import com.crazyloong.cat.rshainan.mybatis.entity.HnMonitor;
import com.crazyloong.cat.rshainan.mybatis.service.HnMonitorService;
import com.crazyloong.cat.rshainan.service.RSHaiNanTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/19 18:49
 * @Description : 海南监控任务执行类
 */
@Component
public class RSHaiNanTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RSHaiNanTaskService rsHaiNanTaskService;
    @Autowired
    private HnMonitorService hnMonitorService;

    @Scheduled(cron = "*/1 * * * * ?")
    public void hnExecute(){
        logger.info("海南日上监控任务开始执行");
        // 获取生效的监控任务
        List<HnMonitor> monitors = hnMonitorService.getEffect();
        for (int i = 0; i < monitors.size(); i++) {
            HnMonitor monitor = monitors.get(i);
            if (!"2".equals(monitor.getMonitorStatus())) {
                monitor.setMonitorStatus("2");
                hnMonitorService.updateById(monitor);
            }
            rsHaiNanTaskService.monitor(monitor);
        }
    }
}
