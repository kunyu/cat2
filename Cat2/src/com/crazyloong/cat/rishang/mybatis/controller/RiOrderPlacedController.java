package com.crazyloong.cat.rishang.mybatis.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPlaced;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderPlacedService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 日上app下单成功信息(RiOrderPlaced)表控制层
 *
 * @author makejava
 * @since 2022-01-16 15:46:37
 */
@RestController
@RequestMapping("riOrderPlaced")
public class RiOrderPlacedController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private RiOrderPlacedService riOrderPlacedService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param riOrderPlaced 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public R selectAll(Page<RiOrderPlaced> page, RiOrderPlaced riOrderPlaced) {
        return success(this.riOrderPlacedService.page(page, new QueryWrapper<>(riOrderPlaced)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.riOrderPlacedService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param riOrderPlaced 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody RiOrderPlaced riOrderPlaced) {
        return success(this.riOrderPlacedService.save(riOrderPlaced));
    }

    /**
     * 修改数据
     *
     * @param riOrderPlaced 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody RiOrderPlaced riOrderPlaced) {
        return success(this.riOrderPlacedService.updateById(riOrderPlaced));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.riOrderPlacedService.removeByIds(idList));
    }
}

