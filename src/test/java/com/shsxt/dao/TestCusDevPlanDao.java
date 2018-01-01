package com.shsxt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.shsxt.base.TestBase;
import com.shsxt.crm.dao.CusDevPlanDao;
import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.vo.CusDevPlan;

public class TestCusDevPlanDao  extends TestBase{
	
	@Resource
	private CusDevPlanDao cusDevPlanDao;
	
	@Test
	public void test01(){
		CusDevPlanQuery cusDevPlanQuery=new CusDevPlanQuery();
		cusDevPlanQuery.setSaleChanceId(55);
		List<CusDevPlan> cusDevPlans= cusDevPlanDao.queryCusDevPlansByParams(cusDevPlanQuery);
	    if(null!=cusDevPlans&&cusDevPlans.size()>0){
	    	for(CusDevPlan cusDevPlan:cusDevPlans){
	    		System.out.println(cusDevPlan);
	    	}
	    }
	}

}
