package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.vo.OrderDetail;

public interface OrderDetailDao {
	
	
	@Select("select  id,goods_name as goodsName,goods_num as  goodsNum ,unit,price,sum"
			+ " from t_order_details "
			+ " where order_id=#{orderId} and is_valid=1 ")
	public List<OrderDetail> queryOrderDetailsByOrderId(Integer orderId);

}
