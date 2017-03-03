package com.ibm.ABCDEF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication // Spring Boot
@EnableDiscoveryClient // 服务发现
@EnableFeignClients // RPC
@EnableCircuitBreaker // 断容器
@EnableHystrixDashboard // 断容器DashBoard
@EnableZuulProxy // Apigateway
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
