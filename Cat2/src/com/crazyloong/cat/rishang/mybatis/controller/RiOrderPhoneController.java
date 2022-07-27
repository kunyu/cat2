package com.crazyloong.cat.rishang.mybatis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyloong.cat.rishang.dto.ConvolutionReq;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderConvolutionCode;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderPhoneService;
import com.mysql.jdbc.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 日上APP下单用户表(RiOrderPhone)表控制层
 *
 * @author makejava
 * @since 2022-01-10 22:30:57
 */
@RestController
@RequestMapping("riOrderPhone")
public class RiOrderPhoneController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private RiOrderPhoneService riOrderPhoneService;

    /**
     * 分页查询所有数据
     *
     * @param page         分页对象
     * @param riOrderPhone 查询实体
     * @return 所有数据
     */
    @GetMapping("selectAll")
    public R selectAll(Page<RiOrderPhone> page, RiOrderPhone riOrderPhone) {
        QueryWrapper wrapper = new QueryWrapper();
        if (riOrderPhone.getRemark() != null) {
            wrapper.like("remark",riOrderPhone.getRemark());
        }
        if (riOrderPhone.getId() != null) {
            wrapper.likeRight("id",riOrderPhone.getId());
        }
        if (riOrderPhone.getPhone() != null) {
            wrapper.likeRight("phone",riOrderPhone.getPhone());
        }
        if (riOrderPhone.getType() != null) {
            wrapper.eq("type",riOrderPhone.getType());
        }
        if (riOrderPhone.getIsNew() != null) {
            wrapper.eq("is_new",riOrderPhone.getIsNew());
        }

        return success(this.riOrderPhoneService.page(page, wrapper));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("get/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.riOrderPhoneService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param riOrderPhone 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    public R insert(@RequestBody RiOrderPhone riOrderPhone) {
        return success(this.riOrderPhoneService.save(riOrderPhone));
    }

    /**
     * 新增数据
     *
     * @param riOrderPhone 实体对象
     * @return 新增结果
     */
    @PostMapping("insertBatch")
    public R insertBatch(@RequestBody RiOrderPhone riOrderPhone) {
        String phones = riOrderPhone.getPhone();
        if (phones == null || "".equals(phones)) {
            return success("无数据可更新");
        }
        List<String> codeList = Arrays.asList(phones.split("\n"));
        for(String code: codeList){
            if (!StringUtils.isNullOrEmpty(code)) {
                RiOrderPhone orderPhone = new RiOrderPhone();
                orderPhone.setPassword(riOrderPhone.getPassword());
                orderPhone.setType(riOrderPhone.getType());
                orderPhone.setRemark(riOrderPhone.getRemark());
                orderPhone.setPhone(code.trim());
                this.riOrderPhoneService.save(orderPhone);
            }

        }
        return success("操作成功");
    }

    /**
     * 修改数据
     *
     * @param riOrderPhone 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public R update(@RequestBody RiOrderPhone riOrderPhone) {
        return success(this.riOrderPhoneService.updateById(riOrderPhone));
    }

    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public R delete(@RequestParam("id") String id) {
        return success(this.riOrderPhoneService.removeById(id));
    }
}

