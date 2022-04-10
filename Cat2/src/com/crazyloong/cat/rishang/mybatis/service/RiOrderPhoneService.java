package com.crazyloong.cat.rishang.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone;

/**
 * 日上APP下单用户表(RiOrderPhone)表服务接口
 *
 * @author makejava
 * @since 2022-01-10 22:30:57
 */
public interface RiOrderPhoneService extends IService<RiOrderPhone> {

    /**
    * 功能描述：根据手机号获取手机对象
    * @Param:
    * @Return: com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone
    * @Author:
    * @Date: 2022/3/19 18:47
    * @Description:
    */
    RiOrderPhone getByPhone(String phone);
}

