package com.prashant.customer.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prashant.customer.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	
	public Page<Customer> findByFirstNameOrEmailOrPhoneOrCityOrLastName(
	        String firstName, String email, String phone, String city, String lastName, Pageable pageable);

}
