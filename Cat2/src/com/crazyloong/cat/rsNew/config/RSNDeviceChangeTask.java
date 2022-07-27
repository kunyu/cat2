package com.crazyloong.cat.rsNew.config;

import com.crazyloong.cat.rsNew.service.RSNewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @Author : crazyloongcat
 * @Date :2022/6/18 16:04
 * @Description :
 */
@Component
public class RSNDeviceChangeTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RSNewService rsNewService;

    // 每隔五分钟更换一次deviceID
    //@Scheduled(fixedRate = 1000*60*5)
    public void hnExecute(){
        logger.info("开始更换DeviceId");
        rsNewService.deviceLogin();
    }
}
