package com.crazyloong.cat.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 日上app下单成功信息(RiOrderPlaced)表实体类
 *
 * @author makejava
 * @since 2022-01-16 15:46:37
 */
@Data
public class RiOrderPlaced extends Model<RiOrderPlaced> {
    //订单编号
    private String orderCode;
    //生成订单用户
    private String orderUser;
    //姓名
    private String addressName;
    //电话
    private String addressPhone;
    //地址
    private String address;
    //订单日期
    @TableField(fill = FieldFill.INSERT)
    private String inputDate;
    //订单时间
    @TableField(fill = FieldFill.INSERT)
    private String inputTime;
    //商品名称
    private String goodsName;
    //商品数量
    private String goodsNum;
    //订单实付
    private String goodsPrice;
    //订单应付
    private String goodsOprice;

}

