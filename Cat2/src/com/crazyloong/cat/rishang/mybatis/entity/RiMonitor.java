package com.crazyloong.cat.rishang.mybatis.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 日上监控(RiMonitor)表实体类
 *
 * @author makejava
 * @since 2022-04-11 20:36:04
 */
@SuppressWarnings("serial")
@Data
public class RiMonitor extends Model<RiMonitor> {
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
    //生效状态 0-生效 1-未生效
    private String status;
    //监控状态 0-待监控 1-正在监控 2-下单成功 3-下单失败
    private String monitorStatus;
    //登记时间
    private String inputTime;
    //更新时间
    private String updateTime;
    //备注
    private String remark;
    //产品名称
    private String monitorGoodsName;
    //下单折扣价
    private BigDecimal discount;


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

