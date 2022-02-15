package com.crazyloong.cat.rishang.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crazyloong.cat.rishang.mybatis.dao.RiOrderConvolutionCodeDao;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderConvolutionCode;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderConvolutionCodeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日上app下单券码(RiOrderConvolutionCode)表服务实现类
 *
 * @author makejava
 * @since 2022-01-10 22:30:57
 */
@Service
public class RiOrderConvolutionCodeServiceImpl extends ServiceImpl<RiOrderConvolutionCodeDao, RiOrderConvolutionCode> implements RiOrderConvolutionCodeService {
    @Override
    public List<RiOrderConvolutionCode> listCodes(RiOrderConvolutionCode code){
        QueryWrapper<RiOrderConvolutionCode> wrapper = new QueryWrapper<RiOrderConvolutionCode>();
        wrapper.eq("is_inuse",code.getIsInuse());
        wrapper.eq("is_used",code.getIsUsed());
        return this.list(wrapper);
    }
}

