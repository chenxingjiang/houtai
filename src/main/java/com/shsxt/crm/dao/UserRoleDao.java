package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.vo.UserRole;

public interface UserRoleDao {
	
	
	public Integer queryUserRoleCountsByUserId(Integer userId);
	
	public Integer deleteUserRolesByUserId(Integer userId);
	
	public Integer insertBatch(List<UserRole> userRoles);
	
	@Select("select count(1) from t_user_role where role_id=#{roleId}")
	public Integer queryUserRoleCountsByRoleId(Integer roleId);
	@Delete("delete from t_user_role where role_id=#{roleId}")
	public Integer deleteUserRolesByRoleId(@Param("roleId")Integer roleId);
	
	

}
