package com.prashant.customer.Service;

import com.prashant.customer.Entity.Customer;
import com.prashant.customer.Helper.CustomerResponse;

public interface CustomerService {

	public Customer addCustomer(Customer customer);
	public Customer updateCustomer(Customer customer,Long cId);
	public void deleteCustomer(Long cId);
	public CustomerResponse getAllCustomer(Integer pageNumber,Integer pagesize, String sortBy, String sortDirection);
	public Customer getCustomer(Long cId);
	public CustomerResponse searchCustomer(String firstName,String email,String phone,String city,String lastName,Integer pageNumber, Integer pageSize, String sortBy);
}
