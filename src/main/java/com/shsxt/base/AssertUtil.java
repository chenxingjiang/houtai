package com.shsxt.base;

import com.shsxt.base.exceptions.ParamsException;



public class AssertUtil {
	
	
	public static void isTrue(Boolean flag,String errorMsg,Integer code) {
		if(flag){
			throw new ParamsException(errorMsg, code);
		}
	}

	
	public static void isTrue(Boolean flag,String errorMsg) {
		if(flag){
			throw new ParamsException(errorMsg, 300);
		}
	}
}
