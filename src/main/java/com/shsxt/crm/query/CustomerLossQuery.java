package com.shsxt.crm.query;

import com.shsxt.base.BaseQuery;

public class CustomerLossQuery extends BaseQuery{
	
	private String cusNo;
	private String cusName;
	private String cusManager;
	private String time;
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCusNo() {
		return cusNo;
	}
	public void setCusNo(String cusNo) {
		this.cusNo = cusNo;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusManager() {
		return cusManager;
	}
	public void setCusManager(String cusManager) {
		this.cusManager = cusManager;
	}
	
	

}
