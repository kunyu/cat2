package com.crazyloong.cat.mybatis.entity;

import java.time.LocalTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 日上app下单地址(RiOrderAddress)表实体类
 *
 * @author makejava
 * @since 2022-01-10 22:30:54
 */
@Data
public class RiOrderAddress extends Model<RiOrderAddress> {
    //主键
    private String id;
    //姓名
    private String userName;
    //电话
    private String userPhone;
    //下单地址
    private String address;
    //登记时间
    @TableField(fill = FieldFill.INSERT)
    private String inputTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
    //备注
    private String remark;

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

