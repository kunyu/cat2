package com.crazyloong.cat.rishang.dto;

import lombok.Data;

/**
 * @author YPLI
 * @description
 * @date 2022/1/13 0013 23:39
 **/
@Data
public class WishReq extends RiReq{
    /** 0-未提交 1-已提交 2-已完成**/
    Integer type;
}
