package com.shsxt.crm.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.base.exceptions.ParamsException;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.CookieUtil;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.vo.User;

/**
 * 
 * @author lp
 * 用户管理模块
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController{

	@Resource
	private UserService userService;

	/*@RequestMapping("userLogin")
	@ResponseBody
	public MessageModel userLogin(String userName,String userPwd){
		MessageModel messageModel=new MessageModel();
		try {
			UserModel userModel= userService.userLoginCheck(userName, userPwd);
			messageModel.setResult(userModel);
		} catch (ParamsException e) {
			e.printStackTrace();
			messageModel.setCode(e.getCode());
			messageModel.setMsg(e.getErrorMsg());
		}catch (Exception e) {
			e.printStackTrace();
			messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
			messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
		}
		return messageModel;
	}*/

	@RequestMapping("userLogin")
	@ResponseBody
	public MessageModel userLogin(String userName,String userPwd){
		MessageModel messageModel=new MessageModel();
		UserModel userModel= userService.userLoginCheck(userName, userPwd);
		messageModel.setResult(userModel);
		return messageModel;
	}

	@RequestMapping("updateUserPassword")
	@ResponseBody
	public MessageModel updateUserPassword(String oldPassword,String newPassword,
			String confirmPassword,HttpServletRequest request){
		MessageModel messageModel=new MessageModel();
		Integer userId=LoginUserUtil.releaseUserIdFromCookie(request);
		try {
			userService.updateUserPassword(userId, oldPassword, newPassword, confirmPassword);
		} catch (ParamsException e) {
			e.printStackTrace();
			messageModel.setCode(e.getCode());
			messageModel.setMsg(e.getErrorMsg());
		}catch (Exception e) {
			e.printStackTrace();
			messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
			messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
		}
		return messageModel;
	}
	
	@RequestMapping("queryAllCustomerManager")
	@ResponseBody
	public List<User> queryAllCustomerManager(){
		return userService.queryAllCustomerManager();
	} 

	
	@RequestMapping("queryUsersByParams")
	@ResponseBody
	public Map<String, Object> queryUsersByParams(UserQuery userQuery){
		return userService.queryUsersByParams(userQuery);
	}
	
	
	@RequestMapping("index")
	public String index(){
		return "user";
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public MessageModel insert(User user){
		userService.insert(user);
		return success("用户记录添加成功!");
	}
	
	@RequestMapping("update")
	@ResponseBody
	public MessageModel update(User user){
		userService.update(user);
		return success("用户记录更新成功!");
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public MessageModel delete(Integer[] ids){
		userService.deleteUser(ids[0]);
		return success("用户记录删除成功!");
	}
	
	@RequestMapping("userLoginOut")
	@ResponseBody
	public MessageModel userLoginOut(HttpServletRequest request,HttpServletResponse response){
		CookieUtil.deleteCookie("userIdStr", request, response);
		CookieUtil.deleteCookie("userName", request, response);
		CookieUtil.deleteCookie("trueName", request, response);
		request.getSession().removeAttribute(CrmConstant.USER_PERMISSIONS);
		return success("用户退出成功");
	}


}
