package com.bishe.consumer.service.Impl;

import com.bishe.consumer.dao.UserDao;
import com.bishe.consumer.entity.User;
import com.bishe.consumer.exception.AllException;
import com.bishe.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String openId) {
        User user = null;
        try {
            user = userDao.getOneByOpenId(openId);
        } catch (Exception e) {
            throw new AllException("访问数据库异常");
        }

        return user;
    }
    @Override
    public boolean setUser(User user) {

        return userDao.insertOne(user);

    }

    @Override
    public boolean updateCarNum(String openId, String carNum) {

        User user = User.builder().openId(openId).carNum(carNum).build();

        return userDao.update(user);

    }

    @Override
    public String isBinding(String openId) {


        return userDao.isBinding(openId);
    }
}
