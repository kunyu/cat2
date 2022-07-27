package com.crazyloong.cat.rsNew.mybatis.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 中免日上优惠券(RsnOrderCoupon)表实体类
 *
 * @author makejava
 * @since 2022-06-29 18:34:12
 */
@Data
public class RsnOrderCoupon extends Model<RsnOrderCoupon> {
    //主键ID
    private String id;
    //优惠券类型 0-用户优惠券 1-公共优惠券
    private Integer type;
    //是否可用 0-可用 1-不可用
    private Integer isInuse;
    //优惠券名称
    private String codename;
    //优惠券ID
    private String codeid;
    //优惠券用户
    private String codePhone;
    @TableField(fill = FieldFill.INSERT)
    private String inputTime;
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

