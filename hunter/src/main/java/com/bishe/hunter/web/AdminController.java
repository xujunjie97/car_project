package com.bishe.hunter.web;


import com.bishe.hunter.entity.Admin;
import com.bishe.hunter.entity.Car;
import com.bishe.hunter.enums.ResultEnum;
import com.bishe.hunter.exception.AllException;
import com.bishe.hunter.fegin.UserClient;
import com.bishe.hunter.service.AdminService;
import com.bishe.hunter.service.CarService;
import com.bishe.hunter.utils.BaseRes;
import com.bishe.hunter.utils.BaseResUtil;
import com.bishe.hunter.utils.CookiesUtil;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.sun.jersey.server.impl.uri.rules.BaseRule;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author xujunjie
 */
@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final static int UP = 1;
    private final static int DOWN = 0;

    private static String NOUSER = "NOUSER";

    @Autowired
    private AdminService adminService;

    @Autowired
    private CarService carService;

    @Autowired
    private UserClient userClient;

    private final String prefix = "pages/";

    @RequestMapping("/main")
    public String main(Authentication authentication) {
        System.out.println(authentication);
        String name = authentication.getName();
        return prefix + "content";
    }

    @GetMapping("/carStatus")
    public String carStatus() {
        return prefix + "carStatus";
    }

    @GetMapping("/bindStatus")
    public String bindStatus() {
        return prefix + "bindStatus";
    }

    /**
     * 更新小车状态
     */
    @PostMapping("/updateStatus")
    @ResponseBody
    public BaseRes updateStatus(@RequestParam String carNum,  @RequestParam Integer status) {
        if (StringUtils.isNotEmpty(carNum)) {
            if (carService.updateCarStatus(carNum, status)) {
                return BaseResUtil.success();
            } else {
                return BaseResUtil.error(ResultEnum.CHANGE_STATUS_ERROR);
            }
        }
        return BaseResUtil.error(ResultEnum.BIND_ERROR);

    }

    @PostMapping("/unBind")
    @Transactional
    @LcnTransaction
    @ResponseBody
    public BaseRes unBindByAdmin(String openId, String carNum) {

        BaseRes res = userClient.changeUserBind(openId);
        if (res.getCode() != 0) {
            log.error("解除绑定，远程方法调用失败");
            throw new AllException(ResultEnum.RPC_ERROR);
        }

        if (carService.updateCarUserId(carNum, NOUSER)) {
            return BaseResUtil.success();
        } else {
            log.error("解除绑定，本地方法调用失败");
            throw new AllException(ResultEnum.BIND_ERROR);
        }

    }

    @GetMapping("/getAllCar")
    @ResponseBody
    public BaseRes getAllCar(Object obj) {

        List<Car> carList = carService.getAllCar();

        return BaseResUtil.success(carList);
    }

}
