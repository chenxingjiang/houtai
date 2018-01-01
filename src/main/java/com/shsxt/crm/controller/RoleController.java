package com.shsxt.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.RequestPermission;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.RoleQuery;
import com.shsxt.crm.service.RoleService;
import com.shsxt.crm.vo.Role;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
	@Resource
	private RoleService roleService;
	
	
	@RequestMapping("index")
	public String index(){
		return "role";
	}
	
	@RequestMapping("queryAllRoles")
	@ResponseBody
	public List<Role> queryAllRoles(){
		return roleService.queryAllRoles();
	}
	
	@RequestMapping("queryRolesByParams")
	@ResponseBody
	public Map<String,Object> queryRolesByParams(RoleQuery roleQuery){
		return roleService.queryRolesByParama(roleQuery);
	}
	
	@RequestPermission(requestVal="5010")
	@RequestMapping("insert")
	@ResponseBody
	public MessageModel insert(Role role){
		System.err.println("添加角色信息");
		roleService.insert(role);
		return success("角色记录添加成功!");
	}
	
	@RequestMapping("update")
	@ResponseBody
	public MessageModel update(Role role){
		roleService.update(role);
		return success("角色记录更新成功!");
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public MessageModel delete(Integer[] ids){
		roleService.delete(ids[0]);
		return success("角色记录删除成功!");
	}
	
	@RequestMapping("addPermission")
	@ResponseBody
	public MessageModel addPermission(Integer rid,Integer[] moduleIds){
		 roleService.addPermission(rid,moduleIds);
		 return success("角色授权成功");
	}

}
