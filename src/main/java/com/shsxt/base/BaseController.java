package com.shsxt.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.shsxt.crm.model.MessageModel;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

public class BaseController {
	
	/**
	 * 获取上下文路径
	 * @param request
	 */
	@ModelAttribute
	public void preMethod(HttpServletRequest request){
		request.setAttribute("ctx", request.getContextPath());
	}

	/**
	 * 操作成功非法
	 * @param message
	 * @return
	 */
	public MessageModel  success(String message){
		MessageModel messageModel=new MessageModel();
		messageModel.setMsg(message);
	    return messageModel;
	}
}
