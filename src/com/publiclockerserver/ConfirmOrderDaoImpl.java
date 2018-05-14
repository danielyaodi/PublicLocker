package com.publiclockerserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

public class ConfirmOrderDaoImpl implements ConfirmOrderDao {

	@Override
	public Map<String, String> confirmOrder(String apiKey, String orderNumber, String lockerID) {
		// update all related cellAvailability =0;

		Connection conn;
		ResultSet cellRS;
		String getCellSql = "SELECT cellID FROM AsignedToCusomter WHERE lockerID = '" + lockerID + "'";
		conn = C3p0Utils.getConnection();
		cellRS = C3p0Utils.getResultSet(conn, getCellSql);
		String cellStr = C3p0Utils.getStringFromRS(cellRS, "cellID");
		String updateLockerSQL = "UPDATE LockerCellInfo SET cellAvailability =0, cellCommitted = 0 WHERE cellID in ("
				+ cellStr + ")";

		String removeCommitSql = "DELETE FROM AssignedToCustomer WHERE orderNumber = '" + orderNumber + "'";
		C3p0Utils.executeUpdate(removeCommitSql);		//purge all assigned record from AssignedToCustomer table within this orderNumber;
		C3p0Utils.executeUpdate(conn, updateLockerSQL); // update cellAvailablity =0, cellCommitted=0 in LockerCellInfo
														// table;
		 						//Cancel PurgeTimer

		return null;
	}

}
