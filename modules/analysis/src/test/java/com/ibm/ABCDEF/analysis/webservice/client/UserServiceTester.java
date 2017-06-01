package com.ibm.ABCDEF.analysis.webservice.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.ABCDEF.analysis.SpringTransactionalContextTest;
import com.ibm.ABCDEF.analysis.webservice.IUserService;

public class UserServiceTester extends SpringTransactionalContextTest {

	/**
	 * option1: 使用配置方式调用: cxf-conf/cxf-config.xml
	 */
	@Autowired
	private IUserService userServiceClient;

	@Test
	public void sendVerifyCodeByTelNumber() {
		userServiceClient.getUserById("11000");
	}

	/**
	 * option2: 使用代码方式调用
	 */
	@Test
	public void test() {
		JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
		svr.setServiceClass(IUserService.class);
		svr.setAddress("http://localhost:8080/analysis/webservice/HelloWorld");
		IUserService hw = (IUserService) svr.create();
		System.out.println(hw.getUserById("100"));
	}
}
