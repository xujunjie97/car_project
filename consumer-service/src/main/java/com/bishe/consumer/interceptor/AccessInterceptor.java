//package com.bishe.consumer.interceptor;
//
//import com.alibaba.fastjson.JSON;
//import com.xphone.context.UserContext;
//import com.xphone.entity.User;
//import com.xphone.enums.ResultEnum;
//import com.xphone.service.UserService;
//import com.xphone.service.impl.RedisService;
//import com.xphone.service.impl.UserServiceImpl;
//import com.xphone.utils.BaseResUtil;
//import com.xphone.utils.CookieUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Objects;
//
//
//@Component
//@Slf4j
//public class AccessInterceptor extends HandlerInterceptorAdapter {
//
//
//
////    private static final String[] IGNORE_URI = {"/user/doLogin","/upload"};
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        	boolean flag = false;
//            String hearder = request.getHeader();
//			if(!flag){
//				String userSession = getSession(request, );
//				log.info (String.valueOf (user));
//				if(Objects.isNull (user)){
//					return false;
//				}
//				UserContext.setUser(user);
//				return true;
//			}
//			return false;
//    }
//
//    /**
//     * 写回浏览器
//     *
//     * @param response
//     * @param resultEnum
//     * @throws IOException
//     */
//    private void render(HttpServletResponse response, ResultEnum resultEnum) throws IOException {
//
//        PrintWriter out = response.getWriter();
//        String result = JSON.toJSONString(BaseResUtil.error(resultEnum));
//        out.println(result);
//        out.flush();
//        out.close();
//    }
//
//    private User getUser(HttpServletRequest request, HttpServletResponse response) {
//        String paramToken = request.getParameter(UserServiceImpl.COOKIE_NAME);
//        Cookie cookie = CookieUtil.getCookie(request, UserServiceImpl.COOKIE_NAME);
//        String cookieToken = cookie == null ? null : cookie.getValue();
//
//        if (Objects.isNull(paramToken) && Objects.isNull(cookieToken)) {
//
//            return null;
//    }
//        String token = Objects.isNull(paramToken) ? cookieToken : paramToken;
//        User user = userService.getByToken(response, token);
//        return user;
//    }
//
//    public String getSession(HttpServletRequest request,String userSessionId){
//
//        String userSession = (String) request.getSession().getAttribute(userSessionId);
//        if(StringUtils.isEmpty(userSession)){
//
//        }else {
//
//        }
//    }
//}
