package com.crazyloong.cat.rshainan.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 日上海南监控(HnMonitor)表实体类
 *
 * @author makejava
 * @since 2022-03-17 21:44:39
 */
@Data
@SuppressWarnings("serial")
public class HnMonitor extends Model<HnMonitor> {
    //主键
    private String id;
    //监控账号
    private String monitorPhone;
    //下单用户
    private String placedPhone;
    //下单地址
    private String placedAddressId;
    //下单数量
    private String placedNum;
    //一单数量
    private String placedOnceNum;
    //下单商品ID
    private String monitorGoodsId;
    //下单商品ID
    private String monitorGoodsName;
    //生效状态 0-生效 1-未生效
    private String status;
    //监控状态 0-待监控 1-正在监控 2-下单成功 3-下单失败
    private String monitorStatus;
    //登记时间
    @TableField(fill = FieldFill.INSERT)
    private String inputTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
    //备注
    private String remark;
    // 下单折扣价
    private Double discount;


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

