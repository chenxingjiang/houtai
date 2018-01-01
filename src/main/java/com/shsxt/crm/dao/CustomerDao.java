package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.dto.CustomerDto;
import com.shsxt.crm.query.CustomerDtoQuery;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.vo.Customer;

public interface CustomerDao {


    int insert(Customer record);

    Customer queryById(Integer id);

    int update(Customer record);

    
    @Select("select id,name from t_customer where state=0 and is_valid=1 ")
	public List<Customer> queryAllCustomers();
    
    public List<Customer> queryCustomersByParams(CustomerQuery customerQuery);
    
    
    public Integer deleteCustomers(Integer[] ids);
    
    
    
    /**
     * 查询流失客户记录
     * @return
     */
    public List<Customer> queryLossCustomers();
    
    /**
     * 更新客户流失状态
     * @param ids
     * @return
     */
    public Integer updateCustomerLossState(Integer[] ids);
    
    
    public List<CustomerDto> queryCustomersContribution(CustomerDtoQuery customerDtoQuery);
    
    
    
    public List<CustomerDto> queryCustomersGc();
}