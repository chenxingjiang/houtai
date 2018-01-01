package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.RequestPermission;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.vo.SaleChance;

/**
 * 
 * @author lp
 * 营销机会管理模块
 */
@RequestMapping("sale_chance")
@Controller
public class SaleChanceController extends BaseController {
	
	@Resource
	private SaleChanceService saleChanceService;
	
	@RequestMapping("index/{state}")
	public String index(@PathVariable("state")Integer state){
	    switch (state) {
		case 1:
			return "sale_chance";
		case 2:
			return "cus_dev_plan";
		default:
			return "error";
		}
		
	}
	
	@RequestPermission(requestVal="1010")
	@RequestMapping("querySaleChancesByParams")
	@ResponseBody
	public Map<String, Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery){
		return saleChanceService.querySaleChancesByParams(saleChanceQuery);
	}	
	
	@RequestPermission(requestVal="101001")
	@RequestMapping("insert")
	@ResponseBody
	public MessageModel insert(SaleChance saleChance){
		saleChanceService.insert(saleChance);
		return  success("营销记录添加成功!");
	}
	
	@RequestPermission(requestVal="101002")
	@RequestMapping("update")
	@ResponseBody
	public MessageModel update(SaleChance saleChance){
		saleChanceService.updateSaleChance(saleChance);
		return success("营销记录更新成功!");
	}
	
	@RequestPermission(requestVal="101003")
	@RequestMapping("delete")
	@ResponseBody
	public MessageModel delete(Integer[] ids){
		saleChanceService.deleteSaleChance(ids);
		return success("营销记录删除成功!");
	}
	
	
	@RequestMapping("updateSaleChanceDevResult")
	@ResponseBody
	public MessageModel updateSaleChanceDevResult(Integer sid,Integer devResult){
		saleChanceService.updateSaleChanceDevResult(sid,devResult);
		return success(CrmConstant.OPS_SUCCESS_MSG);
	}
	
	
	
	

}
