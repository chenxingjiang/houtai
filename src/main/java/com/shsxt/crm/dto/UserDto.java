package com.shsxt.crm.dto;

import com.shsxt.crm.vo.User;

public class UserDto  extends User{
	private String roleName;
	
	private String roleIdsStr;
	
	

	public String getRoleIdsStr() {
		return roleIdsStr;
	}

	public void setRoleIdsStr(String roleIdsStr) {
		this.roleIdsStr = roleIdsStr;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

}
