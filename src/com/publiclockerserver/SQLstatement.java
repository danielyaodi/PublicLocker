package com.publiclockerserver;

public class SQLstatement {
	static String confirmOrderSQL = "INSERT INTO ConfirmedOrder VALUES(?,?,?,?,?,?,now.())";

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

	static String getCellAndLockerSQL(String zipStr, int cellType) {
		return "select cellID,lockerID from CellInfo where lockerID in "
				+ "(select lockerID from LockerAddress where zipcode in (" + zipStr + ") and cellHealth = 1 and "
				+ "cellAvailability=1 and cellCommitted=0 and cellType =" + cellType + ")";
	}

}
