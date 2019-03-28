package com.bishe.hunter.utils;


public class BaseRes<T> extends BaseResRoot {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseRes{" +
                "code=" + super.getCode() + "," +
                "msg=" + "\"" + super.getMsg() + "\"" + "," +
                "data=" + data +
                '}';
    }
}
