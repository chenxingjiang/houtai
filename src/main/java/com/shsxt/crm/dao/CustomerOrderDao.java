package com.shsxt.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import com.shsxt.crm.vo.Customer;
import com.shsxt.crm.vo.CustomerOrder;

public interface CustomerOrderDao {

	@Select("select id,cus_id as cusId,order_no as  orderNo,order_date as orderDate,address,state"
			+ " from t_customer_order o "
			+ " where o.cus_id=#{cid} and is_valid=1")
	public List<CustomerOrder> queryCustomerOrdersByCid(Integer cid);
	
	
	public Map<String, Object> queryCustoemrOrderByOrderId(@Param("orderId")Integer orderId);
	
	public CustomerOrder queryCustomerLastOrderByCusId(@Param("cusId")Integer cusId);
}


