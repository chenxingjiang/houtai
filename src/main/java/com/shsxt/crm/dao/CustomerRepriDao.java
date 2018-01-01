package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.shsxt.crm.vo.CustomerReprieve;

public interface CustomerRepriDao {

	
	public List<CustomerReprieve> CustomerReprievesByLossId(Integer lossId);
	
	
	@Insert("insert into t_customer_reprieve(loss_id,measure,is_valid,create_date,update_date)"
			+ " values(#{lossId},#{measure},#{isValid},#{createDate},#{updateDate})")
	public Integer insert(CustomerReprieve customerReprieve);
	
	@Update("update t_customer_reprieve set measure=#{measure} ,update_date=#{updateDate} where id=#{id} ")
	public Integer update(CustomerReprieve customerReprieve);
	
	@Delete("update t_customer_reprieve set is_valid=0 where id=#{id}")
	public Integer delete(Integer id);
	
	@Select("select id,measure,create_date as createDate,update_date as updateDate,loss_id as lossId"
			+ " from t_customer_reprieve where id=#{id}")
	public CustomerReprieve queryCustomerReprieveById(Integer id);
}
