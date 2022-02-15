package com.crazyloong.cat.rishang.mybatis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日上app下单地址(RiOrderAddress)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-10 22:30:54
 */
@Mapper
public interface RiOrderAddressDao extends BaseMapper<RiOrderAddress> {

}

