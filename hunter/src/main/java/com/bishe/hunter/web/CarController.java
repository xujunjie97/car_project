package com.bishe.hunter.web;


import com.bishe.hunter.entity.Car;
import com.bishe.hunter.enums.ResultEnum;
import com.bishe.hunter.exception.AllException;
import com.bishe.hunter.service.AdminService;
import com.bishe.hunter.service.CarService;
import com.bishe.hunter.utils.BaseRes;
import com.bishe.hunter.utils.BaseResUtil;
import com.bishe.hunter.utils.CookiesUtil;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.locks.Lock;

/**
 * @author xujunjie
 */
@RestController
@Slf4j
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/login")
    public BaseRes adminLogin(HttpServletRequest request, HttpServletResponse response, Car car){

            Object object = CookiesUtil.getCookies(request,"carNum");

            if(!Objects.isNull(object)){
                return BaseResUtil.success();
            }

            return carService.login(response,car.getCarNum(),car.getPassword());
    }


    @PostMapping("/loginOut")
    public BaseRes adminLoginOut(HttpServletRequest request, HttpServletResponse response, Car car){

        if(carService.loginOut(car.getCarNum())){

            CookiesUtil.delCookies(response,"carNum",car.getCarNum());

            return BaseResUtil.success();

        }
        return BaseResUtil.error(ResultEnum.BIND_ERROR);

    }

    @PostMapping("/checkBind")
    public BaseRes checkBindStatus(String carNum,String time){
        boolean cycle = false;
        int num = 0;
        if("0".equals(time)){
            cycle = true;
        }
        try {
          while (cycle && num<50){
              BaseRes result = carService.checkBind(carNum);
              if(result.getCode() == 0){
                  return result;
              }
              log.info("循环确认中。。。");
              Thread.sleep(1000);
              num++;
          }
            return carService.checkBind(carNum);
        }catch (Exception e){
            e.printStackTrace();
        }

        return BaseResUtil.error(ResultEnum.BIND_ERROR);

    }

    /**
     * 更新绑定用户
     */
    @PostMapping("/updateUserId")
    @Transactional
    @LcnTransaction
    public BaseRes updateUserId(String carNum, String userId){

        if(StringUtils.isNotEmpty(carNum)){
            if(carService.updateCarUserId(carNum,userId)){
                return BaseResUtil.success();
            }else {
                throw new AllException(ResultEnum.CHANGE_USERID_ERROR);
            }
        }
//        return BaseResUtil.error(ResultEnum.BIND_ERROR);
        throw new AllException(ResultEnum.BIND_ERROR);
    }


}
