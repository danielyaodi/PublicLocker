package com.publiclockerserver.dao;

public interface CodeProcessDao {

	public void deliveryCode(String cellID);

	public void pickupCode(String cellID);
}
