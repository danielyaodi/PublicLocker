package com.publiclockerserver.pojo;

public class TerminalCode_VO {
	private String cellID;
	private String code;

	TerminalCode_VO(String cellID, String code) {
		super();
		this.cellID = cellID;
		this.code = code;
	}

	public String getCellID() {
		return cellID;
	}

	public String getCode() {
		return code;
	}
}
