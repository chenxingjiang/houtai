package com.shsxt.crm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shsxt.crm.dao.DataDicDao;
import com.shsxt.crm.vo.DataDic;

@Service
public class DataDicService {

	@Resource
	private DataDicDao dataDicDao;
	public List<DataDic> queryDataDicValueByDataDicName(String dataDicName){
		return dataDicDao.queryDataDicValueByDataDicName(dataDicName);
	}
}
