package com.bishe.consumer.interceptor;


import com.bishe.consumer.redis.key.prefix.UserKeyPrefix;
import com.bishe.consumer.service.RedisService;
import com.sun.xml.internal.ws.util.Pool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Configuration
@Aspect
@Slf4j
public class ServiceAspect {

    @Autowired
    private RedisService redisService;

    private final String ExpGetResultDataPonit = "execution(* com.bishe.consumer.web.UserController.userInfo(..))";

    //定义切入点,拦截servie包其子包下的所有类的所有方法
//    @Pointcut("execution(* com.haiyang.onlinejava.complier.service..*.*(..))")
    //拦截指定的方法,这里指只拦截TestService.getResultData这个方法
    @Pointcut(ExpGetResultDataPonit)
    public void excuteService() {

    }

    //执行方法前的拦截方法
    @Before("excuteService()")
    public void doBeforeMethod(JoinPoint joinPoint) {
        System.out.println("我是前置通知，我将要执行一个方法了");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        String userSessionKey = (String) obj[0];
        String userSession = redisService.get(new UserKeyPrefix("user"),userSessionKey,String.class);
        if(StringUtils.isNotEmpty(userSession)){
          log.info("userSession={}",userSession);
        }
    }

    /**
     * 后置返回通知
     * 这里需要注意的是:
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     */
//    @AfterReturning(value = ExpGetResultDataPonit, returning = "keys")
//    public void doAfterReturningAdvice1(JoinPoint joinPoint, Object keys) {
//        System.out.println("第一个后置返回通知的返回值：" + keys);
//        if (keys instanceof ResultVO) {
//            ResultVO resultVO = (ResultVO) keys;
//            String message = resultVO.getMessage();
//            resultVO.setMessage("通过AOP把值修改了 " + message);
//        }
//        System.out.println("修改完毕-->返回方法为:" + keys);
//    }
//
//
//    /**
//     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
//     */
////    @After("excuteService()")
//    public void doAfterAdvice(JoinPoint joinPoint) {
//
//        System.out.println("后置通知执行了!!!!");
//    }
//
//
//
//
//    /**
//     * 环绕通知：
//     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
//     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
//     */
//    @Around(ExpGetResultDataPonit)
//    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
//        System.out.println("环绕通知的目标方法名：" + proceedingJoinPoint.getSignature().getName());
//        processInputArg(proceedingJoinPoint.getArgs());
//        try {//obj之前可以写目标方法执行前的逻辑
//            Object obj = proceedingJoinPoint.proceed();//调用执行目标方法
//            processOutPutObj(obj);
//            return obj;
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 处理返回对象
//     */
//    private void processOutPutObj(Object obj) {
//        System.out.println("OBJ 原本为：" + obj.toString());
//        if (obj instanceof ResultVO) {
//            ResultVO resultVO = (ResultVO) obj;
//            resultVO.setMessage("哈哈，我把值修改了" + resultVO.getMessage());
//            System.out.println(resultVO);
//        }
//    }
//
//    /**
//     * 处理输入参数
//     *
//     * @param args 入参列表
//     */
//    private void processInputArg(Object[] args) {
//        for (Object arg : args) {
//            System.out.println("ARG原来为:" + arg);
//            if (arg instanceof ParamVO) {
//                ParamVO paramVO = (ParamVO) arg;
//                paramVO.setInputParam("654321");
//            }
//        }
//    }


}

