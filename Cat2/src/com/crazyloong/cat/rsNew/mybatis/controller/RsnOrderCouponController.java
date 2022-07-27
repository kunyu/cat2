package com.crazyloong.cat.rsNew.mybatis.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderCoupon;
import com.crazyloong.cat.rsNew.mybatis.service.RsnOrderCouponService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 中免日上优惠券(RsnOrderCoupon)表控制层
 *
 * @author makejava
 * @since 2022-06-29 18:34:11
 */
@RestController
@RequestMapping("rsnOrderCoupon")
public class RsnOrderCouponController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private RsnOrderCouponService rsnOrderCouponService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param rsnOrderCoupon 查询实体
     * @return 所有数据
     */
    @GetMapping("selectAll")
    public R selectAll(Page<RsnOrderCoupon> page, RsnOrderCoupon rsnOrderCoupon) {
        return success(this.rsnOrderCouponService.page(page, new QueryWrapper<>(rsnOrderCoupon)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.rsnOrderCouponService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param rsnOrderCoupon 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody RsnOrderCoupon rsnOrderCoupon) {
        return success(this.rsnOrderCouponService.save(rsnOrderCoupon));
    }

    /**
     * 修改数据
     *
     * @param rsnOrderCoupon 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody RsnOrderCoupon rsnOrderCoupon) {
        return success(this.rsnOrderCouponService.updateById(rsnOrderCoupon));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.rsnOrderCouponService.removeByIds(idList));
    }
}

