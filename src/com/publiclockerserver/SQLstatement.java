package com.publiclockerserver;

import java.util.List;

public class SQLstatement {
	static String confirmOrderSQL = "INSERT INTO ConfirmedOrder VALUES(?,?,?,?,?,?,now.())";
	static String commitSQL = "INSERT INTO AssignedToCustomer (cellID, orderNumber,apiKey,packageQty,lockerID) VALUES ";

	static String getCellSQL(String lockerID) {

		return "SELECT cellID FROM AsignedToCusomter WHERE lockerID = '" + lockerID + "'";

	}

	static String updateLockerSQL(String cellStr) {

		return "UPDATE LockerCellInfo SET cellAvailability =0, cellCommitted = 0 WHERE cellID in (" + cellStr + ")";

	}

	static String removeAssignedSQL(String orderNumber) {

		return "DELETE FROM AssignedToCustomer WHERE orderNumber = '" + orderNumber + "'";
	}

	static String verificationSQL(String column, String table, String value) {
		return "select" + column + " from " + table + " where" + column + " ='" + value + "'";
	}

	static String codeVerificationSQL(String code) {
		return "SELECT cellID, deliveryCode, pickupCode FROM ConfirmedOrder WHERE cellID ='" + code + "'";
	}

	static String getCellAndLockerSQL(String zipStr, int cellType) {
		return "select cellID,lockerID from CellInfo where lockerID in "
				+ "(select lockerID from LockerAddress where zipcode in (" + zipStr + ") and cellHealth = 1 and "
				+ "cellAvailability=1 and cellCommitted=0 and cellType =" + cellType + ")";
	}

	static String getLockerAddressSQL(String lockerStr) {
		return "select lockerID,street, city,state, zipcode from LockerAddress where lockerID in (" + lockerStr + ")";
	}

	static String cellCommittedToOneSQL(List<String> cellCommitList) {
		return "UPDATE LockerCellInfo SET cellCommitted = 1 WHERE cellID in ( " + String.join(",", cellCommitList)
				+ ")";
	}

	static String cellCommittedToZeroSQL(List<String> cellCommitList) {
		return "UPDATE LockerCellInfo SET cellCommitted = 0 WHERE cellID in ( " + String.join(",", cellCommitList)
				+ ")";
	}

	static String removeCommitSQL(String orderNumber) {
		return "DELETE FROM AssignedToCustomer WHERE orderNumber = '" + orderNumber + "'";
	}

}
