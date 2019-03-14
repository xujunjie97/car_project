package com.bishe.consumer.utils;


import com.bishe.consumer.enums.ResultEnum;

/**
 * function: 对返回结果进行包装，形成统一格式
 * @author xujunjie
 */
public class BaseResUtil {

    public static BaseRes<Object> success(Object obj) {
        BaseRes<Object> baseRes = new BaseRes<>();
        baseRes.setCode(ResultEnum.SUCCESS.getCode());
        baseRes.setMsg(ResultEnum.SUCCESS.getMsg());
        baseRes.setData(obj);
        return baseRes;
    }

    public static BaseRes success() {
        return success(null);
    }

    public static BaseRes error(Integer code, String msg) {
        BaseRes baseRes = new BaseRes();
        baseRes.setCode(code);
        baseRes.setMsg(msg);
        baseRes.setData(null);
        return baseRes;
    }

    public static BaseRes error(ResultEnum resultEnum) {
        BaseRes baseRes = new BaseRes();
        baseRes.setCode(resultEnum.getCode());
        baseRes.setMsg(resultEnum.getMsg());
        baseRes.setData(null);
        return baseRes;
    }


}
