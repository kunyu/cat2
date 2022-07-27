package com.crazyloong.cat.rsNew.mybatis.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderPlaced;
import com.crazyloong.cat.rsNew.mybatis.service.RsnOrderPlacedService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 中免日上以下单记录(RsnOrderPlaced)表控制层
 *
 * @author makejava
 * @since 2022-06-29 18:34:07
 */
@RestController
@RequestMapping("rsnOrderPlaced")
public class RsnOrderPlacedController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private RsnOrderPlacedService rsnOrderPlacedService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param rsnOrderPlaced 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<RsnOrderPlaced> page, RsnOrderPlaced rsnOrderPlaced) {
        return success(this.rsnOrderPlacedService.page(page, new QueryWrapper<>(rsnOrderPlaced)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.rsnOrderPlacedService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param rsnOrderPlaced 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody RsnOrderPlaced rsnOrderPlaced) {
        return success(this.rsnOrderPlacedService.save(rsnOrderPlaced));
    }

    /**
     * 修改数据
     *
     * @param rsnOrderPlaced 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody RsnOrderPlaced rsnOrderPlaced) {
        return success(this.rsnOrderPlacedService.updateById(rsnOrderPlaced));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.rsnOrderPlacedService.removeByIds(idList));
    }
}

