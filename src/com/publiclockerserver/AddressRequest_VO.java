package com.publiclockerserver;

public class AddressRequest_VO {

	AddressRequest_VO(String apiKey, String orderNumber, int packageType, int packageQty, int []zipcode) {
		super();
		this.apiKey = apiKey;
		this.orderNumber = orderNumber;
		this.packageType = packageType;
		this.packageQty = packageQty;
		this.zipcode = zipcode;
	}

	private String apiKey, orderNumber;
	private int packageType, packageQty;
	private int[]zipcode;

	String getApiKey() {
		return apiKey;
	}

	String getOrderNumber() {
		return orderNumber;
	}

	int getPackageType() {
		return packageType;
	}

	int getPackageQty() {
		return packageQty;
	}

	int[] getZipcode() {
		return zipcode;
	}

}
