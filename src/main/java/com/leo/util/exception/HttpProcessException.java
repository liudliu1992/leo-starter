package com.leo.util.exception;


/** 
 * 
 * @author arron
 * @date 2015年11月1日 下午2:31:37 
 * @version 1.0 
 */
public class HttpProcessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2749168865492921426L;

	public HttpProcessException(Exception e){
		super(e);
	}

	/**
	 * @param msg 消息
	 */
	public HttpProcessException(String msg) {
		super(msg);
	}
	
	/**
	 * @param message 消息
	 * @param e 异常
	 */
	public HttpProcessException(String message, Exception e) {
		super(message, e);
	}
	
}
