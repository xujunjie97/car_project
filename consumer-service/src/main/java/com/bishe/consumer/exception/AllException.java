//package com.bishe.comsumer.exception;
//
//import com.xphone.enums.ResultEnum;
//import lombok.Getter;
//
///**
// * @author xujunjie
// */
//@Getter
//public class AllException extends RuntimeException {
//
//	private static final long serialVersionUID = 9067991894266897203L;
//	private Integer code;
//
//	public AllException(){super();}
//
//	public AllException(String message){super(message);}
//
//	public AllException(ResultEnum resultEnum){
//		this(resultEnum.getMsg ());
//		this.code = resultEnum.getCode ();
//
//	}
//}
