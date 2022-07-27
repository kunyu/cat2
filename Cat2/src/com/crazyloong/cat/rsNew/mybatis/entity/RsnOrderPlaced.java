package com.crazyloong.cat.rsNew.mybatis.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 中免日上以下单记录(RsnOrderPlaced)表实体类
 *
 * @author makejava
 * @since 2022-06-29 18:34:09
 */
@Data
public class RsnOrderPlaced extends Model<RsnOrderPlaced> {
    //主键
    private String id;
    //下单用户
    private String placedPhone;
    //下单状态 0-成功 1-失败
    private Integer status;
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

