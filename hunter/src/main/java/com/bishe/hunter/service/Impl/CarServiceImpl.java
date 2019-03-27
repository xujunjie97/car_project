package com.bishe.hunter.service.Impl;

import com.bishe.hunter.dao.CarDao;
import com.bishe.hunter.entity.Admin;
import com.bishe.hunter.entity.Car;
import com.bishe.hunter.enums.ResultEnum;
import com.bishe.hunter.service.CarService;
import com.bishe.hunter.utils.BaseRes;
import com.bishe.hunter.utils.BaseResUtil;
import com.bishe.hunter.utils.CookiesUtil;
import com.sun.tools.internal.xjc.model.CArrayInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


/**
 * @author xujunjie
 */
@Slf4j
@Service
public class CarServiceImpl implements CarService{

    private final static String NOUSER = "NOUSER";

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
                    return BaseResUtil.error(ResultEnum.PARAM_ERROE);
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
    public boolean updateCarStatus(String carNum, int status) {
        Car car = Car.builder().status(status).carNum(carNum).build();
        return carDao.update(car);
    }


    @Override
    public boolean updateCarUserId(String carNum, String userId) {
        if(StringUtils.isEmpty(userId)){
            log.info("userId传入失败");
            return false;
        }
        Car car = Car.builder().userId(userId).carNum(carNum).build();
        return carDao.update(car);
    }

    @Override
    public BaseRes checkBind(String carNum) {
       Car car = carDao.getOne(carNum);
       if(Objects.equals(car.getUserId(), NOUSER)){
           return BaseResUtil.error(-1,"小车没绑定用户");
       }
       return BaseResUtil.success(car.getUserId());
    }
}
