package com.ibm.ABCDEF.test.dao;

import java.util.function.Function;

public class MainTester {

	public static void main(String[] args) {
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
		testFunction();
	}

	public static void testFunction() {
		// 简单的,只有一行
		Function<Integer, String> function1 = (x) -> {
			System.out.println("test result: " + x);
			return "test result: " + x;
		};

		// 标准的,有花括号, return, 分号.
		Function<String, String> function2 = (x) -> {
			return "after function1: " + x;
		};
		System.out.println(function1.apply(6));
		System.out.println(function1.andThen(function2).apply(7));
	}

}
