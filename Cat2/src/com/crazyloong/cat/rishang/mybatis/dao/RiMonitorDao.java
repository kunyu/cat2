package com.crazyloong.cat.rishang.mybatis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crazyloong.cat.rishang.mybatis.entity.RiMonitor;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日上监控(RiMonitor)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-11 20:36:04
 */
@Mapper
public interface RiMonitorDao extends BaseMapper<RiMonitor> {

}

