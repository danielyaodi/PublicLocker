package com.publiclockerserver;

public class Order_VO {
	private String apiKey,orderNumber,lockerID;

	private Order_VO(String apiKey, String orderNumber, String lockerID) {
		super();
		this.apiKey = apiKey;
		this.orderNumber = orderNumber;
		this.lockerID = lockerID;
	}

	String getApiKey() {
		return apiKey;
	}

	String getOrderNumber() {
		return orderNumber;
	}

	String getLockerID() {
		return lockerID;
	}
}
