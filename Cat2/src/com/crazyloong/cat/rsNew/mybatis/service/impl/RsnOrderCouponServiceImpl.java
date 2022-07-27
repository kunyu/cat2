package com.crazyloong.cat.rsNew.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crazyloong.cat.rsNew.mybatis.dao.RsnOrderCouponDao;
import com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderCoupon;
import com.crazyloong.cat.rsNew.mybatis.service.RsnOrderCouponService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 中免日上优惠券(RsnOrderCoupon)表服务实现类
 *
 * @author makejava
 * @since 2022-06-29 18:34:13
 */
@Service("rsnOrderCouponService")
public class RsnOrderCouponServiceImpl extends ServiceImpl<RsnOrderCouponDao, RsnOrderCoupon> implements RsnOrderCouponService {


    /**
     * 功能描述：根据手机号删除优惠券
     * @Param: [phone]
     * @Return: java.lang.Boolean
     * @Author:
     * @Date: 2022/6/29 18:35
     * @Description:
     */
    public Boolean deleteByPhone(String phone) {
        QueryWrapper<RsnOrderCoupon> wrapper = new QueryWrapper<>();
        wrapper.eq("code_phone",phone);
        this.remove(wrapper);
        return true;
    }

    /**
     * 功能描述： 根据手机号获取优惠券列表
     * @Param: [phone]
     * @Return: java.util.List<com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderCoupon>
     * @Author:
     * @Date: 2022/6/29 18:45
     * @Description:
     */
    public List<RsnOrderCoupon> getByPhone(String phone) {
        QueryWrapper<RsnOrderCoupon> wrapper = new QueryWrapper<>();
        wrapper.eq("code_phone",phone);
        wrapper.eq("is_inuse","0");
        return this.list(wrapper);
    }

    /**
     * 功能描述： 根据优惠券编号更新不可用
     * @Param: [phone]
     * @Return: java.util.List<com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderCoupon>
     * @Author:
     * @Date: 2022/6/29 18:45
     * @Description:
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean updateUnused(String codeId) {
        UpdateWrapper<RsnOrderCoupon> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_inuse","1");
        updateWrapper.eq("codeID",codeId);
        return this.update(updateWrapper);
    }
}

