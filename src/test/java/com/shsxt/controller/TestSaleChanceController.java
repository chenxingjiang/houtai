package com.shsxt.controller;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;

import com.shsxt.base.TestBase;
import com.shsxt.crm.controller.SaleChanceController;

public class TestSaleChanceController extends TestBase{

	@Resource
	private SaleChanceController saleChanceController;

	@Test
	public void test() {
		saleChanceController.querySaleChancesByParams(null);
	}

}
