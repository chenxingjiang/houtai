package com.shsxt.crm.task;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.shsxt.crm.service.CustomerService;

@Component
public class CustomerLossTask {
	
	@Resource
	private CustomerService customerService;
	
	//@Scheduled(cron="0/2 * * * * * ?")
	public void task(){
		customerService.updateCustomerLossState();
	}
	
	/*public static void main(String[] args) {
		
	}
*/
}
