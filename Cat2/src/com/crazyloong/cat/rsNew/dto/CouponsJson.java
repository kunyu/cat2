package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/17 0:09
 * @Description :
 */
@NoArgsConstructor
@Data
public class CouponsJson {

    private DataDTO data;
    private MetaDTO meta;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        private List<ElementsDTO> elements;
        private String endTime;
        private String id;
        private String itemId;
        private String name;
        private String pageId;
        private PageStyleDTO pageStyle;
        private ShareInfoDTO shareInfo;
        private String startTime;
        private String title;

        @NoArgsConstructor
        @Data
        public static class PageStyleDTO {
            private String bgColor;
            private String footerColor;
            private String navStyle;
        }

        @NoArgsConstructor
        @Data
        public static class ShareInfoDTO {
            private String image;
            private String title;
            private String url;
        }

        @NoArgsConstructor
        @Data
        public static class ElementsDTO {
            private List<ContentDTO> content;
            private String id;
            private String moduleId;
            private String moduleType;
            private String name;
            private List<ModulestyleDTO> modulestyle;

            @NoArgsConstructor
            @Data
            public static class ContentDTO {
                private String desc;
                private String endTime;
                private String mainImage;
                private String rule;
                private String startTime;
                private UrlsDTO urls;
                private List<ListDTO> list;

                @NoArgsConstructor
                @Data
                public static class ListDTO {
                    private String couponId;
                    private String couponTitle;
                }

                @NoArgsConstructor
                @Data
                public static class UrlsDTO {
                    private String client;
                    private String miniProgram;
                    private String web;
                }
            }

            @NoArgsConstructor
            @Data
            public static class ModulestyleDTO {
                private String couponbackcolor;
                private String dicountcolor;
                private String getcolor;
                private String seemorecolor;
                private String titlecolor;
            }
        }
    }

    @NoArgsConstructor
    @Data
    public static class MetaDTO {
        private Integer author;
        private String contentType;
        private List<String> templates;
        private Integer tenant;
        private String tenantId;
        private Integer version;
    }
}
