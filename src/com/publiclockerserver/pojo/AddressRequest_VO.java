package com.publiclockerserver.pojo;

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

	public String getApiKey() {
		return apiKey;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public int getPackageType() {
		return packageType;
	}

	public int getPackageQty() {
		return packageQty;
	}

	public int[] getZipcode() {
		return zipcode;
	}

}
