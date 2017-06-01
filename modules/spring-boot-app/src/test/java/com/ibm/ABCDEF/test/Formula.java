package com.ibm.ABCDEF.test;

@FunctionalInterface
public interface Formula {

	double calurate(int a);

	default double sqrl(int a) {
		return a * a;
	}

	static void doud() {
	}
}
