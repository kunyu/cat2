package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/14 23:47
 * @Description :
 */
@NoArgsConstructor
@Data
public class LoginRsp {

    private TokenInfoDTO tokenInfo;
    private String userId;
    private String lehuId;
    private String userName;
    private String mobile;
    private Boolean isNewUser;
    private Boolean isBindPhone;
    private Boolean isBindUserPassword;
    private Object hasModifyName;
    private String prepaidMobile;
    private List<MemberListDTO> memberList;
    private List<?> tagList;
    private List<?> enterpriseNames;

    @NoArgsConstructor
    @Data
    public static class TokenInfoDTO {
        private String accessToken;
        private String accessTokenExpiresAt;
        private String refreshToken;
        private String refreshTokenExpiresAt;
    }

    @NoArgsConstructor
    @Data
    public static class MemberListDTO {
        private String memberType;
        private String memberName;
        private Boolean isBound;
        private String memberCode;
        private String iconUrl;
    }
}
