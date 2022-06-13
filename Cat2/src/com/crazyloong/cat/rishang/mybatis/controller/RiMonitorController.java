package com.crazyloong.cat.rishang.mybatis.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyloong.cat.rishang.mybatis.entity.RiMonitor;
import com.crazyloong.cat.rishang.mybatis.service.RiMonitorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 日上监控(RiMonitor)表控制层
 *
 * @author makejava
 * @since 2022-04-11 20:36:04
 */
@RestController
@RequestMapping("riMonitor")
public class RiMonitorController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private RiMonitorService riMonitorService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param riMonitor 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<RiMonitor> page, RiMonitor riMonitor) {
        return success(this.riMonitorService.page(page, new QueryWrapper<>(riMonitor)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.riMonitorService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param riMonitor 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody RiMonitor riMonitor) {
        return success(this.riMonitorService.save(riMonitor));
    }

    /**
     * 修改数据
     *
     * @param riMonitor 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody RiMonitor riMonitor) {
        return success(this.riMonitorService.updateById(riMonitor));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.riMonitorService.removeByIds(idList));
    }
}

