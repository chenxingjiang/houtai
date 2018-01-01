package com.shsxt.base.exceptions;

/**
 * 
 * @author lp
 *  参数异常
 */
public class ParamsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMsg;
	private Integer code;
	
	
	public ParamsException(String errorMsg, Integer code) {
		super(errorMsg);
		this.errorMsg=errorMsg;
		this.code=code;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	

}
