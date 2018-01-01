package com.shsxt.crm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.AssertUtil;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.dao.CustomerDao;
import com.shsxt.crm.dao.CustomerLossDao;
import com.shsxt.crm.dao.CustomerOrderDao;
import com.shsxt.crm.dto.CustomerDto;
import com.shsxt.crm.query.CustomerDtoQuery;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.utils.MathUtil;
import com.shsxt.crm.vo.Customer;
import com.shsxt.crm.vo.CustomerLoss;
import com.shsxt.crm.vo.CustomerOrder;

@Service
public class CustomerService {
	@Resource
	private CustomerDao customerDao;
	
	@Resource
	private CustomerOrderDao customerOrderDao;
	
	@Resource
	private CustomerLossDao customerLossDao;
	
	
	public List<Customer> queryAllCustomers(){
		return customerDao.queryAllCustomers();
	}

	
	
	public Map<String, Object> queryCustomersByParams(CustomerQuery customerQuery){
		PageHelper.startPage(customerQuery.getPage(), customerQuery.getRows());
		List<Customer> customers=  customerDao.queryCustomersByParams(customerQuery);
		PageInfo<Customer> pageInfo=new PageInfo<Customer>(customers);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	public void insert(Customer customer){
		/**
		 * 1.参数合法性校验  客户名称不可重复
		 *    客户姓名
		 *    客户电话
		 *    客户法人代表
		 * 2.额外参数补充
		 *    添加时间
		 *    更新时间
		 *    有效字段
		 *    是否流失  state=0
		 * 3.执行添加
		 */
		checkCustomerParams(customer.getName(),customer.getPhone(),customer.getFr());
		customer.setCreateDate(new Date());
		customer.setUpdateDate(new Date());
		customer.setIsValid(1);
		customer.setState(0);
		customer.setKhno(MathUtil.genereateKhCode());//  20170901234232
		AssertUtil.isTrue(customerDao.insert(customer)<1, CrmConstant.OPS_FAILED_MSG);
	}



	private void checkCustomerParams(String name, String phone, String fr) {
		AssertUtil.isTrue(StringUtils.isBlank(name), "客户名称不能为空!");
		/**
		 * 136
		 * 137
		 * 138
		 * 187
		 * 186
		 */
		AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号非空!");
		AssertUtil.isTrue(StringUtils.isBlank(fr), "法人代表非空!");
	}
	
	private void checkCustomerParams(String name, String phone, String fr,Integer id){
		checkCustomerParams(name,phone,fr);
		AssertUtil.isTrue(null==id||null==customerDao.queryById(id), "待更新记录不存在!");
	}
	
	public void update(Customer customer){
		/**
		 * 1. 参数合法性校验  客户名称不可重复
		 *    客户姓名
		 *    客户电话
		 *    客户法人代表
		 *    id 记录非空
		 * 2. 字段值更改 
		 *     更新时间
		 * 3.执行更新
		 */
		checkCustomerParams(customer.getName(), customer.getPhone(), customer.getFr(), customer.getId());
		customer.setUpdateDate(new Date());
		AssertUtil.isTrue(customerDao.update(customer)<1, CrmConstant.OPS_FAILED_MSG);
	}
	
	public static void main(String[] args) {
		//System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		System.out.println(MathUtil.genereateKhCode());
	}
	
	
	public void deleteCustoemrs(Integer[] ids){
		AssertUtil.isTrue(null!=ids&&ids.length==0, "请选择待删除记录!");
		AssertUtil.isTrue(customerDao.deleteCustomers(ids)<1, CrmConstant.OPS_FAILED_MSG);
	}
	
	
	public Customer queryById(Integer cid){
		return customerDao.queryById(cid);
	}
	
	
	
	
	public void updateCustomerLossState(){
		List<Customer> customers= customerDao.queryLossCustomers();
		List<CustomerLoss> customerLosses=null;
		Integer[] cusIds=null;
		if(null!=customers&&customers.size()>0){
			customerLosses=new ArrayList<CustomerLoss>();
			cusIds=new Integer[customers.size()];
			for(Integer i=0;i<customers.size();i++){
				Customer customer=customers.get(i);
				CustomerLoss customerLoss=new CustomerLoss();
				customerLoss.setCusName(customer.getName());
				customerLoss.setCusNo(customer.getKhno());
				customerLoss.setCusManager(customer.getCusManager());
				customerLoss.setCreateDate(new Date());
				customerLoss.setUpdateDate(new Date());
				customerLoss.setIsValid(1);
				customerLoss.setState(0);// 暂缓流失状态
				CustomerOrder customerOrder=customerOrderDao.queryCustomerLastOrderByCusId(customer.getId());
				if(null!=customerOrder){
					customerLoss.setLastOrderTime(customerOrder.getOrderDate());// 设置最后一次下单日期
				}
				customerLosses.add(customerLoss);
				cusIds[i]=customer.getId();
			}
			AssertUtil.isTrue(customerLossDao.insertBatch(customerLosses)<1||
					customerDao.updateCustomerLossState(cusIds)<1, CrmConstant.OPS_FAILED_MSG);
		}	
	}
	
	
	
	public Map<String, Object> queryCustomersContribution(CustomerDtoQuery customerDtoQuery){
		PageHelper.startPage(customerDtoQuery.getPage(), customerDtoQuery.getRows());
		List<CustomerDto> customerDtos=  customerDao.queryCustomersContribution(customerDtoQuery);
		PageInfo<CustomerDto> pageInfo=new PageInfo<CustomerDto>(customerDtos);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	
	public Map<String, Object> queryCustomersGc(){
		List<CustomerDto> customerDtos= customerDao.queryCustomersGc();
		/**
		 * 1.横轴 级别数据  数组
		 * 2.纵轴 每个级别 对应的客户数量 数组
		 */
		String[] levels=null;
		Integer[] counts=null;
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("code", 300);
		if(null!=customerDtos&& customerDtos.size()>0){
			levels=new String[customerDtos.size()];
			counts=new Integer[customerDtos.size()];
			
			for(Integer i=0;i<customerDtos.size();i++){
				CustomerDto customerDto=customerDtos.get(i);
				levels[i]=customerDto.getLevel();
				counts[i]=customerDto.getCount();
			}
			/*map.put("levels", levels);
			map.put("counts", counts);*/
		    map.put("code",200);
		}
		map.put("levels", levels);
		map.put("counts", counts);
		return map;
	}
	
	
	
} 
