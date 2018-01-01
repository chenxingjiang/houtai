package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.service.CustomerLossService;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

@Controller
@RequestMapping("customer_loss")
public class CustomerLossController extends BaseController {
	@Resource
	private CustomerLossService customerLossService;
	
	
	
	@RequestMapping("index")
	public String index(){
		return "customer_loss";
	}
	
	@RequestMapping("queryCustomerLossesByParams")
	@ResponseBody
	public Map<String, Object> queryCustomerLossesByParams(CustomerLossQuery customerLossQuery){
		return customerLossService.queryCustomerLossesByParams(customerLossQuery);
	}
	
	@RequestMapping("/toCustomerRepriPage/{lossId}")
	public String toCustomerRepriPage(@PathVariable("lossId")Integer lossId,Model model){
		model.addAttribute("customerLoss", customerLossService.queryCustomerLossById(lossId));
		return "customer_repri";
	}
	
	@RequestMapping("updateCustomerLossState")
	@ResponseBody
	public MessageModel updateCustomerLossState(Integer lossId,Integer state,String lossReason){
		customerLossService.updateCustomerLossState(lossId,state,lossReason);
		return success("客户流失状态更新成功!");
	}

}
