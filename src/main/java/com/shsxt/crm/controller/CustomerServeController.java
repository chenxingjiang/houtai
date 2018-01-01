package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CustomerServeQuery;
import com.shsxt.crm.service.CustomerServeService;
import com.shsxt.crm.vo.CustomerServe;

@Controller
@RequestMapping("customer_serve")
public class CustomerServeController extends BaseController {
	
	@Resource
	private CustomerServeService customerServeService;
	
	
	@RequestMapping("index/{type}")
	public String index(@PathVariable("type")Integer type){
		switch (type) {
		case 1:
			return "customer_serve_create";
		case 2:
			return "customer_serve_assign";
		case 3:
			return "customer_serve_proceed";
		case 4:
			return "customer_serve_feed_back";
		case 5:
			return "customer_serve_archive";
		default:
			return "error";
		}
	}
	
	
	@RequestMapping("insert")
	@ResponseBody
	public MessageModel insert(CustomerServe customerServe){
		customerServeService.insert(customerServe);
		return success("服务记录创建成功!");
	}

	
	
	@RequestMapping("update")
	@ResponseBody
	public MessageModel update(CustomerServe customerServe,HttpServletRequest request){
		
		customerServeService.update(customerServe,request);
		return success("服务更新成功!");
	}
	
	
	@RequestMapping("queryCustomerServesByParams")
	@ResponseBody
	public Map<String, Object> queryCustomerServesByParams(CustomerServeQuery customerServeQuery){
		return customerServeService.queryCustomerServesByParams(customerServeQuery);
	}
	
	
	@RequestMapping("queryCustomerServeDtos")
	@ResponseBody
	public Map<String, Object> queryCustomerServeDtos(){
		return customerServeService.queryCustomerServeDtos();
	}
}
 