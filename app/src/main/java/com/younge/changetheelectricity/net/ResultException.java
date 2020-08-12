package com.younge.changetheelectricity.net;

import java.io.IOException;

public class ResultException extends IOException {

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultException(){}

    public ResultException(int code,String msg){
        this.code = code;
        this.msg = msg;
    };
}
