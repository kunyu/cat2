package com.crazyloong.cat.rishang.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderConvolutionCode;

import java.util.List;

/**
 * 日上app下单券码(RiOrderConvolutionCode)表服务接口
 *
 * @author makejava
 * @since 2022-01-10 22:30:57
 */
public interface RiOrderConvolutionCodeService extends IService<RiOrderConvolutionCode> {

    /**
     * 根据优惠券类型获取优惠券
     * @param code
     * @return
     */
    List<RiOrderConvolutionCode> listCodes(RiOrderConvolutionCode code);

}

