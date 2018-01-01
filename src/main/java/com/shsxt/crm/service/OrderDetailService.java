package com.shsxt.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.dao.OrderDetailDao;
import com.shsxt.crm.query.OrderDetailsQuery;
import com.shsxt.crm.vo.OrderDetail;

@Service
public class OrderDetailService {
	@Resource
	private OrderDetailDao orderDetailDao;

	public Map<String, Object> queryOrderDetailsByOrderId(OrderDetailsQuery orderDetailsQuery){
		// 初始化分页环境
		PageHelper.startPage(orderDetailsQuery.getPage(), orderDetailsQuery.getRows());
		List<OrderDetail> orderDetails= orderDetailDao.queryOrderDetailsByOrderId(orderDetailsQuery.getOrderId());
		PageInfo<OrderDetail> pageInfo=new PageInfo<OrderDetail>(orderDetails);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}


}
