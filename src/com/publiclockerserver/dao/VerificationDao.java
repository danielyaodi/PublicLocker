package com.publiclockerserver.dao;

public interface VerificationDao {
	boolean verification(String column, String table, String values);
	
	//for clientLogin.jsp,   col1=userName, col2 = password
	boolean verification(String column1, String column2,String table, String value1,String value2,String apiKey);

	int verification(String column, String value);
	
}
