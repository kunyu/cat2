package com.crazyloong.cat.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@ToString
@NoArgsConstructor
@Component
public class TushareBody {
    private String api_name;
    @Value("${tushare.token}")
    private String token;
    private Map<String,Object> params;
    @Value("")
    private String fields;
}
