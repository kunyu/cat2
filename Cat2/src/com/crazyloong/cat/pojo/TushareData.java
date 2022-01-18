package com.crazyloong.cat.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Component
public class TushareData {
    private List<String> fields;
    private List<List<String>> items;
    private String has_more;
}
