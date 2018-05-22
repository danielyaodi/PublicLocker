package com.publiclockerserver.pojo;

public class Order_VO {
	private String apiKey, orderNumber, lockerID;

	private Order_VO(String apiKey, String orderNumber, String lockerID) {
		super();
		this.apiKey = apiKey;
		this.orderNumber = orderNumber;
		this.lockerID = lockerID;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getLockerID() {
		return lockerID;
	}
}
