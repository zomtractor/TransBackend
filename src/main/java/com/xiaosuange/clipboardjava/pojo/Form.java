package com.xiaosuange.clipboardjava.pojo;

import lombok.Data;

public @Data
class Form {
    private Long id;
    private String phoneName;
    private String phoneAddress;
    private Integer serverPort;
    private Integer phonePort;
    private Boolean autoMode;
    private Boolean farMode;
}