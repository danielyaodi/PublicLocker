package com.publiclockerserver.dao;

public interface ClientDao {
	public Boolean clientLogin(String userName, String password,String apiKey);

	public void clientRegister(String userName, String password, String customerName);
}
