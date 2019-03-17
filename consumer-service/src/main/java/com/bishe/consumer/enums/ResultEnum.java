package com.bishe.consumer.enums;

public enum ResultEnum {
    PARAM_ERROE(10001, "参数错误"),
    PARAM_PASSWORD_ERROR(10002, "密码错误"),
    PARAM_PASSWORD_CONFIRM_ERROR(10003, "两次密码不一致"),
    ACCOUNT_EXIST(10004, "账户已经存在"),
    ACTION_ERROR(10005,"执行失败"),
    ACCOUNT_NOT(10006,"用户账号不存在"),
    ACCOUNT_NOT2(10007,"房间不存在"),
    HOUSE_ERROR(10008,"房间已经被预定"),
    ACCOUNT_ORDER(10009,"此账号已经下单"),
    STARTTIME_ERROR(10010,"开始时间超前"),
    ENDTIME_ERROR(10011,"结束时间错误"),
    HOUSE_HAS(10012,"房间已经存在"),
    ADMIN_ERROR(10013,"管理员已经履职"),
    PHONE_ERROE(10014,"手机号码输入错误"),
    ORDER_EXICT(10015,"订单已存在"),
    ORDER_NOTHAVE(10016,"此账号没有订单"),
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
