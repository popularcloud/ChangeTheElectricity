package com.younge.changetheelectricity.base;

public class BaseModel<T> {
    private String msg;
    private int code;   //1是成功，0是不成功
    private T data;

    public BaseModel(String message, int code) {
        this.msg = message;
        this.code = code;
    }

    public int getErrcode() {
        return code;
    }

    public void setErrcode(int code) {
        this.code = code;
    }

    public String getErrmsg() {
        return msg;
    }

    public void setErrmsg(String message) {
        this.msg = message;
    }

    public T getData() {
        return data;
    }

    public void setDataBean(T result) {
        this.data = result;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + data +
                '}';
    }
}
