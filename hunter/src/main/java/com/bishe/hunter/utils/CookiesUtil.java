package com.bishe.hunter.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xujunjie
 */
public class CookiesUtil {

    public static String getCookies(HttpServletRequest request, String name){
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    return cookie.getValue();
                }
            }
        }
        return  null;
    }


    public static String setCookies(HttpServletResponse response, String name, String value){
        //HttpServerletRequest 装请求信息类
        //HttpServerletRespionse 装相应信息的类
        Cookie cookie=new Cookie(name,value);
        cookie.setMaxAge(3600*60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "获得cookies信息成功";
    }


    public static void delCookies(HttpServletResponse response, String name, String value){

        Cookie cookie=new Cookie(name,value);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}

