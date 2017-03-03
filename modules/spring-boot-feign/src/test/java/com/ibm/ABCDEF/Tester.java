package com.ibm.ABCDEF;

import com.netflix.hystrix.HystrixThreadPoolKey;

public class Tester {

	public static void main(String[] args) {
		HystrixThreadPoolKey d = HystrixThreadPoolKey.Factory.asKey("dsds");
		System.out.println(d.name());
	}

}
