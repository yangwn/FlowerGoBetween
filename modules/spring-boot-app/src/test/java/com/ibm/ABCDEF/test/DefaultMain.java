package com.ibm.ABCDEF.test;

public class DefaultMain {

	public static void main(String[] args) {
		Defaulable defaultableImpl = DefaulableFactory.create(DefaultableImpl::new);
		System.out.println(defaultableImpl.get());

		Defaulable defaultable2Impl = DefaulableFactory.create(Defaultable2Impl::new);
		System.out.println(defaultable2Impl.get());

	}

}
