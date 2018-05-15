package com.publiclockerserver;

public class TerminalCode_VO {
	private String cellID;
	private String lockerID;
	private String code;
	TerminalCode_VO(String cellID, String lockerID, String code) {
		super();
		this.cellID = cellID;
		this.lockerID = lockerID;
		this.code = code;
	}

	String getCellID() {
		return cellID;
	}

	String getLockerID() {
		return lockerID;
	}

	String getCode() {
		return code;
	}
}
