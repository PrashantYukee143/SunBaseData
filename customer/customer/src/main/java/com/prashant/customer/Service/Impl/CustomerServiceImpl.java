package com.prashant.customer.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prashant.customer.Entity.Customer;
import com.prashant.customer.Exception.CustomerNotFoundException;
import com.prashant.customer.Helper.CustomerResponse;
import com.prashant.customer.Repository.CustomerRepository;
import com.prashant.customer.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public Customer addCustomer(Customer customer) {
		
		return this.customerRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer,Long cId) {
		
		Optional<Customer> existing_customer_optional=this.customerRepo.findById(cId);
		
		if(existing_customer_optional.isPresent())
		{
			Customer existingCustomer=existing_customer_optional.get();
			
			existingCustomer.setFirstName(customer.getFirstName());
			existingCustomer.setLastName(customer.getLastName());
			existingCustomer.setStreet(customer.getStreet());
			existingCustomer.setAddress(customer.getAddress());
			existingCustomer.setCity(customer.getCity());
			existingCustomer.setState(customer.getState());
			existingCustomer.setEmail(customer.getEmail());
			existingCustomer.setPhone(customer.getPhone());
			
			return this.customerRepo.save(existingCustomer);
		}
		else
		{
			throw new CustomerNotFoundException("Customer with Id "+cId+" not found");
		}
		
	}

	@Override
	public void deleteCustomer(Long cId) {
		
		this.customerRepo.deleteById(cId);
		
	}

	@Override
	public CustomerResponse getAllCustomer(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		
		Sort sort=null;
		
		if(sortDirection.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending();
		}
		
		Pageable p =PageRequest.of(pageNumber,pageSize,sort);
		
		Page<Customer> pageCustomer=this.customerRepo.findAll(p);
		List<Customer> allCustomer=pageCustomer.getContent();
		
		CustomerResponse customerResponse=new CustomerResponse();
		
		customerResponse.setContent(allCustomer);
		customerResponse.setPageNumber(pageCustomer.getNumber());
		customerResponse.setPageSize(pageCustomer.getSize());
		customerResponse.setTotalElements(pageCustomer.getTotalElements());
		customerResponse.setTotalPages(pageCustomer.getTotalPages());
		customerResponse.setLastPage(pageCustomer.isLast());
		
		
		return customerResponse;
	}

	@Override
	public Customer getCustomer(Long cId) {
		
		return this.customerRepo.findById(cId).get();
	}

	@Override
	public CustomerResponse searchCustomer(
	        String firstName, String email, String phone, String city, String lastName,
	        Integer pageNumber, Integer pageSize, String sortBy) {

	    Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

	    Page<Customer> pageCustomer = this.customerRepo.findByFirstNameOrEmailOrPhoneOrCityOrLastName(
	            firstName, email, phone, city, lastName, pageable);

	    List<Customer> searchedCustomer = pageCustomer.getContent();

	    CustomerResponse customerResponse = new CustomerResponse();

	    customerResponse.setContent(searchedCustomer);
	    customerResponse.setPageNumber(pageCustomer.getNumber());
	    customerResponse.setPageSize(pageCustomer.getSize());
	    customerResponse.setTotalElements(pageCustomer.getTotalElements());
	    customerResponse.setTotalPages(pageCustomer.getTotalPages());
	    customerResponse.setLastPage(pageCustomer.isLast());

	    return customerResponse;
	}

}
