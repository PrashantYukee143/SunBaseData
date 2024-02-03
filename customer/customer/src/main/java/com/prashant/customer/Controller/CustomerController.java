package com.prashant.customer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prashant.customer.Entity.Customer;
import com.prashant.customer.Helper.CustomerResponse;
import com.prashant.customer.Service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer)
	{
		Customer customer_1=this.customerService.addCustomer(customer);
		
		return ResponseEntity.ok(customer_1);
	}
	
	@GetMapping("/{cId}")
	public Customer getCustomer(@PathVariable("cId") Long cId)
	{
		return this.customerService.getCustomer(cId);
	}
	
	@GetMapping("/")
	public ResponseEntity<CustomerResponse> getAllCustomers(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
			@RequestParam(value = "sortDirection",defaultValue = "asc",required = false) String sortDirection
			)
	{
		return ResponseEntity.ok(this.customerService.getAllCustomer(pageNumber,pageSize,sortBy,sortDirection));
	}
	
	@PutMapping("/{cId}")
	public Customer updateCustomer(@RequestBody Customer customer,@PathVariable("cId")Long cId)
	{
		return this.customerService.updateCustomer(customer,cId);
	}
	
	@DeleteMapping("/{cId}")
	public void deletCustomer(@PathVariable("cId")Long cId)
	{
		this.customerService.deleteCustomer(cId);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<CustomerResponse> searchCustomer(@PathVariable("keyword") String keyword,
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy)
	{
		
		
		return ResponseEntity.ok(this.customerService.searchCustomer(keyword,keyword,keyword,keyword,keyword, pageSize, pageSize, sortBy));
	}
}
