package com.publiclockerserver.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.publiclockerserver.SQLstatement;
import com.publiclockerserver.dao.ConfirmOrderDao;
import com.publiclockerserver.pojo.Order_VO;
import com.publiclockerserver.utils.BeanUtils;
import com.publiclockerserver.utils.C3p0Utils;

public class ConfirmOrderDaoImpl implements ConfirmOrderDao {
	public ConfirmOrderDaoImpl(Order_VO order) {
		super();
		this.apiKey = order.getApiKey();
		this.orderNumber = order.getOrderNumber();
		this.lockerID = order.getLockerID();
		confirmOrder();
	}

	private String apiKey, orderNumber, lockerID;
	Connection conn = C3p0Utils.getConnection();
	ResultSet cellRS;
	String cellStr;

	@Override
	public Map<String, String> confirmOrder() {
		// update all related cellAvailability =0;
		Map<String, String> codeMap = new HashMap<String, String>();
		purgeAssignedToCustomer(); // purge all assigned record from AssignedToCustomer table within this
									// orderNumber;
		updateCellAvailability();// update cellAvailablity =0, cellCommitted=0 in LockerCellInfo table;

		// ***************Cancel PurgeTimer***************

		String deliveryCode = BeanUtils.getShortUUID();
		String pickupCode = BeanUtils.getShortUUID();

		// insert record to ConfirmedOrder table
		insertRecord(cellStr, deliveryCode, pickupCode); // cellStr will be initialized in updateCellAvailability()
															// method;
		codeMap.put("delvieryCode", deliveryCode);
		codeMap.put("pickupCode", pickupCode);

		C3p0Utils.close(conn);
		C3p0Utils.close(cellRS);

		return codeMap;
	}

	private void updateCellAvailability() {
		String getCellSql = SQLstatement.getCellSQL(lockerID);

		cellRS = C3p0Utils.getResultSet(conn, getCellSql);
		cellStr = BeanUtils.getStringFromRS(cellRS, "cellID");
		String updateLockerSQL = SQLstatement.updateLockerSQL(cellStr);

		C3p0Utils.executeUpdate(conn, updateLockerSQL);

	}

	private void purgeAssignedToCustomer() {
		String removeAssignedSQL = SQLstatement.removeAssignedSQL(orderNumber);
		C3p0Utils.executeUpdate(removeAssignedSQL);
	}

	private void insertRecord(String cellStr, String deliveryCode, String pickupCode) {
		String[] cellArray = cellStr.split(":");

		try {
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		PreparedStatement pstmt = C3p0Utils.getPstmt(conn, SQLstatement.confirmOrderSQL); // Prepare to insert
																							// (?.?,?,?,?,now());
		try {
			for (int i = 0; i < cellArray.length; i++) {
				pstmt.setString(1, cellArray[i]);
				pstmt.setString(2, lockerID);
				pstmt.setString(3, orderNumber);
				pstmt.setString(4, deliveryCode);
				pstmt.setString(5, pickupCode);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.close(pstmt);
		}
	}

}
