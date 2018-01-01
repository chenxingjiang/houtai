package com.shsxt.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.dao.CustomerOrderDao;
import com.shsxt.crm.query.CustomerOrderQuery;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.vo.CustomerOrder;

@Service
public class CustomerOrderService {
	@Resource
	private CustomerOrderDao customerOrderDao;
	
	public Map<String, Object> queryCustomerOrdersByCid(CustomerOrderQuery customerOrderQuery){
		PageHelper.startPage(customerOrderQuery.getPage(), customerOrderQuery.getRows());
		List<CustomerOrder> customerOrders= customerOrderDao.queryCustomerOrdersByCid(customerOrderQuery.getCid());
		PageInfo<CustomerOrder> pageInfo=new PageInfo<CustomerOrder>(customerOrders);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
	    map.put("rows", pageInfo.getList());
	    return map;
	}	
	
	
	
	public Map<String,Object> queryCustoemrOrderByOrderId(Integer orderId){
		return customerOrderDao.queryCustoemrOrderByOrderId(orderId);
	}	

}
