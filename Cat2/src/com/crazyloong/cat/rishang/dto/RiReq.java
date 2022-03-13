package com.crazyloong.cat.rishang.dto;

import lombok.Data;

import java.util.List;

/**
 * @author YPLI
 * @description
 * @date 2022/1/13 0013 23:40
 **/
@Data
public class RiReq {
    String token;
    List<String> phoneList;
    String password;
}
