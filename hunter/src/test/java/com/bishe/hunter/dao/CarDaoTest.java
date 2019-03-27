package com.bishe.hunter.dao;

import com.bishe.hunter.entity.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarDaoTest {

    @Autowired
    private CarDao carDao;
    @Test
    public void getOne() throws Exception {

        Car car = carDao.getOne("001");
        System.out.println(car);
    }
    @Test
    public void insertOne() throws Exception {
        Car car = Car.builder().carNum("001").password("123456").status(1).build();
        carDao.insertOne(car);


    }
    @Test
    public void delOne() throws Exception {

        carDao.delOne("001");
    }
    @Test
    public void update() throws Exception {

        Car car = Car.builder().carNum("001").status(0).userId("afaesf44547").build();
        carDao.update(car);

    }

}