package com.publiclockerserver.dao;

public interface VerificationDao {
	boolean verification(String column, String table, String values);

	int verification(String column, String value);

}
