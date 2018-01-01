package com.shsxt.crm.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CustomerRepriQuery;
import com.shsxt.crm.service.CustomerRepriService;
import com.shsxt.crm.vo.CustomerReprieve;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

@Controller
@RequestMapping("customer_repri")
public class CustomerRepriController extends BaseController {
	@Resource
	private CustomerRepriService customerRepriService;

	@RequestMapping("customerReprievesByLossId")
	@ResponseBody
	public Map<String, Object> customerReprievesByLossId(CustomerRepriQuery customerRepriQuery){
		return customerRepriService.customerReprievesByLossId(customerRepriQuery);
	}	
	
	
	@RequestMapping("insert")
	@ResponseBody
	public MessageModel insert(CustomerReprieve customerReprieve){
		customerRepriService.insert(customerReprieve);
		return success("暂缓措施添加成功!");
	}
	
	@RequestMapping("update")
	@ResponseBody
	public MessageModel update(CustomerReprieve customerReprieve){
		customerRepriService.update(customerReprieve);
		return success("暂缓措施更新成功!");
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public MessageModel delete(Integer id){
		customerRepriService.delete(id);
		return success("暂缓措施删除成功!");
	}
	
	
	
	
}
