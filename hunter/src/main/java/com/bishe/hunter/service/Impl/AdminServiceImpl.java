package com.bishe.hunter.service.Impl;

import com.bishe.hunter.dao.AdminDao;
import com.bishe.hunter.entity.Admin;
import com.bishe.hunter.enums.ResultEnum;
import com.bishe.hunter.service.AdminService;
import com.bishe.hunter.utils.BaseRes;
import com.bishe.hunter.utils.BaseResUtil;
import com.bishe.hunter.utils.CookiesUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xujunjie
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;

    @Override
    public BaseRes login(HttpServletResponse response,String adminNum, String passWord) {

        if(StringUtils.isEmpty(adminNum) || StringUtils.isEmpty(passWord)){
            log.info("传入参数为空"+System.currentTimeMillis());
            return BaseResUtil.error(ResultEnum.PARAM_ERROE);
        }

        try {
            Admin admin1 = adminDao.getOne(adminNum);
            if(admin1 != null){
                if(passWord.equals(admin1.getPassWord())){

                    CookiesUtil.setCookies(response,"adminNum",adminNum);

                    return BaseResUtil.success(admin1);
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
    public boolean loginOut(String adminNum) {

        return adminDao.deleteOne(adminNum);

    }
    @Override
    public Admin getAdmin(String adminNum) {

        return adminDao.getOne(adminNum);
    }
}
