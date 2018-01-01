package com.shsxt.crm.proxy;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shsxt.base.exceptions.ParamsException;
import com.shsxt.crm.RequestPermission;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.dao.PermissionDao;
import com.shsxt.crm.dao.UserDao;
import com.shsxt.crm.utils.LoginUserUtil;

@Component
@Aspect
public class PermissionProxy {
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private UserDao userDao;
	/**
	 * 1.切入点
	 * 2.通知
	 */
	
	/*@Pointcut("execution (* com.*.*.controller.*.*(..))")
	public void cut(){};*/
	
	@Before("execution (* com.*.*.controller.*.*(..))")
	public  void preMethod(JoinPoint joinPoint ){
		/**
		 * 权限认证
		 *  1.用户拥有的权限
		 *  2.访问时 资源存在权限是多少
		 *  3.权限比对
		 *      包含访问的资源权限值 
		 *          用户拥有操作该资源的权限
		 *       不包含
		 *           用户没有该权限   权限认证失败 无权限
		 */
		
		MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
		Method method= methodSignature.getMethod();// 获取访问的方法
		RequestPermission requestPermission= method.getAnnotation(RequestPermission.class);
		if(null!=requestPermission){
			String requestVal=requestPermission.requestVal();
			Integer userId=LoginUserUtil.releaseUserIdFromCookie(request);
			if(null==userId||null==userDao.queryUserById(userId)){
				throw new ParamsException("未登录", CrmConstant.USER_NOT_LOGIN_CODE);
			}
			List<String> permissions= (List<String>) request.getSession().getAttribute(CrmConstant.USER_PERMISSIONS);
			if(null==permissions){
				permissions=permissionDao.queryPermissionsByUserId(userId);
			}		
			if(null!=permissions&&permissions.size()>0){
				// 权限放入session
				request.getSession().setAttribute(CrmConstant.USER_PERMISSIONS, permissions);
				/**
				 * 拥有权限
				 */
				if(!permissions.contains(requestVal)){
					throw new ParamsException("没有该权限!",300);
				}
				System.err.println("该用户用户操作该资源的权限。。。");
			}
		}else{
			return;
		}
		
		  
		
		
		
		
		
	}
	

}
