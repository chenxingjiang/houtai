package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.vo.DataDic;

public interface DataDicDao {
	
	@Select("select data_dic_name as dataDicName,data_dic_value as dataDicValue from t_datadic where is_valid=1 and data_dic_name=#{dataDicName}")
	public List<DataDic> queryDataDicValueByDataDicName(String dataDicName);

}
