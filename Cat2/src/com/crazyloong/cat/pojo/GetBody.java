package com.crazyloong.cat.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
@NoArgsConstructor
public class GetBody {

    private String API;

    private String host;

    private String authorization;

    private Map<String, Object> paramters;

    private String stockId;

}
