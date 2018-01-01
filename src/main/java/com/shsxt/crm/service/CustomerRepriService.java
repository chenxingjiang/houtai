package com.shsxt.crm.service;

import java.util.Date;
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
import com.shsxt.crm.dao.CustomerRepriDao;
import com.shsxt.crm.query.CustomerRepriQuery;
import com.shsxt.crm.vo.CustomerReprieve;

@Service
public class CustomerRepriService {
	@Resource
	private CustomerRepriDao customerRepriDao;
	
	@Resource
	private CustomerLossService customerLossService;

	
	public Map<String, Object> customerReprievesByLossId(CustomerRepriQuery customerRepriQuery){
		// 初始化分页环境
		PageHelper.startPage(customerRepriQuery.getPage(), customerRepriQuery.getRows());
		List<CustomerReprieve> customerReprieves= customerRepriDao.CustomerReprievesByLossId(customerRepriQuery.getLossId());
		PageInfo<CustomerReprieve> pageInfo=new PageInfo<CustomerReprieve>(customerReprieves);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	
	public void insert(CustomerReprieve customerReprieve){
		/**
		 * 1.参数校验
		 *    处理措施非空
		 *    流失记录必须存在
		 * 2.额外字段设置请求值
		 *    isValid
		 *    createDate
		 *    updateDate
		 * 3.执行添加
		 */
		checkCustomerRepriParams(customerReprieve.getMeasure(),customerReprieve.getLossId());
		customerReprieve.setCreateDate(new Date());
		customerReprieve.setUpdateDate(new Date());
		customerReprieve.setIsValid(1);
		AssertUtil.isTrue(customerRepriDao.insert(customerReprieve)<1, CrmConstant.OPS_FAILED_MSG);
	}

	private void checkCustomerRepriParams(String measure, Integer lossId) {
		AssertUtil.isTrue(StringUtils.isBlank(measure), "处理措施非空!");
		Map<String, Object> map=customerLossService.queryCustomerLossById(lossId);
		AssertUtil.isTrue(null==lossId||null==map||map.isEmpty(), "流失记录不存在!");
	}
	
	private void checkCustomerRepriParams(String measure, Integer lossId,Integer id) {
		checkCustomerRepriParams(measure, lossId);
		AssertUtil.isTrue(null==id||null==customerRepriDao.queryCustomerReprieveById(id), "待更新记录不存在!");
	}
	
	
	
	public void update(CustomerReprieve customerReprieve){
		/**
		 * 1.参数校验
		 *    处理措施非空
		 *    流失记录必须存在
		 *    当前id 记录必须存在 
		 * 2.设置额外字段
		 *    updateDate
		 * 3.执行更新
		 */
		checkCustomerRepriParams(customerReprieve.getMeasure(), customerReprieve.getLossId(), customerReprieve.getId());
		customerReprieve.setUpdateDate(new Date());
		AssertUtil.isTrue(customerRepriDao.update(customerReprieve)<1, CrmConstant.OPS_FAILED_MSG);
	}
	
	public void delete(Integer id){
		AssertUtil.isTrue(null==id||null==customerRepriDao.queryCustomerReprieveById(id), "待删除记录不存在!");
		AssertUtil.isTrue(customerRepriDao.delete(id)<1, CrmConstant.OPS_FAILED_MSG);
	}
}
