package com.bishe.hunter.enums;

public enum ResultEnum {
    PARAM_ERROE(10001, "参数错误"),
    PARAM_PASSWORD_ERROR(10002, "密码错误"),
    PARAM_PASSWORD_CONFIRM_ERROR(10003, "两次密码不一致"),
    CHANGE_STATUS_ERROR(10004,"更新小车状态失败"),
    CHANGE_USERID_ERROR(10005,"更新小车绑定用户失败"),
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
