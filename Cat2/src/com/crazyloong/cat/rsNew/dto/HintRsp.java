package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/17 0:02
 * @Description :
 */
@NoArgsConstructor
@Data
public class HintRsp {


    private ResponseHeadDTO responseHead;
    private HintTextDTO hintText;
    private List<HintTextListDTO> hintTextList;

    @NoArgsConstructor
    @Data
    public static class ResponseHeadDTO {
        private Boolean isSuccess;
        private String resultMessage;
        private String latestVersion;
        private Integer code;
        private String traceId;
    }

    @NoArgsConstructor
    @Data
    public static class HintTextDTO {
        private String text;
        private String linkUrl;
        private String wxLinkUrl;
    }

    @NoArgsConstructor
    @Data
    public static class HintTextListDTO {
        private String text;
        private String linkUrl;
        private String wxLinkUrl;
    }
}
