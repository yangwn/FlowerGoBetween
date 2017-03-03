package com.ibm.ABCDEF.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ComputeService {
	
	@Autowired
	private ComputeClient computeClient;

	@HystrixCommand(fallbackMethod = "addServiceFallback")
	public String addService() {
		return computeClient.hello();
	}

	public String addServiceFallback() {
		return "error";
	}
}
