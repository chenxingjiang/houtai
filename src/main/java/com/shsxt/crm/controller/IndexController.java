package com.shsxt.crm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shsxt.base.BaseController;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.vo.User;


/**
 * 
 * @author lp
 *  登录+主页 页面转发controller
 */
@Controller
public class IndexController extends BaseController{
	
	@Resource
	private UserService userService;
	
	/**
	 * 转发到登录页面
	 * @return
	 */
	@RequestMapping("index")
	public String index(){
		return "index";
	}
	
	
	/**
	 * 转发到主页面
	 * @return
	 */
	@RequestMapping("main")
	public String main(HttpServletRequest request){
		Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
		User user=userService.queryUserById(userId);
		/**
		 * userName
		 * trueName
		 */
		if(null!=user){
			request.setAttribute("userName", user.getUserName());
			request.setAttribute("trueName", user.getTrueName());
		}
		return "main";
	}
	
	

}
