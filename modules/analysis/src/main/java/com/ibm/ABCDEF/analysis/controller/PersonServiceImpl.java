package com.ibm.ABCDEF.analysis.controller;

import com.ibm.ABCDEF.analysis.domain.User;

public class PersonServiceImpl implements PersonService {

	public User readPersonInfoWithUsername(String username) {
		User user = new User();
		user.setName(username);
		user.setId(100);
		return user;
	}
}
