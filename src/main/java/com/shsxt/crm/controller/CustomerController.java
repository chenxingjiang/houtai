package com.shsxt.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CustomerDtoQuery;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.service.CustomerService;
import com.shsxt.crm.vo.Customer;

/**
 * 
 * @author lp
 * 客户信息管理模块
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController{
	
	@Resource
	private CustomerService customerService;

	
	
	@RequestMapping("index")
	public String index(){
		return "customer";
	}
	
	@RequestMapping("queryAllCustomers")
	@ResponseBody
	public List<Customer> queryAllCustomers(){
		return customerService.queryAllCustomers();
	}
	
	
	@RequestMapping("queryCustomersByParams")
	@ResponseBody
	public Map<String, Object> queryCustomersByParams(CustomerQuery customerQuery){
		return customerService.queryCustomersByParams(customerQuery);
	}	
	

	@RequestMapping("insert")
	@ResponseBody
	public MessageModel insert(Customer customer){
		customerService.insert(customer);
		return success("客户信息添加成功!");
	}
	
	@RequestMapping("update")
	@ResponseBody
	public MessageModel update(Customer customer){
		customerService.update(customer);
		return success("客户信息更新成功!");
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public MessageModel delete(Integer[] ids){
		customerService.deleteCustoemrs(ids);
		return success("客户信息删除成功!");
	}
	
	
	@RequestMapping("toOtherPage/{type}/{cid}")
	public String toOtherPage(@PathVariable("type")Integer type,@PathVariable("cid")Integer cid,Model model){
		model.addAttribute("customer",customerService.queryById(cid)) ;
		switch (type) {
		case 1:
			return "customer_linkman";
		case 2:
			return "customer_contact";
	    case 3:
	    	return "customer_order";
		default:
			return "error";
		}
	}
	
	
	@RequestMapping("queryCustomersContribution")
	@ResponseBody
	public Map<String, Object> queryCustomersContribution(CustomerDtoQuery customerDtoQuery){
		return customerService.queryCustomersContribution(customerDtoQuery);
	}	
	
	@RequestMapping("queryCustomersGc")
	@ResponseBody
	public Map<String, Object> queryCustomersGc(){
		return customerService.queryCustomersGc();
	}
	
}
 