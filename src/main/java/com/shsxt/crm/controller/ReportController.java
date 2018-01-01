package com.shsxt.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shsxt.base.BaseController;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController {
	
	@RequestMapping("/{type}")
	public String index(@PathVariable("type")Integer type){
		switch (type) {
		case 0:
			return "customer_contribution";
		case 1:
			return "customer_gc";
		case 2:
			return "customer_serve";
		case 3:
			return "customer_loss02";
		default:
			return "error";
		}
		
	}

}
