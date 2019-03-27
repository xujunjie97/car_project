package com.bishe.consumer.service;


import com.bishe.consumer.entity.User;

/**
 * @author xujunjie
 */
public interface UserService {

    User getUser(String openId);

    boolean setUser(User user);

    boolean updateCarNum(String openId,String carNum);
     
}
