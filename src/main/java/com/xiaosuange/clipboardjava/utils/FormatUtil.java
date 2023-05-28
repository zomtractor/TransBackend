package com.xiaosuange.clipboardjava.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
    public static String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd,hh:mm:ss");
        return format.format(new Date(System.currentTimeMillis()));
    }
}
