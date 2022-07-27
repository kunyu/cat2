package com.crazyloong.cat.rsNew.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/14 22:42
 * @Description : 中免日上设备登录返回对象
 */
@NoArgsConstructor
@Data
public class DeviceLoginRsp {

    private String userId;
    private String refreshToken;
    private String refreshTokenExpiresAt;
    private UserAgentDTO userAgent;
    private String accessToken;
    private String accessTokenExpiresAt;
    private Boolean isCreateB;

    @NoArgsConstructor
    @Data
    public static class UserAgentDTO {
        private String sign;
        private String serialNo;
        private String system;
        private String appName;
        private String version;
        private String type;
        private String model;
        private String serverId;
        private Object data;
    }
}
