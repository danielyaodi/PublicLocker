package com.publiclockerserver;

import java.util.List;

import com.publiclockerserver.utils.BeanUtils;

public class SQLstatement {
	public static String confirmOrderSQL = "INSERT INTO ConfirmedOrder VALUES(?,?,?,?,?,?,now())";
	// public static String commitSQL = "INSERT INTO AssignedToCustomer
	// (cellID,orderNumber,apiKey,packageQty,lockerID) VALUES "; // for
	// insertAssignedToCustome();

	public static String insertNewLockerSQL = getInsertSQL("LockerAddress", "?,?,?,?,?,now()");
	public static String insertNewCellSQL = getInsertSQL("LockerCellInfo", "?,?,?,1,0,1");
	public static String showAllLockersSQL = getSelectSQL("*", "LockerAddress");
	public static String UpdateLockerAddressSQL = "UPDATE LockerAddress SET street =?,city=?,state=?,zipcode=? WHERE lockerID=?";
	public static String newClientSQL = getInsertSQL("CustomerInfo", "?,?,?,now(),?");

	// for showAllCellsInLocker();
	public static String getShowAllCellsInLockerSQL(String lockerID) {

		return getSelectSQL("*", "LockerCellInfo", "LockerID", lockerID);
	}

	// for deleteLockerSQL();
	public static String getDeleteLockerSQL(String lockerID) {
		return getDeleteSQL("LockerAddress", "lockerID", lockerID);
	}

	// for deleteLockerSQL();
	public static String getDeleteCellsSQL(String lockerID) {
		return getDeleteSQL("LockerCellInfo", "lockerID", lockerID);
	}

	// for deleteOneCell();
	public static String getDeleteOneCellSQL(String cellID) {
		return getDeleteSQL("LockerCellInfo", "cellID", cellID);
	}

	// for insertNewLocker();
	public static String getInsertSQL(String table, String value) {
		return "INSERT INTO " + table + " VALUES (" + value + ")";
	}

	// for insertHistory();
	public static String getInsertHistorySQL(String cellID) {
		String history = Constant.CELL_ID + "," + Constant.LOCKER_ID + "," + Constant.ORDER_NUMBER + ","
				+ Constant.DELIVERY_CODE + "," + Constant.PICKUP_CODE + "," + Constant.ORDER_TIME + ","
				+ Constant.API_KEY;
		return insertFromTable(Constant.HISTORY, history, Constant.CONFIRMED_ORDER, history, Constant.CELL_ID,
				BeanUtils.addSingleQuote(cellID));
	}

	// public method
	public static String getUpdateSQL(String table, String columns, String values, String whereColumn,
			String whereValue) {
		return "UPDATE " + table + " SET (" + columns + ") VALUES( " + values + " ) WHERE" + whereColumn + "="
				+ whereValue;
	}

	// for cellCommitted->1 LockerCellInfo
	public static String getUpdateInSQL(String table, String columns, String values, String whereColumn,
			String whereValue) {
		return "UPDATE " + table + " SET (" + columns + ") VALUES( " + values + " ) WHERE" + whereColumn + "in ("
				+ whereValue + " )";
	}

	public static String getSelectSQL(String columns, String table, String whereColumn, String whereValue) {
		return "SELECT" + columns + "FROM" + table + "WHERE" + whereColumn + "=" + whereValue;
	}

	// using get packageQty cellIDs from LockerCellInfo in lockerIDs;
	public static String getSelectInLimitSQL(String columns, String table, String whereColumn, String whereValue,
			int limit) {
		return "SELECT " + columns + " FROM " + table + " WHERE " + whereColumn + " in( " + whereValue + " ) LIMIT "
				+ limit;
	}

	public static String getSelectSQL(String columns, String table) {
		return "SELECT" + columns + "FROM" + table;
	}

	public static String insertFromTable(String destTable, String destColumn, String origTable, String origColumn,
			String whereColumn, String whereValue) {
		return "INSERT INTO " + destTable + "(" + destColumn + ")" + "SELECT" + origColumn + "FROM" + origTable
				+ "WHERE" + whereColumn + "=" + whereValue;
	}

	public static String getDeleteSQL(String table, String whereColumn, String whereValue) {
		return "DELETE FROM" + table + "WHERE" + whereColumn + "=" + whereValue;
	}

	public static String getCellSQL(String lockerID) {

		return "SELECT cellID FROM AsignedToCusomter WHERE lockerID = '" + lockerID + "'";

	}

	public static String updateLockerSQL(String cellStr) {

		return "UPDATE LockerCellInfo SET cellAvailability =0, cellCommitted = 0 WHERE cellID in (" + cellStr + ")";

	}

	public static String removeAssignedSQL(String orderNumber) {

		return "DELETE FROM AssignedToCustomer WHERE orderNumber = '" + orderNumber + "'";
	}

	// public static String verificationSQL(String column, String table, String
	// value) {
	// return "select" + column + " from " + table + " where" + column + " ='" +
	// value + "'";
	// }
	public static String verificationSQL(String column, String table, String value) {
		return "SELECT COUNT(" + column + ") FROM " + table + " WHERE" + column + " ='" + value + "'";
	}

	public static String verificationSQL(String column1, String column2, String table, String value1, String value2) {
		return "SELECT COUNT(apiKey),apiKey FROM " + table + " WHERE " + column1 + " ='" + value1 + "' and " + column2
				+ " ='" + value2 + "' GROUP BY apiKey";
	}

	public static String codeVerificationSQL(String code) {
		return "SELECT cellID, deliveryCode, pickupCode FROM ConfirmedOrder WHERE cellID ='" + code + "'";
	}

	public static String getCellAndLockerSQL(String zipStr, int cellType) {
		return "SELECT cellID,lockerID FROM LockerCellInfo WHERE lockerID IN "
				+ "(SELECT lockerID FROM LockerAddress WHERE zipcode in (" + zipStr + ")) and cellHealth = 1 and "
				+ "cellAvailability=1 and cellCommitted=0 and cellType =" + cellType;
	}

	public static String getLockerAddressSQL(String lockerStr) {
		return "select lockerID,street, city,state, zipcode from LockerAddress where lockerID in (" + lockerStr + ")";
	}

	public static String removeDeliveryCodeSQL(String cellID) {
		return "UPDATE ConfirmedOrder SET deliveryCode = NULL WHERE cellID = '" + cellID + "'";
	}

	public static String removeCommitSQL(String orderNumber) {
		return "DELETE FROM AssignedToCustomer WHERE orderNumber = '" + orderNumber + "'";
	}

}
