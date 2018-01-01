package com.shsxt.crm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.AssertUtil;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.dao.PermissionDao;
import com.shsxt.crm.dao.UserDao;
import com.shsxt.crm.dao.UserRoleDao;
import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.utils.Md5Util;
import com.shsxt.crm.utils.UserIDBase64;
import com.shsxt.crm.vo.SaleChance;
import com.shsxt.crm.vo.User;
import com.shsxt.crm.vo.UserRole;

@Service
public class UserService {


	@Resource
	private UserDao userDao;
	
	@Resource
	private UserRoleDao userRoleDao;
	
	@Resource
	private PermissionDao permissionDao;
	
	@Autowired
	private HttpSession session;

	/**
	 * 用户登陆业务方法
	 * @param userName
	 * @param userPwd
	 */
	public UserModel userLoginCheck(String userName,String userPwd){
		/**
		 * 1.参数非空校验
		 * 2.执行用户记录查询 根据用户名  获取记录
		 * 3.记录非空
		 *      3.1 记录是否有效
		 *      3.2 比对密码（加密前台 userPwd 值）
		 *      3.3 密码比对成功
		 *         用户登陆成功 
		 *            构建用户登陆成功 用户信息
		 */
		AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空!");
		AssertUtil.isTrue(StringUtils.isBlank(userPwd), "密码不能为空!");

		User user=userDao.queryUserByUserName(userName);
		AssertUtil.isTrue(null==user, "用户记录不存在!");
		AssertUtil.isTrue(user.getIsValid().equals(0), "该用户已注销");
		AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(userPwd)), "密码不正确!");
		/**
		 * 获取用户权限  根据用户拥有的角色
		 */
		List<String> permissions=permissionDao.queryPermissionsByUserId(user.getId());
		if(null!=permissions&&permissions.size()>0){
			session.setAttribute(CrmConstant.USER_PERMISSIONS, permissions);
		}
		return  buildUserInfo(user);
	}

	private UserModel buildUserInfo(User user) {
		UserModel userModel=new UserModel();
		userModel.setUserName(user.getUserName());
		userModel.setTrueName(user.getTrueName());
		userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
		return userModel;
	}

	/**
	 * 根据id 查询用户记录（有效记录）
	 * @param userId
	 * @return
	 */
	public User queryUserById(Integer userId){
		return userDao.queryUserById(userId);
	}

	/**
	 * 更新用户密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @param confirmPassword
	 */
	public void updateUserPassword(Integer userId,String oldPassword,String newPassword,
			String confirmPassword){
		/**
		 * 1.参数合法性校验
		 * 2.执行密码更新
		 */
		User user=queryUserById(userId);
		AssertUtil.isTrue(null==userId||null==(userId), "用户未登录！！！");
		AssertUtil.isTrue(StringUtils.isBlank(oldPassword), "原始密码非空!!!");
		AssertUtil.isTrue(!Md5Util.encode(oldPassword).equals(user.getUserPwd()), "原始密码错误！！！");
		AssertUtil.isTrue(StringUtils.isBlank(newPassword), "新密码不能为空!!!");
		AssertUtil.isTrue(StringUtils.isBlank(confirmPassword), "确认密码不能为空!!!");
		AssertUtil.isTrue(!newPassword.equals(confirmPassword), "新密码输入不一致！！！");
		user.setUserPwd(Md5Util.encode(newPassword));
		AssertUtil.isTrue(userDao.updateUserPassword(user)<1, CrmConstant.OPS_FAILED_MSG);
	}


	public List<User> queryAllCustomerManager(){
		return userDao.queryAllCustomerManager();
	}


	public Map<String, Object> queryUsersByParams(UserQuery userQuery){
		// 初始化分页环境
		PageHelper.startPage(userQuery.getPage(), userQuery.getRows());
		List<UserDto> userDtos= userDao.queryUsersByParams(userQuery);
		if(null !=userDtos&&userDtos.size()>0){
			for(UserDto userDto:userDtos){
				String roleIdStr=userDto.getRoleIdsStr();
				if(StringUtils.isNoneBlank(roleIdStr)){
					String[] roleIds=roleIdStr.split(",");
					for(String  rid:roleIds){
						userDto.getRoleIds().add(Integer.parseInt(rid));
					}
				}
			}
		}
		PageInfo<UserDto> pageInfo=new PageInfo<UserDto>(userDtos);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}	



	public void insert(User user){
		/**
		 * 1.参数校验
		 *  非空校验
		 *    用户名
		 *    密码
		 *    真实名称
		 *   可空
		 *      email
		 *      phone
		 * 2.用户名唯一
		 * 3.额外参数值设置
		 *     isValid
		 *     createDate
		 *     updateDate
		 * 4.执行用户记录添加
		 *    角色分配
		 *       如果分配角色 关联角色
		 */
		checkUserParams(user.getUserName(),user.getUserPwd(),user.getTrueName());
		User temp=userDao.queryUserByUserName(user.getUserName());
		AssertUtil.isTrue(null!=temp, "用户名已存在!");
		user.setIsValid(1);
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		user.setUserPwd(Md5Util.encode(user.getUserPwd()));
		AssertUtil.isTrue(userDao.insert(user)<1, CrmConstant.OPS_FAILED_MSG);
		
		int userId=user.getId();//获取主键
		if(null!=user.getRoleIds()&&user.getRoleIds().size()>0){
			relateRoles(userId, user.getRoleIds());
		}
	}

	/**
	 * 关联角色
	 * @param userId
	 * @param roleIds
	 */
	private void relateRoles(int userId, List<Integer> roleIds) {
	   /**
	    * 先执行删除操作  根据用户id 删除用户角色记录
	    * 执行用户角色记录添加操作
	    */
		int count=userRoleDao.queryUserRoleCountsByUserId(userId);
		if(count>0){
			AssertUtil.isTrue(userRoleDao.deleteUserRolesByUserId(userId)<count, CrmConstant.OPS_FAILED_MSG);
		}
		
		List<UserRole> userRoles=new ArrayList<UserRole>();
		for(Integer rid:roleIds){
			UserRole userRole=new UserRole();
			userRole.setCreateDate(new Date());
			userRole.setUpdateDate(new Date());
			userRole.setUserId(userId);
			userRole.setRoleId(rid);
			userRoles.add(userRole);
		}
		AssertUtil.isTrue(userRoleDao.insertBatch(userRoles)<roleIds.size(), CrmConstant.OPS_FAILED_MSG);
	}

	private void checkUserParams(String userName, String userPwd,
			String trueName) {
		
		AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名非空!");
		AssertUtil.isTrue(StringUtils.isBlank(userPwd), "密码非空!");
		AssertUtil.isTrue(StringUtils.isBlank(trueName), "真实名称非空!");
	}
	
	private void checkUserParams(String userName,String trueName,Integer id) {
		AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名非空!");
		AssertUtil.isTrue(StringUtils.isBlank(trueName), "真实名称非空!");
		AssertUtil.isTrue(null==id||null==userDao.queryUserById(id), "待更新用户记录不存在!");
	}
	
	public void update(User user){
		/**
		 * 1.参数校验
		 *    用户名
		 *    真实名称
		 *    id 记录必须存在
		 * 2.用户名唯一校验
		 * 3.额外字段设置
		 *     updateDate
		 * 4.执行更新
		 * 5.关联角色
		 */
		checkUserParams(user.getUserName(), user.getTrueName(), user.getId());
		User temp=userDao.queryUserByUserName(user.getUserName());
		AssertUtil.isTrue(null!=temp&&!temp.getId().equals(user.getId()), "该用户已存在!");
		user.setUpdateDate(new Date());
		AssertUtil.isTrue(userDao.update(user)<1, CrmConstant.OPS_FAILED_MSG);
		
		if(null!=user.getRoleIds()&&user.getRoleIds().size()>0){
			relateRoles(user.getId(), user.getRoleIds());
		}else{
			// 全部删除用户角色记录
			int count=userRoleDao.queryUserRoleCountsByUserId(user.getId());
			if(count>0){
				AssertUtil.isTrue(userRoleDao.deleteUserRolesByUserId(user.getId())<count, CrmConstant.OPS_FAILED_MSG);
			}
		}
	}
	
	public void deleteUser(Integer uid){
		/**
		 * 1.参数校验
		 *    id 记录不许存在
		 * 2.删除用户记录
		 *    级联删除用户角色表记录
		 */
		AssertUtil.isTrue(null==uid||null==userDao.queryUserById(uid), "待删除用户记录不存在!");
		int count=userRoleDao.queryUserRoleCountsByUserId(uid);
		if(count>0){
			AssertUtil.isTrue(userRoleDao.deleteUserRolesByUserId(uid)<count, CrmConstant.OPS_FAILED_MSG);
		}
		AssertUtil.isTrue(userDao.delete(uid)<1, CrmConstant.OPS_FAILED_MSG);
	}







}
