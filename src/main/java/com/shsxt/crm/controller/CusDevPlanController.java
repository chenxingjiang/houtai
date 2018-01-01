package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.service.CusDevPlanService;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.vo.CusDevPlan;
import com.shsxt.crm.vo.SaleChance;

@Controller
@RequestMapping("cus_dev_plan")
public class CusDevPlanController extends BaseController {
	
	@Resource
	private SaleChanceService saleChanceService;
	
	@Resource
	private CusDevPlanService cusDevPlanService;
	
	
	@RequestMapping("index")
	public String index(Integer sid,Model model){
		SaleChance saleChance= saleChanceService.queryById(sid);
		model.addAttribute("saleChance", saleChance);
		return "cus_dev_plan_detail";
	}
	
	
	@RequestMapping("queryCusDevPlansByParams")
	@ResponseBody
	public Map<String, Object> queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery){
		return cusDevPlanService.queryCusDevPlansByParams(cusDevPlanQuery);
	}	
	
	
	@RequestMapping("insert")
	@ResponseBody
	public MessageModel insert(CusDevPlan cusDevPlan){
		cusDevPlanService.insert(cusDevPlan);
		return success("成功添加计划项数据");
		
	}
	
	@RequestMapping("update")
	@ResponseBody
	public MessageModel update(CusDevPlan cusDevPlan){
		cusDevPlanService.update(cusDevPlan);
		return success("计划项数据更新成功");
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public MessageModel delete(Integer id){
		cusDevPlanService.delete(id);
		return success("计划项数据删除成功");
	}
	
	

}
