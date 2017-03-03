package com.ibm.ABCDEF.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

	private Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ComputeClient computeClient;

	@Autowired
	private ComputeService computeService;

	@RequestMapping(value = "callhello", method = RequestMethod.GET)
	public String index2() {
		String body = restTemplate.getForEntity("http://spring-boot-app-service/hello", String.class).getBody();
		logger.info("body : {}", body);
		return "body";
	}

	@RequestMapping(value = "call", method = RequestMethod.GET)
	public String index() {
		String body = computeClient.hello();
		logger.info("body : {}", body);
		return "body";
	}

	@RequestMapping(value = "hytrix", method = RequestMethod.GET)
	public String index3() {
		String body = computeService.addService();
		logger.info("body : {}", body);
		return body;
	}

}
