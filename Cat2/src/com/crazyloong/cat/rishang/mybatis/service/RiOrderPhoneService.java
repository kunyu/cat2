package com.crazyloong.cat.rishang.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone;
import com.crazyloong.cat.rsNew.dto.DrawCouponReq;

import java.util.List;

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

    /**
    * 功能描述：获取手机号集合
    * @param type
    * @Return: java.util.List<com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone>
    * @Author:
    * @Date: 2022/6/17 18:35
    * @Description:
    */
    List<RiOrderPhone> getPhonesUnPlacedToday(String type);

    /**
     * 功能描述：获取手机号集合
     * @param type
     * @Return: java.util.List<com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone>
     * @Author:
     * @Date: 2022/6/17 18:35
     * @Description:
     */
    List<RiOrderPhone> getPhonesByType(String type);

    /**
     * 功能描述：获取手机号集合
     * @Param: [drawCouponReq]
     * @Return: java.util.List<com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone>
     * @Author:
     * @Date: 2022/7/26 23:48
     * @Description:
     */
    List<RiOrderPhone> getPhones(DrawCouponReq drawCouponReq);

}

