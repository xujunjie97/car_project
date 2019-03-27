package com.bishe.hunter.web;

import com.bishe.hunter.entity.Admin;
import com.bishe.hunter.enums.ResultEnum;
import com.bishe.hunter.service.AdminService;
import com.bishe.hunter.service.CarService;
import com.bishe.hunter.utils.BaseRes;
import com.bishe.hunter.utils.BaseResUtil;
import com.bishe.hunter.utils.CookiesUtil;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xujunjie
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final static int UP = 1;
    private final static int DOWN = 0;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CarService carService;

    @GetMapping("/main")
    public String main(){

            return "welcome";
    }

    /**
     * 更新小车状态
     */
    @PostMapping("/updateStatus")
    public BaseRes updateStatus(String carNum,int status){

        if(StringUtils.isNotEmpty(carNum)){
            if(carService.updateCarStatus(carNum,status)){
                return BaseResUtil.success();
            }else {
                return BaseResUtil.error(ResultEnum.CHANGE_STATUS_ERROR);
            }
        }
        return BaseResUtil.error(ResultEnum.BIND_ERROR);

    }

}
