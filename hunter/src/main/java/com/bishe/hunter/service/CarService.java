package com.bishe.hunter.service;

import com.bishe.hunter.entity.Car;
import com.bishe.hunter.utils.BaseRes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author xujunjie
 */
public interface CarService {

    BaseRes login(HttpServletResponse response, String carNum, String passWord);

    boolean loginOut(String carNum);

    boolean updateCarStatus(String carNum, Integer status);

    boolean updateCarUserId(String carNum, String userId);

    BaseRes checkBind(String carNum);

    List<Car> getAllCar();
}
