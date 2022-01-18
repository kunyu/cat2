package com.crazyloong.cat.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@ToString
@NoArgsConstructor
@Component
public class TushareBack {
    private String request_id;
    private int code;
    private String msg;
    private TushareData data;
}
