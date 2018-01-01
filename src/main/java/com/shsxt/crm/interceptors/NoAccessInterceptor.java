package com.shsxt.crm.interceptors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shsxt.base.AssertUtil;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.vo.User;

public class NoAccessInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private UserService userService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/**
		 * 
		 * 1.获取cookie 信息 userIdStr
		 *    值  null 0 非法
		 *      not  null  查询数据库 根据id 查询用户记录
		 *  2.未登录 后续处理(全局异常对未登录情况进行处理)
		 *      转到登录页面     
		 */
		Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
		AssertUtil.isTrue(null==userId||userId==0, CrmConstant.USER_NOT_LOGIN_MSG,CrmConstant.USER_NOT_LOGIN_CODE);
		User result= userService.queryUserById(userId);
		AssertUtil.isTrue(null==result, CrmConstant.USER_NOT_LOGIN_MSG,CrmConstant.USER_NOT_LOGIN_CODE);
		return true;
	}
	
	

}
