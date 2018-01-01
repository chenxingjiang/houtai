package com.shsxt.crm.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SaleChance {
	
	private Integer id;
	private String chanceSource;
	private String customerName;
	private String cgjl;
	private String overview;
	private String linkMan;
	private String linkPhone;
	private String description;
	private String createMan;
	private String assignMan;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date assignTime;
	private Integer state;
	private Integer devResult;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateDate;
	private Integer isValid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChanceSource() {
		return chanceSource;
	}
	public void setChanceSource(String chanceSource) {
		this.chanceSource = chanceSource;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCgjl() {
		return cgjl;
	}
	public void setCgjl(String cgjl) {
		this.cgjl = cgjl;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public String getAssignMan() {
		return assignMan;
	}
	public void setAssignMan(String assignMan) {
		this.assignMan = assignMan;
	}
	public Date getAssignTime() {
		return assignTime;
	}
	public void setAssignTime(Date assignTime) {
		this.assignTime = assignTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getDevResult() {
		return devResult;
	}
	public void setDevResult(Integer devResult) {
		this.devResult = devResult;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	@Override
	public String toString() {
		return "SaleChance [id=" + id + ", chanceSource=" + chanceSource
				+ ", customerName=" + customerName + ", cgjl=" + cgjl
				+ ", overview=" + overview + ", linkMan=" + linkMan
				+ ", linkPhone=" + linkPhone + ", description=" + description
				+ ", createMan=" + createMan + ", assignMan=" + assignMan
				+ ", assignTime=" + assignTime + ", state=" + state
				+ ", devResult=" + devResult + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", isValid=" + isValid + "]";
	}
	
	
	
	
	

}
