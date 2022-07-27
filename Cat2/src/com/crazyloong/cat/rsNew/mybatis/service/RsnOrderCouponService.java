package com.crazyloong.cat.rsNew.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderCoupon;

import java.util.List;

/**
 * 中免日上优惠券(RsnOrderCoupon)表服务接口
 *
 * @author makejava
 * @since 2022-06-29 18:34:13
 */
public interface RsnOrderCouponService extends IService<RsnOrderCoupon> {

    /**
    * 功能描述：根据手机号删除优惠券
    * @Param: [phone]
    * @Return: java.lang.Boolean
    * @Author:
    * @Date: 2022/6/29 18:35
    * @Description:
    */
    Boolean deleteByPhone(String phone);

    /**
    * 功能描述： 根据手机号获取优惠券列表
    * @Param: [phone]
    * @Return: java.util.List<com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderCoupon>
    * @Author:
    * @Date: 2022/6/29 18:45
    * @Description:
    */
    List<RsnOrderCoupon> getByPhone(String phone);

    /**
     * 功能描述： 根据优惠券编号更新不可用
     * @Param: [phone]
     * @Return: java.util.List<com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderCoupon>
     * @Author:
     * @Date: 2022/6/29 18:45
     * @Description:
     */
    Boolean updateUnused(String codeId);
}

