package com.shsxt.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shsxt.crm.dao.SaleChanceDao;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.vo.SaleChance;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:servlet-context.xml"})
public class TestSaleChanceDao {
	
	@Resource
	private SaleChanceDao saleChanceDao;

	@Test
	public void test01(){
		SaleChanceQuery saleChanceQuery=new SaleChanceQuery();
		saleChanceQuery.setState(0);
		saleChanceQuery.setCreateMan("sh");
		saleChanceQuery.setCustomerName("test");
		saleChanceQuery.setCreateDate("2017-06-01");
		List<SaleChance> saleChances= saleChanceDao.querySaleChancesByParams(saleChanceQuery);
		if(null!=saleChances && saleChances.size()>0){
	    	for(SaleChance saleChance:saleChances){
	    		System.err.println(saleChance);
	    	}
	    }
	}
	
	
	@Test
	public void test02(){
		SaleChance saleChance=new SaleChance();
		saleChance.setCustomerName("上海尚学堂");
		saleChance.setChanceSource("来自百度推荐");
		saleChance.setCreateDate(new Date());
		saleChance.setUpdateDate(new Date());
		saleChance.setCreateMan("admin");
		saleChance.setDevResult(0);
		saleChance.setState(0);
		saleChance.setLinkMan("老裴");
		saleChance.setLinkPhone("232432423432");
		saleChance.setCgjl("0.5");
		saleChanceDao.insert(saleChance);
	}
}
