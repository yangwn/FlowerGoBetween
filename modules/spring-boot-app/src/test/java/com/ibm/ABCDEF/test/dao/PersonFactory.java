package com.ibm.ABCDEF.test.dao;

@FunctionalInterface
public interface PersonFactory<P extends Person> {

	P create(String firstName, String lastName);

}
