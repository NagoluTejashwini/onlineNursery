package com.ec.onlineplantnursery.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ec.onlineplantnursery.customer.entity.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer>{
	
}
