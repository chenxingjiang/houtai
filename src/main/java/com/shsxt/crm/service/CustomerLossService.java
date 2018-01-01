package com.shsxt.crm.service;

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
import com.shsxt.crm.dao.CustomerLossDao;
import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.vo.CustomerLoss;

@Service
public class CustomerLossService {
	@Resource
	private CustomerLossDao customerLossDao;
	
	public Map<String, Object> queryCustomerLossesByParams(CustomerLossQuery  customerLossQuery){
		// 初始化分页环境
		PageHelper.startPage(customerLossQuery.getPage(), customerLossQuery.getRows());
		List<CustomerLoss> customerLosses= customerLossDao.queryCustomerLosses(customerLossQuery);
		PageInfo<CustomerLoss> pageInfo=new PageInfo<CustomerLoss>(customerLosses);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	public Map<String, Object> queryCustomerLossById(Integer lossId){
		return customerLossDao.queryById(lossId);
	}

	public void updateCustomerLossState(Integer lossId, Integer state,
			String lossReason) {
		checkParams(lossId,state,lossReason);
		AssertUtil.isTrue(customerLossDao.updateCustomerLossState(lossId,state,lossReason)<1, CrmConstant.OPS_FAILED_MSG);
	}

	private void checkParams(Integer lossId, Integer state, String lossReason) {
		Map<String, Object> map=queryCustomerLossById(lossId);	
		AssertUtil.isTrue(null==lossId||null==map||map.isEmpty(), "流失记录不存在");
		AssertUtil.isTrue(null==state||state!=1, "状态值非法!");
		AssertUtil.isTrue(StringUtils.isBlank(lossReason), "流失原因非空!");
	}
	

}
