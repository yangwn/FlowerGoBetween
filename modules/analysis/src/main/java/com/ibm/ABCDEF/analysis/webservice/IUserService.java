package com.ibm.ABCDEF.analysis.webservice;

import java.util.List;

import javax.jws.WebService;

import com.ibm.ABCDEF.analysis.domain.User;

@WebService
public interface IUserService {
	
	public User getUserById(String id);

	public List<User> getAllUsers();
}
