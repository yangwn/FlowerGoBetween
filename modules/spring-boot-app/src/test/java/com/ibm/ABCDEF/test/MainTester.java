package com.ibm.ABCDEF.test;

import java.util.Objects;
import java.util.function.Predicate;

public class MainTester {

	public static void main(String[] args) {

		Formula formula = new Formula() {
			@Override
			public double calurate(int a) {
				return sqrl(a);
			}
		};
		System.out.println(formula.calurate(10));

		Predicate<String> predicate = (s) -> s.length() > 0;
	}

}
