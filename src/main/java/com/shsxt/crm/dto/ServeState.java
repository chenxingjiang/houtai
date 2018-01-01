package com.shsxt.crm.dto;

public enum ServeState {
	
	SERVE_NEW_CREATED("1"),// 新创建
	SERVE_ASSIGNED("2"),// 已分配
    SERVE_PROCEED("3"),  // 已处理
    SERVE_FEED_BACK("4"),// 已反馈
    SERVE_ARCHIVE("5");// 已归档
	
	
	private String state;

	private ServeState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	
	

}
