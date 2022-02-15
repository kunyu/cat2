package com.crazyloong.cat.rishang.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crazyloong.cat.rishang.mybatis.dao.RiOrderPlacedDao;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPlaced;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderPlacedService;
import org.springframework.stereotype.Service;

/**
 * 日上app下单成功信息(RiOrderPlaced)表服务实现类
 *
 * @author makejava
 * @since 2022-01-16 15:46:37
 */
@Service("riOrderPlacedService")
public class RiOrderPlacedServiceImpl extends ServiceImpl<RiOrderPlacedDao, RiOrderPlaced> implements RiOrderPlacedService {

}

