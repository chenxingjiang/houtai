package com.shsxt.crm.dao;

import java.util.List;

import com.shsxt.crm.dto.CustomerServeDto;
import com.shsxt.crm.query.CustomerServeQuery;
import com.shsxt.crm.vo.CustomerServe;

public interface CustomerServeDao {

    int insert(CustomerServe record);

    CustomerServe queryById(Integer id);

    int update(CustomerServe record);
    
    public List<CustomerServe> queryCustomerServesByParams(CustomerServeQuery customerServeQuery);

    
    
    public List<CustomerServeDto> queryCustomerServeDtos();
} 