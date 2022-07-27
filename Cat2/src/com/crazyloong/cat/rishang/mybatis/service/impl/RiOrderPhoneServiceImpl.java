package com.crazyloong.cat.rishang.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crazyloong.cat.rishang.mybatis.dao.RiOrderPhoneDao;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderPhoneService;
import com.crazyloong.cat.rsNew.constant.PhoneFlag;
import com.crazyloong.cat.rsNew.dto.DrawCouponReq;
import com.crazyloong.cat.rsNew.mybatis.service.RsnOrderPlacedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 日上APP下单用户表(RiOrderPhone)表服务实现类
 *
 * @author makejava
 * @since 2022-01-10 22:30:57
 */
@Service
public class RiOrderPhoneServiceImpl extends ServiceImpl<RiOrderPhoneDao, RiOrderPhone> implements RiOrderPhoneService {

    @Autowired
    private RsnOrderPlacedService orderPlacedService;

    /**
     * 功能描述：根据手机号获取手机对象
     * @Param:
     * @Return: com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone
     * @Author:
     * @Date: 2022/3/19 18:47
     * @Description:
     */
    @Override
    public RiOrderPhone getByPhone(String phone){
        QueryWrapper<RiOrderPhone> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("PHONE", phone);
        return this.getOne(queryWrapper);
    }

    /**
     * 功能描述：获取手机号集合
     * @param type
     * @Return: java.util.List<com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone>
     * @Author:
     * @Date: 2022/6/17 18:35
     * @Description:
     */
    public List<RiOrderPhone> getPhonesUnPlacedToday(String type) {
        List<RiOrderPhone> orderPhoneList = this.getPhonesByType(type);
        List<String> placedPhone = orderPlacedService.getPlacedPhoneToday();
        List<String> allPhone = orderPhoneList.stream().map(orderPhone -> orderPhone.getPhone()).collect(Collectors.toList());
        allPhone.removeAll(placedPhone);

        List<RiOrderPhone> unPlacedPhone = new ArrayList<>();
        for(RiOrderPhone orderPhone: orderPhoneList) {
            if (allPhone.contains(orderPhone.getPhone())) {
                unPlacedPhone.add(orderPhone);
            }
        }
        return unPlacedPhone;
    }


    /**
     * 功能描述：获取手机号集合
     * @param type
     * @Return: java.util.List<com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone>
     * @Author:
     * @Date: 2022/6/17 18:35
     * @Description:
     */
    public List<RiOrderPhone> getPhonesByType(String type) {
        QueryWrapper<RiOrderPhone> wrapper = new QueryWrapper<>();
        wrapper.like("type",type);
        return this.list(wrapper);
    }

    /**
    * 功能描述：获取手机号集合
    * @Param: [drawCouponReq]
    * @Return: java.util.List<com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone>
    * @Author:
    * @Date: 2022/7/26 23:48
    * @Description:
    */
    public List<RiOrderPhone> getPhones(DrawCouponReq drawCouponReq) {
        QueryWrapper<RiOrderPhone> wrapper = new QueryWrapper<>();
        wrapper.like("type", PhoneFlag.RS_NEW.code);
        if (drawCouponReq.getNewFlag()!= null && drawCouponReq.getNewFlag() ) {
            wrapper.eq("is_new", 0);
        } else if (drawCouponReq.getNewFlag()!= null ) {
            wrapper.eq("is_new", 1);
        }
        if (drawCouponReq.getLimitNum() != null) {
            wrapper.last("limit "+drawCouponReq.getLimitNum());
        }

        return this.list(wrapper);
    }

}

