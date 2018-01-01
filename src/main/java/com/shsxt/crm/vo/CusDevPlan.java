package com.shsxt.crm.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shsxt.base.BaseVo;

public class CusDevPlan extends BaseVo{
	
	private Integer id;
	private String planItem;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date planDate;
	private String exeAffect;
	private Integer saleChanceId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlanItem() {
		return planItem;
	}
	public void setPlanItem(String planItem) {
		this.planItem = planItem;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public String getExeAffect() {
		return exeAffect;
	}
	public void setExeAffect(String exeAffect) {
		this.exeAffect = exeAffect;
	}
	public Integer getSaleChanceId() {
		return saleChanceId;
	}
	public void setSaleChanceId(Integer saleChanceId) {
		this.saleChanceId = saleChanceId;
	}
	@Override
	public String toString() {
		return super.toString()+ "CusDevPlan [id=" + id + ", planItem=" + planItem
				+ ", planDate=" + planDate + ", exeAffect=" + exeAffect
				+ ", saleChanceId=" + saleChanceId + "]";
	}
	
	

}
