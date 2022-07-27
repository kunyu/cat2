package com.crazyloong.cat.rishang.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 日上APP下单用户表(RiOrderPhone)表实体类
 *
 * @author makejava
 * @since 2022-01-10 22:30:57
 */
@Data
@TableName("ri_order_phone")
public class RiOrderPhone extends Model<RiOrderPhone> {
    //主键
    @TableId
    private String id;
    //用户手机号
    private String phone;
    //用户密码
    private String password;
    //登记时间
    @TableField(fill = FieldFill.INSERT)
    private String inputTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
    //备注
    private String remark;
    // 账号类型
    private String type;
    // 是否为新账号 0-新 1-旧
    private Integer isNew;


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

