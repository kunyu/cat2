package com.crazyloong.cat.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crazyloong.cat.mybatis.entity.RiOrderConvolutionCode;

import java.util.List;

/**
 * 日上app下单券码(RiOrderConvolutionCode)表服务接口
 *
 * @author makejava
 * @since 2022-01-10 22:30:57
 */
public interface RiOrderConvolutionCodeService extends IService<RiOrderConvolutionCode> {

    List<RiOrderConvolutionCode> listCodes(RiOrderConvolutionCode code);

}

