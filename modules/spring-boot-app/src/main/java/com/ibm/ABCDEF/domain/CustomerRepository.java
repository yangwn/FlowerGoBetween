package com.ibm.ABCDEF.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addCustomer(Customer customer) {
		jdbcTemplate.execute("insert into customer(name) values(" + customer.getName() + ")");
		jdbcTemplate.execute("insert into user(username) values(" + customer.getName() + ")");
	}

}
