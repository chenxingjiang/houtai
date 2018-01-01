package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.vo.CusDevPlan;

public interface CusDevPlanDao {
	
	public List<CusDevPlan> queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery);
	
	
	
	@Insert("insert into t_cus_dev_plan(sale_chance_id,"
			+ "plan_item,plan_date,exe_affect,create_date,update_date,is_valid)"
			+ "values(#{saleChanceId},#{planItem},#{planDate},#{exeAffect},#{createDate},#{updateDate},#{isValid})")
	public Integer insert(CusDevPlan cusDevPlan);
	
	
	@Update("update t_cus_dev_plan set plan_item=#{planItem},plan_date=#{planDate},exe_affect=#{exeAffect}"
			+ " where id=#{id}")
	public Integer update(CusDevPlan cusDevPlan);
	
	@Select("select id,plan_item as planItem,plan_date as planDate "
			+ " from t_cus_dev_plan where id=#{id} and is_valid=1")
	public CusDevPlan queryById(Integer id);
	
	@Delete("update t_cus_dev_plan set is_valid=0 where id=#{id}")
	public Integer delete(Integer id);
	
	

}
