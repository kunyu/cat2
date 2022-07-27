package com.crazyloong.cat.rsNew.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderPlaced;

import java.util.List;

/**
 * 中免日上以下单记录(RsnOrderPlaced)表服务接口
 *
 * @author makejava
 * @since 2022-06-29 18:34:10
 */
public interface RsnOrderPlacedService extends IService<RsnOrderPlaced> {

    /**
    * 功能描述：获取当日已下单用户
    * @Param: []
    * @Return: java.util.List<java.lang.String>
    * @Author:
    * @Date: 2022/7/24 14:44
    * @Description:
    */
    List<String> getPlacedPhoneToday();

}

