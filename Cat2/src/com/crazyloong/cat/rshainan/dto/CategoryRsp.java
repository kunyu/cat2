package com.crazyloong.cat.rshainan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/13 21:50
 * @Description :
 */
@NoArgsConstructor
@Data
public class CategoryRsp {
    private String id;
    private String categoryId;
    private String cnName;
    private String enName;
    private String aliasName;
    private String parentCategoryId;
    private Integer closed;
    private Integer level;
    private Integer index;
    private String smallImg;
    private Integer idType;
    private String idValue;
    private Boolean newX;
}
