package com.shsxt.crm.vo;

import com.shsxt.base.BaseVo;

public class Role extends BaseVo{
	private Integer id;
	private String roleName;
	private String roleRemark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleRemark() {
		return roleRemark;
	}
	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}
	

}
