package com.ibm.ABCDEF.web;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "spring-boot-app-service")
public interface ComputeClient {

	@RequestMapping(method = RequestMethod.GET, value = "/hello")
	String hello();
}