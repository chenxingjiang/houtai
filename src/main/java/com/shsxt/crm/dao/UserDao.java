package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.dto.UserDto;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.vo.User;

public interface UserDao {
	
	/**
	 * 根据用户名查询用户记录
	 * @param userName
	 * @return
	 */
	public User queryUserByUserName(String userName);
	
	public User queryUserById(Integer userId);

	
	
	public Integer updateUserPassword(User user);
	
	
	/**
	 * 查询所有客户经理
	 * @return
	 */
	@Select("select  u.true_name as trueName,u.id "
			+ " from t_role r LEFT JOIN t_user_role ur on r.id=ur.role_id "
			+ "LEFT JOIN t_user u on ur.user_id=u.id  "
			+ " where r.role_name='客户经理' and r.is_valid and u.is_valid=1 ")
	public List<User> queryAllCustomerManager();
	
	
	public List<UserDto> queryUsersByParams(UserQuery userQuery);
	
	public Integer insert(User user);
	
	public Integer update(User user);
	
	public Integer delete(Integer uid);
	
	
	
	
}
