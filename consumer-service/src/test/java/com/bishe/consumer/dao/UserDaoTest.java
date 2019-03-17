package com.bishe.consumer.dao;


import com.bishe.consumer.dao.UserDao;
import com.bishe.consumer.entity.User;
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
        User user = userDao.getOneByNickName("kkk");
        System.out.println(user);

    }

    @Test
    public void getOneByOpenId() throws Exception {
        User user = userDao.getOneByOpenId("dffw5845");
        System.out.println(user);

    }

    @Test
    public void getOneByNicName() throws Exception {
        User user = userDao.getOneByNickName("xusiduo");
        System.out.println(user);
    }
    @Test
    public void insertOne() throws Exception {
        User user = User.builder()
                .id(001L)
                .nickName("xusiduo")
                .country("中国")
                .city("株洲")
                .gender("man")
                .openId("dffw5845").build();
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
                .nickName("kkk")
                .country("中国")
                .city("株洲")
                .gender("man")
                .openId("dffw5845").build();

        userDao.update(user);
    }

}