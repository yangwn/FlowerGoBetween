package com.ibm.ABCDEF.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.ABCDEF.domain.Customer;
import com.ibm.ABCDEF.domain.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RedisTemplate<String, String> stringRedisTemplate;

	@Transactional
	public void addCutomer(String name) {

		stringRedisTemplate.boundValueOps("age").set("1");

		Customer customer = new Customer(name);
		customerRepository.addCustomer(customer);
	}
}
