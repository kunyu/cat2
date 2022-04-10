package com.crazyloong.cat.rshainan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/13 23:49
 * @Description : 首页信息返回
 */
@NoArgsConstructor
@Data
public class SubjectListRsp {


    private List<DataDTO> data;
    private List<ListDTO> list;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        private Integer subjectType;
        private String name;
        private String title;
        private String subjectId;
        private List<ContentDTO> content;
        private List<?> goods;
        private InfoDTO info;

        @NoArgsConstructor
        @Data
        public static class InfoDTO {
            private String id;
            private String url;
            private Integer urlType;
            private String imageUrl;
            private Integer idType;
            private String idValue;
        }

        @NoArgsConstructor
        @Data
        public static class ContentDTO {
            private String id;
            private String url;
            private Integer order;
            private Integer urlType;
            private String imageUrl;
            private Integer idType;
            private String idValue;
        }
    }

    @NoArgsConstructor
    @Data
    public static class ListDTO {
        private String goodsId;
        private String smallImage;
        private Integer marketPrice;
        private Double salesPrice;
        private String productName;
        private List<String> labels;
        private Integer count;
    }
}
