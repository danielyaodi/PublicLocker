package com.publiclockerserver.pojo;

public class AddLocker_VO {
	public AddLocker_VO(String street, String city, String state, int zipcode, int cellTypeIQty, int cellTypeIIQty,
			int cellTypeIIIQty) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.cellTypeIQty = cellTypeIQty;
		this.cellTypeIIQty = cellTypeIIQty;
		this.cellTypeIIIQty = cellTypeIIIQty;
	}

	private String street;
	private String city;
	private String state;
	private int zipcode;
	private int cellTypeIQty;
	private int cellTypeIIQty;
	private int cellTypeIIIQty;

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public int getCellTypeIQty() {
		return cellTypeIQty;
	}

	public int getCellTypeIIQty() {
		return cellTypeIIQty;
	}

	public int getCellTypeIIIQty() {
		return cellTypeIIIQty;
	}
}
