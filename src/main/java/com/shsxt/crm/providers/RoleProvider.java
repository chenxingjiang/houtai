package com.shsxt.crm.providers;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;


public class RoleProvider {

	public String queryRolesByParamsSql(@Param("roleName") final String roleName){
		return new SQL(){
			{
			 	SELECT("id,role_name as roleName,role_remark as roleRemark,"
			 			+ "create_date as createDate,update_date as updateDate,"
			 			+ " is_valid as isValid");
				FROM("t_role");
				WHERE("is_valid=1");
				if(StringUtils.isNoneBlank(roleName)){
					WHERE("role_name like concat('%',#{roleName},'%')");
				}
			}
		}.toString();
		
	}
}
