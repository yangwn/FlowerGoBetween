package com.ibm.ABCDEF.test;

import java.util.function.Supplier;

public interface DefaulableFactory {

	public static Defaulable create(Supplier<Defaulable> supplier) {
		return supplier.get();
	}

}
