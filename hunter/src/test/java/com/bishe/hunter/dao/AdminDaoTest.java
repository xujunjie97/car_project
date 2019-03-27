package com.bishe.hunter.dao;

import com.bishe.hunter.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminDaoTest {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertOne() throws Exception {

        String password = passwordEncoder.encode("123456");
        Admin admin = Admin.builder().adminName("siduo").adminNum("123456").passWord(password).build();

        adminDao.insertOne(admin);


    }
    @Test
    public void deleteOne() throws Exception {

        adminDao.deleteOne("001");

    }
    @Test
    public void getOne() throws Exception {

        Admin admin = adminDao.getOne("001");
        System.out.println(admin.toString());
    }

}