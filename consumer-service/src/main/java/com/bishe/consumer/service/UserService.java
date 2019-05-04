package com.bishe.consumer.service;


import com.bishe.consumer.entity.User;

/**
 * @author xujunjie
 */
public interface UserService {

    User getUser(String openId);

    boolean setUser(User user);

    boolean updateCarNum(String openId,String carNum);

    /**
     * 判断用户是否绑定小车
     * @param openId
     * @return
     */
    String isBinding(String openId);
     
}
