package com.bishe.consumer.enums;

public enum ResultEnum {
    PARAM_ERROE(10001, "参数错误"),
    PARAM_PASSWORD_ERROR(10002, "密码错误"),
    PARAM_PASSWORD_CONFIRM_ERROR(10003, "两次密码不一致"),
    ACCOUNT_EXIST(10004, "账户已经存在"),
    ACTION_ERROR(10005,"执行失败"),
    ACCOUNT_NOT(10006,"用户账号不存在"),
    RPC_ERROR(10007,"远程方法调用失败"),
    BIND_CAR_ERROR(10008,"绑定车辆失败"),
    SERVICE_SERROR(10017,"服务错误"),
    BIND_ERROR(10018,"绑定异常"),
    SUCCESS(0, "success");


    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
