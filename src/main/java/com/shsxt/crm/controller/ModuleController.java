package com.shsxt.crm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.service.ModuleService;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {
	@Resource
	private ModuleService moduleService;
	
	@RequestMapping("queryAllsModuleDtos")
	@ResponseBody
	public List<ModuleDto> queryAllsModuleDtos(Integer rid){
		return moduleService.queryAllsModuleDtos(rid);
	}
	
	

}
