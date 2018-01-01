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
import com.shsxt.crm.dao.SaleChanceDao;
import com.shsxt.crm.dto.DevResult;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.vo.SaleChance;

@Service
public class SaleChanceService {
	
	
	@Resource
	private SaleChanceDao saleChanceDao;
	
	/**
	 * 分页查询营销机会数据
	 * @param saleChanceQuery
	 * @return
	 */
	
	public Map<String, Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery){
		// 初始化分页环境
		PageHelper.startPage(saleChanceQuery.getPage(), saleChanceQuery.getRows());
		List<SaleChance> saleChances= saleChanceDao.querySaleChancesByParams(saleChanceQuery);
		PageInfo<SaleChance> pageInfo=new PageInfo<SaleChance>(saleChances);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	
	public void insert(SaleChance saleChance){
		/**
		 * 1.参数合法性校验
		 *    客户名称
		 *    成功几率
		 *    联系人
		 *    联系电话
		 * 2.执行添加逻辑
		 *    添加时间
		 *    更新时间
		 *    有效值 默认有效
		 *    开发状态 默认为未开发  0-未开发  1-开发中  2-开发成功  3-开发失败
		 *    分配状态 state 如果分配 state=1  否则 state=0
		 *    assignMan 如果存在值  state=1  assignTime=now()  不存在 stater=0
		 *    createMan  系统获取创建人
		 */
		checkSaleChanceParams(saleChance.getCustomerName(),saleChance.getCgjl(),saleChance.getLinkMan(),saleChance.getLinkPhone());
		saleChance.setCreateDate(new Date());
		saleChance.setUpdateDate(new Date());
		saleChance.setIsValid(1);
		saleChance.setDevResult(DevResult.UN_DEV.getDevResult());
		saleChance.setState(0);
		if(StringUtils.isNoneBlank(saleChance.getAssignMan())){
			saleChance.setState(1);// 已分配
			saleChance.setAssignTime(new Date());// 设置分配时间
		}
		AssertUtil.isTrue(saleChanceDao.insert(saleChance)<1, CrmConstant.OPS_FAILED_MSG);
	}


	private void checkSaleChanceParams(String customerName, String cgjl,
			String linkMan, String linkPhone) {
		AssertUtil.isTrue(StringUtils.isBlank(customerName), "客户名称不能为空!");
		// 0-1 成功几率 值为数字  范围 0-1  0.1 0.5  0.8  1 
		AssertUtil.isTrue(StringUtils.isBlank(cgjl), "成功几率值不合法!");
		AssertUtil.isTrue(StringUtils.isBlank(linkMan), "联系人不能为空!");
		// linkPhone  必须为手机号  手机号|座机号
		AssertUtil.isTrue(StringUtils.isBlank(linkPhone), "手机号非法!");
	}
	
	
	
	public void updateSaleChance(SaleChance saleChance){
		/**
		 * 1.参数合法性校验
		 *    客户名称
		 *    成功几率
		 *    联系人
		 *    联系电话  
		 *    id 记录必需存在
		 *  2.执行更新  
		 */
		checkSaleChanceParams(saleChance.getCustomerName(),saleChance.getCgjl(),
				saleChance.getLinkMan(),saleChance.getLinkPhone());
		AssertUtil.isTrue(null==saleChance.getId()||null==saleChanceDao.queryById(saleChance.getId()), "待更新记录不存在!");
		if(StringUtils.isNoneBlank(saleChance.getAssignMan())){
			saleChance.setState(1);
			saleChance.setAssignTime(new Date());
		}
		saleChance.setUpdateDate(new Date());
		AssertUtil.isTrue(saleChanceDao.update(saleChance)<1, CrmConstant.OPS_FAILED_MSG);
	}
	
	
	public void deleteSaleChance(Integer[] ids){
		AssertUtil.isTrue(null==ids||ids.length==0, "请选择删除记录!");
		AssertUtil.isTrue(saleChanceDao.deleteSaleChance(ids)<1, CrmConstant.OPS_FAILED_MSG);
	}
	
	
	public SaleChance queryById(Integer sid){
		return saleChanceDao.queryById(sid);
	}


	public void updateSaleChanceDevResult(Integer sid, Integer devResult) {
		AssertUtil.isTrue(null==sid||null==queryById(sid), "待更新的机会记录存在!");
		DevResult[] devResults= DevResult.values();
		int count=0;
		for(DevResult temp:devResults){
			System.out.println(temp.getDevResult());
			if(!temp.getDevResult().equals(devResult)){
				count=count+1;
			}
		}
		System.out.println("count="+count);
		AssertUtil.isTrue(count==devResults.length, "开发状态值非法!");	
		AssertUtil.isTrue(saleChanceDao.updateSaleChanceDevResult(sid,devResult)<1, CrmConstant.OPS_FAILED_MSG);
	}

}
