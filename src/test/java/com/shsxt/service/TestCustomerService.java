package com.shsxt.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;

import com.shsxt.base.TestBase;
import com.shsxt.crm.service.CustomerService;

public class TestCustomerService extends TestBase {
	
	@Resource
	private CustomerService customerService;



	@Test
	public void test() {
		customerService.updateCustomerLossState();
	}

}
