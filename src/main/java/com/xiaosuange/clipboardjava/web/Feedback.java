package com.xiaosuange.clipboardjava.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
class FileInfo{
    private String file_name;
    private Long file_size;
    private Boolean is_dir;
}

@SuperBuilder
@Data
public class Feedback {
    private FileInfo[] file_list;
    private String msg;
}
