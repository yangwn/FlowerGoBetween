package com.ibm.ABCDEF.test;

public interface Defaulable {

	default String create() {
		return "Defaulable create";
	}

	String get();
}
