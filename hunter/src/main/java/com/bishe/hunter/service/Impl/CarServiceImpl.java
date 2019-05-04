package com.bishe.hunter.service.Impl;

import com.bishe.hunter.dao.CarDao;
import com.bishe.hunter.entity.Admin;
import com.bishe.hunter.entity.Car;
import com.bishe.hunter.enums.ResultEnum;
import com.bishe.hunter.exception.AllException;
import com.bishe.hunter.service.CarService;
import com.bishe.hunter.utils.BaseRes;
import com.bishe.hunter.utils.BaseResUtil;
import com.bishe.hunter.utils.CookiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;


/**
 * @author xujunjie
 */
@Slf4j
@Service
public class CarServiceImpl implements CarService{

    private final static String NOUSER = "NOUSER";

    private final static int STATUS_UP = 1;

    @Autowired
    private CarDao carDao;

    @Override
    public BaseRes login(HttpServletResponse response, String carNum, String passWord) {
        if(StringUtils.isEmpty(carNum) || StringUtils.isEmpty(passWord)){
            log.info("传入参数为空"+System.currentTimeMillis());
            return BaseResUtil.error(ResultEnum.PARAM_ERROE);
        }

        try {
            Car car = carDao.getOne(carNum);
            if(car != null){
                if(passWord.equals(car.getPassword())){

                    CookiesUtil.setCookies(response,"adminNum",carNum);

                    return BaseResUtil.success(car);
                }else {
                    return BaseResUtil.error(ResultEnum.PARAM_PASSWORD_ERROR);
                }
            }else {
                return BaseResUtil.error(ResultEnum.BIND_ERROR);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return BaseResUtil.error(ResultEnum.BIND_ERROR);
    }


    @Override
    public boolean loginOut(String carNum) {
        return carDao.delOne(carNum);
    }


    @Override
    public boolean updateCarStatus(String carNum, Integer status) {
        Car car = Car.builder().carStatus(status).carNum(carNum).build();
        return carDao.update(car);
    }


    @Override
    public boolean updateCarUserId(String carNum, String userId) {
        if(StringUtils.isEmpty(userId)){
            log.info("userId传入失败");
            throw new AllException("userId传入失败");
        }
        //解除绑定
        if(userId.equals(NOUSER)){
            Car car = Car.builder().userId(userId).carNum(carNum).carStatus(1).build();
            return carDao.update(car);
        }

        int count = carDao.count(carNum,STATUS_UP,NOUSER);

        if(count == 1){
            Car car = Car.builder().userId(userId).carNum(carNum).carStatus(1).build();
            return carDao.update(car);
        }else if(count > 1){
            throw new AllException("查询结果不止一个。");

        }else {
            return false;
        }

    }

    @Override
    public BaseRes checkBind(String carNum) {
       Car car = carDao.getOne(carNum);
       if(Objects.equals(car.getUserId(), NOUSER)){
           return BaseResUtil.error(-1,"小车没绑定用户");
       }
       return BaseResUtil.success(car.getUserId());
    }


    @Override
    public List<Car> getAllCar() {
        return carDao.getAllCar();
    }
}
