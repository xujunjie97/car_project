package com.bishe.hunter.service;

import com.bishe.hunter.utils.BaseRes;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xujunjie
 */
public interface CarService {

    BaseRes login(HttpServletResponse response, String carNum, String passWord);

    boolean loginOut(String carNum);

    boolean updateCarStatus(String carNum, int status);

    boolean updateCarUserId(String carNum, String userId);

    BaseRes checkBind(String carNum);
}
