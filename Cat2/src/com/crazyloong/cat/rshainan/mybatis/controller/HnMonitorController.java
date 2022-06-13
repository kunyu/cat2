package com.crazyloong.cat.rshainan.mybatis.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.crazyloong.cat.rshainan.mybatis.entity.HnMonitor;
import com.crazyloong.cat.rshainan.mybatis.service.HnMonitorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 日上海南监控(HnMonitor)表控制层
 *
 * @author makejava
 * @since 2022-03-17 21:44:37
 */
@RestController
@RequestMapping("hnMonitor")
public class HnMonitorController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private HnMonitorService hnMonitorService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param hnMonitor 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public R selectAll(Page<HnMonitor> page, HnMonitor hnMonitor) {
        QueryWrapper wrapper = new QueryWrapper();
        if (hnMonitor.getMonitorGoodsName() != null) {
            wrapper.like("monitor_Goods_Name",hnMonitor.getMonitorGoodsName());
        }
        if (hnMonitor.getMonitorPhone() != null) {
            wrapper.likeRight("monitor_Phone",hnMonitor.getMonitorPhone());
        }
        if (hnMonitor.getPlacedPhone() != null) {
            wrapper.likeRight("placed_Phone",hnMonitor.getPlacedPhone());
        }
        if (hnMonitor.getStatus() != null) {
            wrapper.likeRight("status",hnMonitor.getStatus());
        }
        return success(this.hnMonitorService.page(page, wrapper));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.hnMonitorService.getById(id));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/getByGoodsId/{id}")
    public R selectByGoodsId(@PathVariable String id) {
        return success(this.hnMonitorService.getByGoodsId(id));
    }

    /**
     * 新增数据
     *
     * @param hnMonitor 实体对象
     * @return 新增结果
     */
    @PostMapping("/insertOrUpdate")
    public R insertOrUpdate(@RequestBody HnMonitor hnMonitor) {
        HnMonitor goodsHn = hnMonitorService.getByGoodsId(hnMonitor.getMonitorGoodsId());
        if (goodsHn != null) {
            hnMonitor.setId(goodsHn.getId());
            return success(this.hnMonitorService.updateById(hnMonitor));
        }
        return success(this.hnMonitorService.save(hnMonitor));
    }

    /**
     * 新增数据
     *
     * @param hnMonitor 实体对象
     * @return 新增结果
     */
    @PostMapping("/updateAll")
    public R updateAll(@RequestBody HnMonitor hnMonitor) {
        return success(this.hnMonitorService.updateAll(hnMonitor));
    }

    /**
     * 修改数据
     *
     * @param hnMonitor 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody HnMonitor hnMonitor) {
        return success(this.hnMonitorService.updateById(hnMonitor));
    }



    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public R delete(@RequestParam("id") String id) {
        return success(this.hnMonitorService.removeById(id));
    }
}

