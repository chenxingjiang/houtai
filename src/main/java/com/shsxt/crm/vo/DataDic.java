package com.shsxt.crm.vo;

import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;
import com.shsxt.base.BaseVo;

/**
 * @author lp
 *
 */
public class DataDic extends BaseVo{
	private Integer id;
	private String dataDicName;
	private String dataDicValue;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDataDicName() {
		return dataDicName;
	}
	public void setDataDicName(String dataDicName) {
		this.dataDicName = dataDicName;
	}
	public String getDataDicValue() {
		return dataDicValue;
	}
	public void setDataDicValue(String dataDicValue) {
		this.dataDicValue = dataDicValue;
	}
	
	
	
	
	

}
