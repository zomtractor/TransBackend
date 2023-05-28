package com.xiaosuange.clipboardjava.pojo;

import lombok.Data;

@Data
public
class Resp{
    private Object data;
    private Integer status;

    public static Resp ok(Object data,int type){
        Resp resp = new Resp();
        resp.setData(data);
        resp.setStatus(type);
        return resp;
    }
    public static Resp fail(){
        Resp resp = new Resp();
        resp.setStatus(-1);
        return resp;
    }
}