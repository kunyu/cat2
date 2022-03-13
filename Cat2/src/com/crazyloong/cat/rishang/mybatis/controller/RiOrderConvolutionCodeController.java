package com.crazyloong.cat.rishang.mybatis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crazyloong.cat.api.ApiController;
import com.crazyloong.cat.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyloong.cat.rishang.dto.ConvolutionReq;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderConvolutionCode;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderConvolutionCodeService;
import com.mysql.jdbc.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 日上app下单券码(RiOrderConvolutionCode)表控制层
 *
 * @author makejava
 * @since 2022-01-10 22:30:56
 */
@RestController
@RequestMapping("riOrderConvolutionCode")
public class RiOrderConvolutionCodeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private RiOrderConvolutionCodeService riOrderConvolutionCodeService;

    /**
     * 分页查询所有数据
     *
     * @param page                   分页对象
     * @param riOrderConvolutionCode 查询实体
     * @return 所有数据
     */
    @GetMapping("/selectAll")
    public R selectAll(Page<RiOrderConvolutionCode> page, RiOrderConvolutionCode riOrderConvolutionCode) {
        return success(this.riOrderConvolutionCodeService.page(page, new QueryWrapper<>(riOrderConvolutionCode)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("get/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.riOrderConvolutionCodeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param riOrderConvolutionCode 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    public R insert(@RequestBody RiOrderConvolutionCode riOrderConvolutionCode) {
        return success(this.riOrderConvolutionCodeService.save(riOrderConvolutionCode));
    }

    /**
     * 新增数据
     *
     * @param convolutionReq 实体对象
     * @return 新增结果
     */
    @PostMapping("insertBatch")
    public R insertBatch(@RequestBody ConvolutionReq convolutionReq) {
        String codeString = convolutionReq.getCodeString();
        if (codeString == null || "".equals(codeString)) {
            return success("无数据可更新");
        }
        List<String> codeList = Arrays.asList(convolutionReq.getCodeString().split("\n"));

        for(String code: codeList){
            if (!StringUtils.isNullOrEmpty(code)) {
                RiOrderConvolutionCode riOrderConvolutionCode = new RiOrderConvolutionCode();
                riOrderConvolutionCode.setCode(code.trim());
                this.riOrderConvolutionCodeService.save(riOrderConvolutionCode);
            }

        }
        return success("操作成功");
    }

    /**
     * 修改数据
     *
     * @param riOrderConvolutionCode 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public R update(@RequestBody RiOrderConvolutionCode riOrderConvolutionCode) {
        return success(this.riOrderConvolutionCodeService.updateById(riOrderConvolutionCode));
    }

    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping("delete")
    public R delete(@RequestParam("id") String id) {
        return success(this.riOrderConvolutionCodeService.removeById(id));
    }
}

