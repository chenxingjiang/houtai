package com.shsxt.crm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shsxt.base.BaseVo;

public class CustomerOrder extends BaseVo{
	
	private Integer id;
	private String orderNo;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date orderDate;
	private String address;
	private Integer state;
	private Integer cid;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	

}
