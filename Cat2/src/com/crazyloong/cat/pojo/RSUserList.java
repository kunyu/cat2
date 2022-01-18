package com.crazyloong.cat.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix="rishang.user")
public class RSUserList {
    private List<RSUser> userList;  //注意字段名称保持一致
}
