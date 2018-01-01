package com.shsxt.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.service.DataDicService;
import com.shsxt.crm.vo.DataDic;

@Controller
@RequestMapping("data_dic")
public class DataDicController extends BaseController {
	@Resource
	private DataDicService dataDicService;
	
	
	@RequestMapping("queryDataDicValueByDataDicName")
	@ResponseBody
	public List<DataDic> queryDataDicValueByDataDicName(String dataDicName){
		return dataDicService.queryDataDicValueByDataDicName(dataDicName);
	}

}
