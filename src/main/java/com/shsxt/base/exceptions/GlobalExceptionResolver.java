package com.shsxt.base.exceptions;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.model.MessageModel;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView modelAndView=getDefautlModelAndView();	
		/**
		 * 1.view
		 * 2.json
		 */
		if(handler instanceof HandlerMethod){
			/**
			 * 处理未登录情况
			 */
			if(ex instanceof ParamsException){
				ParamsException pe=(ParamsException) ex;
				if(pe.getCode().equals(CrmConstant.USER_NOT_LOGIN_CODE)){
					/**
					 * 跳转至登录页面
					 */
					modelAndView.addObject("ctx", request.getContextPath());
					modelAndView.addObject("errorMsg",pe.getErrorMsg());
					modelAndView.addObject("code", pe.getCode());
					modelAndView.addObject("uri", request.getRequestURI());
					modelAndView.setViewName("error");
					return modelAndView;
				}
			}
			
			HandlerMethod handlerMethod=(HandlerMethod) handler;
			Method method= handlerMethod.getMethod();
			ResponseBody responseBody= method.getAnnotation(ResponseBody.class);
			if(null !=responseBody){
				MessageModel messageModel=new MessageModel();
				messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
				messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
				if(ex instanceof ParamsException){
					ParamsException pe=(ParamsException) ex;
					messageModel.setCode(pe.getCode());
					messageModel.setMsg(pe.getErrorMsg());
				}
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/json;charset=utf-8");
				PrintWriter pw= null;
				try {
					pw=response.getWriter();
					
				} catch (IOException e) {
					e.printStackTrace();
					messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
					messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
				}finally{
					if(null!=pw){
						pw.write(JSON.toJSONString(messageModel));
						pw.flush();
						pw.close();
					}
				}
				return null;
			}else{
				if(ex instanceof ParamsException){
					ParamsException pe=(ParamsException) ex;
					modelAndView.addObject("uri", request.getRequestURI());
					modelAndView.addObject("errorMsg", pe.getErrorMsg());
					modelAndView.addObject("code", pe.getCode());
				}
				return modelAndView;
			}
			//return modelAndView;
		}else{
			return modelAndView;
		}
	}

	private ModelAndView getDefautlModelAndView() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("error");
		mv.addObject("msg", CrmConstant.OPS_FAILED_MSG);
		return mv;
	}

}
