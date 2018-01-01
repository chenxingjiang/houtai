package com.shsxt.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.AssertUtil;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.dao.CustomerServeDao;
import com.shsxt.crm.dto.CustomerServeDto;
import com.shsxt.crm.dto.ServeState;
import com.shsxt.crm.dto.ServeTypeDto;
import com.shsxt.crm.query.CustomerServeQuery;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.vo.CustomerServe;
import com.shsxt.crm.vo.SaleChance;

@Service
public class CustomerServeService {
	
	@Resource
	private CustomerServeDao customerServeDao;
	
	@Resource
	private UserService userService;
	
	
	public void insert(CustomerServe customerServe){
		/**
		 * 1.参数校验
		 *    服务类型（必须设置）
		 *    客户名称（必须提供）
		 *    服务概要
		 *    服务请求内容（必须存在）
		 * 2.额外字段值设置
		 *     isValid
		 *     createDate
		 *     updateDate
		 *     state ：服务状态   新创建、已分配、已处理、已反馈、已归档
		 *     createMan  服务创建人
		 * 3.执行记录添加
		 */
		cheakCustomerServeParams(customerServe.getServeType(),customerServe.getCustomer(),customerServe.getServiceRequest());
		customerServe.setIsValid(1);
		customerServe.setCreateDate(new Date());
		customerServe.setUpdateDate(new Date());
		customerServe.setState(ServeState.SERVE_NEW_CREATED.getState());
		AssertUtil.isTrue(customerServeDao.insert(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
	}


	private void cheakCustomerServeParams(String serveType, String customer,
			String serviceRequest) {
		AssertUtil.isTrue(StringUtils.isBlank(serveType), "服务类型非空!");
		AssertUtil.isTrue(StringUtils.isBlank(customer), "客户名称非空!");
		AssertUtil.isTrue(StringUtils.isBlank(serviceRequest), "内容非空!");
	}
	private void cheakCustomerServeParams(String serveType, String customer,
			String serviceRequest,Integer id){
		 cheakCustomerServeParams(serveType,customer,serviceRequest);
		 AssertUtil.isTrue(null==id||null==customerServeDao.queryById(id), "待更新服务记录不存在!");
	}
	
	
	
	
	public void update(CustomerServe customerServe,HttpServletRequest request){
		/**
		 * 1.参数基本校验
		 *    服务类型（必须设置）
		 *    客户名称（必须提供）
		 *    服务请求内容（必须存在）
		 *    id 记录必须存在
		 * 2.额外参数设置
		 *     updateDate
		 *     状态
		 *        分配状态:设置分配时间
		 *        处理状态:设置处理人  处理时间
		 *        反馈:设置处理结果  满意度  设置state 为归档状态 
		 *        归档:
		 *  3.执行更新
		 */
		cheakCustomerServeParams(customerServe.getServeType(),customerServe.getCustomer()
				,customerServe.getServiceRequest(),customerServe.getId());
		customerServe.setUpdateDate(new Date());
		// 已分配状态
		if(customerServe.getState().equals(ServeState.SERVE_ASSIGNED.getState())){
			customerServe.setAssignTime(new Date());
		}
		
		
		if(customerServe.getState().equals(ServeState.SERVE_PROCEED.getState())){
			customerServe.setServiceProcePeople(userService.queryUserById(LoginUserUtil.releaseUserIdFromCookie(request)).getTrueName());
			customerServe.setServiceProceTime(new Date());
		}
		
		if(customerServe.getState().equals(ServeState.SERVE_FEED_BACK.getState())){
			customerServe.setState(ServeState.SERVE_ARCHIVE.getState());
		}
		AssertUtil.isTrue(customerServeDao.update(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
	}
	
	
	public Map<String, Object> queryCustomerServesByParams(CustomerServeQuery  customerServeQuery){
		// 初始化分页环境
		PageHelper.startPage(customerServeQuery.getPage(), customerServeQuery.getRows());
		List<CustomerServe> customerServes= customerServeDao.queryCustomerServesByParams(customerServeQuery);
		PageInfo<CustomerServe> pageInfo=new PageInfo<CustomerServe>(customerServes);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	
	public Map<String, Object> queryCustomerServeDtos(){
		List<CustomerServeDto> customerServeDtos= customerServeDao.queryCustomerServeDtos();
		/**
		 * 1.服务类别数据
		 * 2.每个类别 总数
		 */
		String[] types=null;
		ServeTypeDto[] serveTypeDtos=null;
		Map<String, Object> map=new HashMap<String,Object>();	
		map.put("code", 300);
		if(null!=customerServeDtos&&customerServeDtos.size()>0){
			types=new String[customerServeDtos.size()];
			serveTypeDtos=new ServeTypeDto[customerServeDtos.size()];
			for(Integer i=0;i<customerServeDtos.size();i++){
				types[i]=customerServeDtos.get(i).getType();
				ServeTypeDto serveTypeDto=new ServeTypeDto();
				serveTypeDto.setName(customerServeDtos.get(i).getType());
				serveTypeDto.setValue(customerServeDtos.get(i).getCount());
				serveTypeDtos[i]=serveTypeDto;
			}
			map.put("code", 200);
			map.put("types", types);
			map.put("serveTypeDtos", serveTypeDtos);
		}
		return map;
	}
	
	
	

}
