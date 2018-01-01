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
import com.shsxt.crm.dao.CusDevPlanDao;
import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.vo.CusDevPlan;


@Service
public class CusDevPlanService {
	
	@Resource
	private CusDevPlanDao cusDevPlanDao;
	
	@Resource
	private SaleChanceService saleChanceService;
	
	public Map<String, Object> queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery){
		PageHelper.startPage(cusDevPlanQuery.getPage(), cusDevPlanQuery.getRows());
		List<CusDevPlan> cusDevPlans= cusDevPlanDao.queryCusDevPlansByParams(cusDevPlanQuery);
		PageInfo<CusDevPlan> pageInfo=new PageInfo<CusDevPlan>(cusDevPlans);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	
	public void insert(CusDevPlan cusDevPlan ){
		/**
		 * 1.参数校验
		 *    saleChanceId 
		 *    planItem
		 *    exeAffect
		 * 2.设置额外字段值
		 *     createDate
		 *     updateDate
		 *     isValid
		 *  3.执行添加
		 */
		
		checkCusDevPlanParams(cusDevPlan.getSaleChanceId(),cusDevPlan.getPlanItem(),cusDevPlan.getExeAffect());
		cusDevPlan.setCreateDate(new Date());
		cusDevPlan.setUpdateDate(new Date());
		cusDevPlan.setIsValid(1);
		AssertUtil.isTrue(cusDevPlanDao.insert(cusDevPlan)<1, CrmConstant.OPS_FAILED_MSG);
	}


	private void checkCusDevPlanParams(Integer saleChanceId, String planItem,
			String exeAffect) {
		AssertUtil.isTrue(null==saleChanceId||null==saleChanceService.queryById(saleChanceId), "营销机会数据不存在!");
		AssertUtil.isTrue(StringUtils.isBlank(planItem), "计划项内容不能为空!");
		AssertUtil.isTrue(StringUtils.isBlank(exeAffect), "执行效果不能为空!");
	}
	
	
	private void checkCusDevPlanParams(Integer saleChanceId, String planItem,
			String exeAffect,Integer id) {
		checkCusDevPlanParams(saleChanceId,planItem,exeAffect);
		AssertUtil.isTrue(null==id||null==cusDevPlanDao.queryById(id), "待更新记录不存在");
	}
	
	public void update(CusDevPlan cusDevPlan ){
		/**
		 * 1.参数非空校验
		 *    saleChanceId 
		 *    planItem
		 *    exeAffect
		 *     id 记录存在校验
		 * 2.设置更新时间
		 * 3.执行更新
		 */
		checkCusDevPlanParams(cusDevPlan.getSaleChanceId(),cusDevPlan.getPlanItem(),cusDevPlan.getExeAffect(),cusDevPlan.getId());
		cusDevPlan.setUpdateDate(new Date());
		AssertUtil.isTrue(cusDevPlanDao.update(cusDevPlan)<1,CrmConstant.OPS_FAILED_MSG);
	}

	
	public void delete(Integer id){
		AssertUtil.isTrue(null==id||null==cusDevPlanDao.queryById(id), "待删除记录不存在!");
		AssertUtil.isTrue(cusDevPlanDao.delete(id)<1, CrmConstant.OPS_FAILED_MSG);
	}
	

	

}
