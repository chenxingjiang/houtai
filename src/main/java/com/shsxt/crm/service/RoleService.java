package com.shsxt.crm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.AssertUtil;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.dao.ModuleDao;
import com.shsxt.crm.dao.PermissionDao;
import com.shsxt.crm.dao.RoleDao;
import com.shsxt.crm.dao.UserRoleDao;
import com.shsxt.crm.query.RoleQuery;
import com.shsxt.crm.vo.Module;
import com.shsxt.crm.vo.Permission;
import com.shsxt.crm.vo.Role;
import com.shsxt.crm.vo.SaleChance;

@Service
public class RoleService {

	@Resource
	private RoleDao roleDao;
	
	
	@Resource
	private PermissionDao permissionDao;
	
	@Resource
	private ModuleDao moduleDao;
	
	@Resource
	private UserRoleDao userRoleDao;
	public List<Role> queryAllRoles(){
		return roleDao.queryAllsRoles();
	}
	
	
	public Map<String,Object> queryRolesByParama(RoleQuery roleQuery){
		PageHelper.startPage(roleQuery.getPage(), roleQuery.getRows());
		List<Role> roles= roleDao.queryRolesByParams(roleQuery.getRoleName());
		PageInfo<Role> pageInfo=new PageInfo<Role>(roles);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	public void insert(Role role){
		/*
		 * 1.参数校验
		 *    角色名非空
		 * 2.角色名重复性校验
		 * 3.额外字段值设置
		 * 4.执行添加
		 */
		AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名非空!");
		AssertUtil.isTrue(null!=roleDao.queryRoleByRoleName(role.getRoleName()), "角色已存在!");
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		role.setIsValid(1);
		AssertUtil.isTrue(roleDao.insert(role)<1, CrmConstant.OPS_FAILED_MSG);
	}
	
	
	public void update(Role role){
		/*
		 * 1.参数校验
		 *    角色名非空
		 *    id 记录必须存在
		 * 2.角色名重复性校验
		 * 3.额外字段值设置
		 *    updateDate
		 * 4.执行更新
		 */
		AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名非空!");
		AssertUtil.isTrue(null==role.getId()||null==roleDao.queryRoleById(role.getId()), "待更新的角色记录不存在!");
		Role temp=roleDao.queryRoleByRoleName(role.getRoleName());
		AssertUtil.isTrue(null!=temp&&!temp.getId().equals(role.getId()), "角色名已存在!");
		role.setUpdateDate(new Date());
		AssertUtil.isTrue(roleDao.update(role)<1, CrmConstant.OPS_FAILED_MSG);
	}
	
	public void delete(Integer id){
		AssertUtil.isTrue(null==id||null==roleDao.queryRoleById(id), "待删除的角色记录不存在!");
	    /**
	     * 删除角色记录
	     *    级联删除用户角色记录
	     */
		int count=userRoleDao.queryUserRoleCountsByRoleId(id);
		if(count>0){
			AssertUtil.isTrue(userRoleDao.deleteUserRolesByRoleId(id)<count, CrmConstant.OPS_FAILED_MSG);
		}
		AssertUtil.isTrue(roleDao.delete(id)<1, CrmConstant.OPS_FAILED_MSG);
	}


	public void addPermission(Integer rid, Integer[] moduleIds) {
		/**
		 * 1.参数合法性校验
		 *    rid 角色记录必须存在
		 *    moduleIds  可空
		 * 2.删除原始权限
		 *    查询原始权限
		 *         原始权限存在  执行删除操作
		 * 3. 执行批量添加
		 *     根据moduleId  查询每个模块  权限值 
		 *     List<Permission>        
		 */
		AssertUtil.isTrue(null==rid||null==roleDao.queryRoleById(rid), "待授权的角色不存在!");
		int count=permissionDao.queryPermissionCountByRid(rid);
		if(count>0){
			AssertUtil.isTrue(permissionDao.deletePermissionByRid(rid)<count, CrmConstant.OPS_FAILED_MSG);
		}
		List<Permission> permissions=null;
		if(null!=moduleIds&&moduleIds.length>0){
			/**
			 * 执行批量添加
			 */
			permissions=new ArrayList<Permission>();
			Module module=null;
			for(Integer moduleId:moduleIds){
				 module=moduleDao.queryModuleById(moduleId);
				 module.getOptValue();
				 Permission permission=new Permission();
				 if(null !=module){
					 permission.setAclValue(module.getOptValue());
				 }
				 permission.setRoleId(rid);
				 permission.setModuleId(moduleId);
				 permission.setCreateDate(new Date());
				 permission.setUpdateDate(new Date());
				 permissions.add(permission);
			}
			AssertUtil.isTrue(permissionDao.insertBatch(permissions)<moduleIds.length, CrmConstant.OPS_FAILED_MSG);
		}
	}
}
