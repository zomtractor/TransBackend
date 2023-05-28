package com.xiaosuange.clipboardjava.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hist {
    private Long id;
    private String source;
    private String time;
    private String text;
}
