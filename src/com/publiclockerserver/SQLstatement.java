package com.publiclockerserver;

import java.util.List;

import com.publiclockerserver.utils.BeanUtils;

public class SQLstatement {
	public static String confirmOrderSQL = "INSERT INTO ConfirmedOrder VALUES(?,?,?,?,?,?,now.())";
	public static String commitSQL = "INSERT INTO AssignedToCustomer (cellID, orderNumber,apiKey,packageQty,lockerID) VALUES ";

	public static String insertNewLockerSQL = getInsertSQL("LockerAddress", "?,?,?,?,?,now.()");
	public static String insertNewCellSQL = getInsertSQL("LockerCellInfo", "?,?,?,1,0,1");
	public static String showAllLockersSQL = getSelectSQL("*", "LockerAddress");
	public static String UpdateLockerAddressSQL = "UPDATE LockerAddress SET street =?,city=?,state=?,zipcode=? WHERE lockerID=?";

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

		return "INSERT INTO" + table + "VALUES(" + value + ")";
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
		return "UPDATE" + table + "SET (" + columns + ") VALUES(" + values + ") WHERE" + whereColumn + "=" + whereValue;
	}

	static String getSelectSQL(String columns, String table, String whereColumn, String whereValue) {
		return "SELECT" + columns + "FROM" + table + "WHERE" + whereColumn + "=" + whereValue;
	}

	static String getSelectSQL(String columns, String table) {
		return "SELECT" + columns + "FROM" + table;
	}

	static String insertFromTable(String destTable, String destColumn, String origTable, String origColumn,
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

	public static String verificationSQL(String column, String table, String value) {
		return "select" + column + " from " + table + " where" + column + " ='" + value + "'";
	}

	public static String codeVerificationSQL(String code) {
		return "SELECT cellID, deliveryCode, pickupCode FROM ConfirmedOrder WHERE cellID ='" + code + "'";
	}

	public static String getCellAndLockerSQL(String zipStr, int cellType) {
		return "select cellID,lockerID from CellInfo where lockerID in "
				+ "(select lockerID from LockerAddress where zipcode in (" + zipStr + ") and cellHealth = 1 and "
				+ "cellAvailability=1 and cellCommitted=0 and cellType =" + cellType + ")";
	}

	public static String getLockerAddressSQL(String lockerStr) {
		return "select lockerID,street, city,state, zipcode from LockerAddress where lockerID in (" + lockerStr + ")";
	}

	public static String cellCommittedToOneSQL(List<String> cellCommitList) {
		return "UPDATE LockerCellInfo SET cellCommitted = 1 WHERE cellID in ( " + String.join(",", cellCommitList)
				+ ")";
	}

	public static String cellCommittedToZeroSQL(List<String> cellCommitList) {
		return "UPDATE LockerCellInfo SET cellCommitted = 0 WHERE cellID in ( " + String.join(",", cellCommitList)
				+ ")";
	}

	public static String removeDeliveryCodeSQL(String cellID) {
		return "UPDATE ConfirmedOrder SET deliveryCode = NULL WHERE cellID = '" + cellID + "'";
	}

	public static String removeCommitSQL(String orderNumber) {
		return "DELETE FROM AssignedToCustomer WHERE orderNumber = '" + orderNumber + "'";
	}

}
