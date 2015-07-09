package com.acme.order.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	List<Customer> findByCustomerTypeName(@Param("name") String name);
	
	@Query("select c from Customer c where c.name=:name")
	List<Customer> findByCustomerName(@Param("name") String name);
	
}
