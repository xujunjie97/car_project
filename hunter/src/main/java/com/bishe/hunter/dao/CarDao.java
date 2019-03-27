package com.bishe.hunter.dao;

import com.bishe.hunter.entity.Car;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xujunjie
 */
@Repository
public interface CarDao {

    Car getOne(String carNum);

    boolean insertOne(@Param("car") Car car);

    boolean delOne(String carNum);

    boolean update(@Param("car") Car car);

}
