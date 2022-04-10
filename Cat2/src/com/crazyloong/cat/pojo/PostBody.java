package com.crazyloong.cat.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
@NoArgsConstructor
public class PostBody<T> {

    // 调用接口
    private String API;
    // 地址
    private String host;
    // 认证
    private String authorization;
    // API传递参数
    private T paramters;
    // 邮寄人名
    private String name;
    // 邮寄电话
    private String tel;
    // 邮寄地址
    private String address;
    // 购买商品
    private Map<String, Integer> goods;

    private String stockId;
}
