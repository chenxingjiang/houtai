package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.query.CustomerOrderQuery;
import com.shsxt.crm.service.CustomerOrderService;

@Controller
@RequestMapping("customer_order")
public class CustomerOrderController extends BaseController {
	
	@Resource
	private CustomerOrderService customerOrderService;
	
	@RequestMapping("queryOrdersByCid")
	@ResponseBody
	public Map<String, Object> queryCustomerOrdersByCid(CustomerOrderQuery customerOrderQuery){
	   	return customerOrderService.queryCustomerOrdersByCid(customerOrderQuery);
	}
	
	
	@RequestMapping("queryCustoemrOrderByOrderId")
	@ResponseBody
	public Map<String, Object> queryCustoemrOrderByOrderId(Integer orderId){
	   	return customerOrderService.queryCustoemrOrderByOrderId(orderId);
	}


}
