package com.bishe.comsumer.dao;

import com.bishe.comsumer.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void getOneById() throws Exception {
        User user = userDao.getOneByNicName("xusiduo");
        System.out.println(user);

    }
    @Test
    public void getOneByNicName() throws Exception {
        User user = userDao.getOneByNicName("xusiduo");
        System.out.println(user);
    }
    @Test
    public void insertOne() throws Exception {
        User user = User.builder()
                .id(002L)
                .nicName("xusiduo")
                .avatarUrl("https://www.yuque.com/qxvho5/mshgg7/lgnpxc")
                .gender("man").build();
        userDao.insertOne(user);
    }
    @Test
    public void deleteOne() throws Exception {
        userDao.deleteOne(001L);
    }
    @Test
    public void update() throws Exception {
        User user = User.builder()
                .id(001L)
                .nicName("kkk")
                .avatarUrl("https://www.yuque.com/qxvho5/mshgg7/lgnpxc")
                .gender("man").build();

        userDao.update(user);
    }

}