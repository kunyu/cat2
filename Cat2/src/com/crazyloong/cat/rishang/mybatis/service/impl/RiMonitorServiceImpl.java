package com.crazyloong.cat.rishang.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crazyloong.cat.rishang.mybatis.dao.RiMonitorDao;
import com.crazyloong.cat.rishang.mybatis.entity.RiMonitor;
import com.crazyloong.cat.rishang.mybatis.service.RiMonitorService;
import org.springframework.stereotype.Service;

/**
 * 日上监控(RiMonitor)表服务实现类
 *
 * @author makejava
 * @since 2022-04-11 20:36:04
 */
@Service("riMonitorService")
public class RiMonitorServiceImpl extends ServiceImpl<RiMonitorDao, RiMonitor> implements RiMonitorService {

}

