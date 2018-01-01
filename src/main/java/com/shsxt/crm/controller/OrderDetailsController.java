package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.query.OrderDetailsQuery;
import com.shsxt.crm.service.OrderDetailService;

@Controller
@RequestMapping("order_detail")
public class OrderDetailsController extends BaseController {

	@Resource
	private OrderDetailService orderDetailService;
	
	@RequestMapping("queryOrderDetailsByOrderId")
	@ResponseBody
	public Map<String, Object> queryOrderDetailsByOrderId(OrderDetailsQuery orderDetailsQuery){
		return orderDetailService.queryOrderDetailsByOrderId(orderDetailsQuery);
	}
		
}
