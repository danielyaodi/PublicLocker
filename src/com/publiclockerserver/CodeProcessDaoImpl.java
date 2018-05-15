package com.publiclockerserver;

public class CodeProcessDaoImpl implements CodeProcessDao {

	CodeProcessDaoImpl(TerminalCode_VO terminalCode) {

		super();
		this.cellID = terminalCode.getCellID();
		this.deliveryCode = terminalCode.getCode();
		this.LockerID = terminalCode.getLockerID();
	}

	private String cellID;
	private String deliveryCode;
	private String LockerID;

	@Override
	public void deliveryCode(String cellID, String deliveryCode, String LockerID) {
		insertHistory(cellID, deliveryCode);
		removeCodeFromConfirmedOrder(cellID);
	}

	private void removeCodeFromConfirmedOrder(String cellID) {
		String removeDeliveryCodeSQL = SQLstatement.removeDeliveryCodeSQL(cellID); // remove deliveryCode in
																					// ConfirmedOrder SQL
		C3p0Utils.executeUpdate(removeDeliveryCodeSQL); // remove
	}

}
