//package com.bishe.consumer.exception;
//
//
//import com.xphone.enums.ResultEnum;
//import com.xphone.utils.BaseRes;
//import com.xphone.utils.BaseResUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.validation.BindException;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
///**
// * @author xujunjie
// */
//@ControllerAdvice
//@ResponseBody
//@Slf4j
//public class AllExceptionHandler {
//
//	@ExceptionHandler(value = Exception.class)
//	public BaseRes exceptionhandler(Exception ex){
//		log.error ("message  : {}", ex.getMessage());
//		ex.printStackTrace();
//		if(ex instanceof BindException){
//			BindException bindException = (BindException) ex;
//			List<ObjectError> errors = bindException.getAllErrors ();
//			String er = errors.get (0).getDefaultMessage ();
//			return BaseResUtil.error (ResultEnum.BIND_ERROR.getCode (),newstr(er));
//		}else if(ex instanceof AllException){
//			AllException allException = (AllException) ex;
//			return BaseResUtil.error (allException.getCode (),allException.getMessage ());
//		}else {
//			return BaseResUtil.error (ResultEnum.SERVICE_SERROR);
//		}
//	}
//
//	public String newstr(String str){
//		String prefix = "validate error:";
//		return prefix+str;
//	}
//}
