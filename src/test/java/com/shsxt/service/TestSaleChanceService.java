package com.shsxt.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;

import com.shsxt.base.TestBase;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.vo.SaleChance;


public class TestSaleChanceService extends TestBase{


	@Resource
	private SaleChanceService saleChanceService;


	@Test
	public void test() {
		SaleChanceQuery saleChanceQuery=new SaleChanceQuery();
		saleChanceQuery.setState(0);
		saleChanceQuery.setCreateMan("sh");
		saleChanceQuery.setCustomerName("test");
		saleChanceQuery.setCreateDate("2017-06-01");
		Map<String, Object> map=saleChanceService.querySaleChancesByParams(saleChanceQuery);
		/*for(Entry<String, Object> entry:map.entrySet()){

	     }*/
		System.out.println(map.get("total"));
		List<SaleChance> saleChances=(List<SaleChance>) map.get("rows");
		if(null!=saleChances && saleChances.size()>0){
			for(SaleChance saleChance:saleChances){
	    		System.err.println(saleChance);
	    	}
		}

	}
	
	
	@Test
	public void test02(){
		SaleChance saleChance=new SaleChance();
		saleChance.setCustomerName("百度");
		saleChance.setChanceSource("百度推荐");
		saleChance.setCreateDate(new Date());
		saleChance.setUpdateDate(new Date());
		saleChance.setCreateMan("admin");
		saleChance.setDevResult(0);
		saleChance.setState(0);
		//saleChance.setLinkMan("李彦宏");
		saleChance.setLinkPhone("232432423432");
		saleChance.setCgjl("0.9");
		saleChanceService.insert(saleChance);
	}

}
