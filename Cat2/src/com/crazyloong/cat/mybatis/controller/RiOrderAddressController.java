package com.crazyloong.cat.mybatis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.crazyloong.cat.mybatis.entity.RiOrderAddress;
import com.crazyloong.cat.mybatis.service.RiOrderAddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 日上app下单地址(RiOrderAddress)表控制层
 *
 * @author makejava
 * @since 2022-01-10 22:30:52
 */
@RestController
@RequestMapping("riOrderAddress")
public class RiOrderAddressController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private RiOrderAddressService riOrderAddressService;

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param riOrderAddress 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public R selectAll(Page<RiOrderAddress> page, RiOrderAddress riOrderAddress) {
        return success(this.riOrderAddressService.page(page, new QueryWrapper<>(riOrderAddress)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.riOrderAddressService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param riOrderAddress 实体对象
     * @return 新增结果
     */
    @PostMapping("/insert")
    public R insert(@RequestBody RiOrderAddress riOrderAddress) {
        return success(this.riOrderAddressService.save(riOrderAddress));
    }

    /**
     * 修改数据
     *
     * @param riOrderAddress 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public R update(@RequestBody RiOrderAddress riOrderAddress) {
        return success(this.riOrderAddressService.updateById(riOrderAddress));
    }

    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public R delete(@RequestParam("id") String id) {
        return success(this.riOrderAddressService.removeById(id));
    }
}

