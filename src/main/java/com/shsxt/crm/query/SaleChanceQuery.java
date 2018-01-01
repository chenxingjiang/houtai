package com.shsxt.crm.query;

import java.util.Date;

import com.shsxt.base.BaseQuery;

public class SaleChanceQuery  extends BaseQuery{
	private String customerName;
	private String createMan;
	private Integer state;
	private String createDate;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
	

}
