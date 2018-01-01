package com.shsxt.crm.dto;

/**
 * 
 * @author lp
 * 开发结果枚举类
 * 开发状态 默认为未开发  0-未开发  1-开发中  2-开发成功  3-开发失败
 */
public enum DevResult {
	
	UN_DEV(0),
	DEVING(1),
	DEV_SUCCESS(2),
	DEV_FAILED(3);
	
	private Integer devResult;

	
	
	private DevResult(Integer devResult) {
		this.devResult = devResult;
	}

	public Integer getDevResult() {
		return devResult;
	}

	public void setDevResult(Integer devResult) {
		this.devResult = devResult;
	}
	
	

}
