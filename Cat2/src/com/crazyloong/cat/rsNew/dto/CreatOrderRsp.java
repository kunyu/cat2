package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/19 21:10
 * @Description :
 */
@NoArgsConstructor
@Data
public class CreatOrderRsp {

    private ResponseHeadDTO responseHead;
    private String orderID;
    private List<String> subOrderID;
    private String code;

    @NoArgsConstructor
    @Data
    public static class ResponseHeadDTO {
        private Boolean isSuccess;
        private String resultMessage;
        private String latestVersion;
        private Integer code;
        private String traceId;
    }
}
