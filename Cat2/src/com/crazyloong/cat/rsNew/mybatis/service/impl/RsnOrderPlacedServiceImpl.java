package com.crazyloong.cat.rsNew.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone;
import com.crazyloong.cat.rsNew.mybatis.dao.RsnOrderPlacedDao;
import com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderPlaced;
import com.crazyloong.cat.rsNew.mybatis.service.RsnOrderPlacedService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 中免日上以下单记录(RsnOrderPlaced)表服务实现类
 *
 * @author makejava
 * @since 2022-06-29 18:34:10
 */
@Service("rsnOrderPlacedService")
public class RsnOrderPlacedServiceImpl extends ServiceImpl<RsnOrderPlacedDao, RsnOrderPlaced> implements RsnOrderPlacedService {

    private final String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 功能描述：获取当日已下单用户
     * @Param: []
     * @Return: java.util.List<java.lang.String>
     * @Author:
     * @Date: 2022/7/24 14:44
     * @Description:
     */
    public List<String> getPlacedPhoneToday() {
        QueryWrapper<RsnOrderPlaced> queryWrapper = new QueryWrapper<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
        queryWrapper.like("input_time", formatter.format(LocalDate.now()));
        queryWrapper.select("DISTINCT placed_phone");
        return this.listMaps(queryWrapper).stream().map(stringObjectMap -> (String)stringObjectMap.get("placed_phone")).collect(Collectors.toList());
    }
}

