package com.crazyloong.cat.rsNew.dto;

import lombok.Data;

import java.util.UUID;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/14 22:28
 * @Description : 中免日上设备登录请求对象
 */

@Data
public class DeviceLoginReq {

    /* 登录方式 */
    private String sign;
    /* 时间戳 */
    private Long timeStamp;
    /* 密码 */
    private String password;
    /* 登录手机号 */
    private String userName;
    private UserAgentDTO userAgent;


    @Data
    public static class UserAgentDTO {
        private String appName;
        private String model;
        private String serialNo;
        private String sign;
        private String system;
        private String type;
        private String version;

        public UserAgentDTO() {
            appName = "华为";
            model = "SM-G977N";
            //serialNo = "bnVsbF84M2RkN2RjYmI0YmUyEEYy";
            serialNo = UUID.randomUUID().toString().replaceAll("-","");
            sign = "";
            system = "9";
            type = "Android";
            version = "1.1.4";
        }
    }

    public DeviceLoginReq() {
        sign = "md5sign";
        timeStamp = System.currentTimeMillis();
        userAgent = new UserAgentDTO();
        userName = "";
        // qaz123456
        //password = "34dec7944f93cb8e322eb9b76edc5e7110959ab60269a183c5fb955d6618cdc00bf000e1bfc6d5d7dc5ba0024e279d8e15c7af02d88d832657f0f64dd0c34c2e";
        // yll123456
        //password = "1aa1ec5f613ca8ab85264eae77f4fbf51694e86d89fec6aa03a9f3a392a2de679c02e444baaac153799a1391b51fb45010d258efa496eddbd9dbaeed74afd659";
        // wang123456
        //password = "5727a864535fb0f66afb823a72091fec3b4387cf3ba59a48d5d18c0840a089db765cafb86567bfab2e260a3340e7d772f2a9c4d7b7b9fb8a97d81a762877b849";
        // aa123456
        password = "b3bfbb4e359fb766102e7aeca241f58e19674f89d5f7c31e28884d74aa0a4c3c00680f0aab7e8d713ca53e9ad010e26607c9aaf642da12d7e006a25d175bf7ba";

    }
}
