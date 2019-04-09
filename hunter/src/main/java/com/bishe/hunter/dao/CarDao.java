package com.bishe.hunter.dao;

import com.bishe.hunter.entity.Car;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xujunjie
 */
@Repository
public interface CarDao {

    Car getOne(String carNum);

    boolean insertOne(@Param("car") Car car);

    boolean delOne(String carNum);

    boolean update(@Param("car") Car car);

    int count(@Param("carNum") String carNum, @Param("status") int status,@Param("userId") String userId);

    List<Car> getAllCar();
}
