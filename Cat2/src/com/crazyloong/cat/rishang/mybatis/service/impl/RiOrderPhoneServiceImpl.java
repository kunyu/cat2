package com.crazyloong.cat.rishang.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crazyloong.cat.rishang.mybatis.dao.RiOrderPhoneDao;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderPhoneService;
import org.springframework.stereotype.Service;

/**
 * 日上APP下单用户表(RiOrderPhone)表服务实现类
 *
 * @author makejava
 * @since 2022-01-10 22:30:57
 */
@Service
public class RiOrderPhoneServiceImpl extends ServiceImpl<RiOrderPhoneDao, RiOrderPhone> implements RiOrderPhoneService {

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
}

