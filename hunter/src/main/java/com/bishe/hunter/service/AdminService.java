package com.bishe.hunter.service;

import com.bishe.hunter.entity.Admin;
import com.bishe.hunter.utils.BaseRes;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xujunjie
 */
public interface AdminService {

    BaseRes login(HttpServletResponse response,String adminNum, String passWord);

    boolean loginOut(String adminNum);

    Admin getAdmin(String adminNum);

}
