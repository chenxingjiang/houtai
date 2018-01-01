package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.vo.Permission;

public interface PermissionDao {
	
	@Select("select count(1) from t_permission where role_id=#{rid}")
	public Integer queryPermissionCountByRid(@Param("rid")Integer rid);
	
	@Delete("delete from t_permission where role_id=#{rid}")
	public Integer deletePermissionByRid(@Param("rid")Integer rid);
	
	public Integer insertBatch(List<Permission> permissions);
	
	
	@Select("select module_id from t_permission where role_id=#{rid}")
	public List<Integer> queryPermissionModuleIdsByRid(@Param("rid")Integer rid);
	
	@Select("select acl_value FROM t_user u"
			+ " LEFT JOIN t_user_role ur ON u.id = ur.user_id"
			+ " LEFT JOIN t_permission p ON ur.role_id = p.role_id"
			+ " where u.id=#{uid} and u.is_valid=1")
	public List<String> queryPermissionsByUserId(Integer uid);

}
