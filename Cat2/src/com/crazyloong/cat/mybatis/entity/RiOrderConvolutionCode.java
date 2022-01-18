package com.crazyloong.cat.mybatis.entity;

import java.time.LocalTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 日上app下单券码(RiOrderConvolutionCode)表实体类
 *
 * @author makejava
 * @since 2022-01-10 22:30:56
 */
@Data
public class RiOrderConvolutionCode extends Model<RiOrderConvolutionCode> {
    //主键
    private String id;
    //券码
    private String code;
    //是否可用 0-是 1-否
    private Integer isInuse;
    //使用状态 0-未使用 1-已使用
    private Integer isUsed;
    //登记时间
    @TableField(fill = FieldFill.INSERT)
    private String inputTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;



    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

