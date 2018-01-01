package com.shsxt.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.vo.CustomerLoss;


public interface CustomerLossDao {
	
	public Map<String, Object> queryById(Integer id);
	
	
	public Integer insert(CustomerLoss customerLoss);
	
	public Integer update(CustomerLoss customerLoss);
	
	public Integer insertBatch(List<CustomerLoss> customerLosses);
	
	
	public List<CustomerLoss> queryCustomerLosses(CustomerLossQuery customerLossQuery);


	@Update("update t_customer_loss set state=#{state},confirm_loss_time=now(),"
			+ "loss_reason=#{lossReason} where id=#{id}")
	public Integer updateCustomerLossState(@Param("id")Integer lossId, 
			@Param("state")Integer state,@Param("lossReason")String lossReason);
	
  
}