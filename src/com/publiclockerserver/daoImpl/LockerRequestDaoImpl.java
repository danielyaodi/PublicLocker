package com.publiclockerserver.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publiclockerserver.SQLstatement;
import com.publiclockerserver.purgeTimer;
import com.publiclockerserver.dao.LockerRequestDao;
import com.publiclockerserver.pojo.AddressRequest_VO;
import com.publiclockerserver.utils.BeanUtils;
import com.publiclockerserver.utils.C3p0Utils;

public class LockerRequestDaoImpl implements LockerRequestDao {
	public LockerRequestDaoImpl(AddressRequest_VO address) {
		super();
		this.apiKey = address.getApiKey();
		this.orderNumber = address.getOrderNumber();
		this.cellType = address.getPackageType();
		this.packageQty = address.getPackageQty();
		this.zipcode = address.getZipcode();
	}

	private String apiKey;
	private String orderNumber;
	private int cellType;
	private int packageQty;
	private int[] zipcode;
	Connection conn = C3p0Utils.getConnection();

	@Override
	public String addressQuery() {
		String zipStr = Arrays.toString(zipcode); // convert zipcode array to zipcode string;
		zipStr = zipStr.substring(1, zipStr.length() - 1); // remove [ ]from converted string;
		String getLockerRSSQL = SQLstatement.getCellAndLockerSQL(zipStr, cellType); // complex SQL; get count(cellID),
																					// lockerID
		ArrayList<String> lockerList = new ArrayList<String>();
		ResultSet lockerRS = C3p0Utils.getResultSet(conn, getLockerRSSQL); // get cellID count and lockerID to RS;

		try {
			while (lockerRS.next()) {
				if (lockerRS.getInt(1) >= packageQty) {
					lockerList.add(lockerRS.getString("lockerID"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String lockerListStr = String.join(",", lockerList); // convert the list to string for SQL;formal
																// value1,value2..
		String lockerAddressSQL = SQLstatement.getLockerAddressSQL(lockerListStr);
		lockerRS = C3p0Utils.getResultSet(conn, lockerAddressSQL);// use lockerRS receive lockerAddress
		String lockerAddressJsonStr = BeanUtils.getJsonStringFromRS(lockerRS); // convert lockerRS to Json for sending
																				// to client

		insertAssignedToCustomer(lockerListStr); // three things to do: insert all to AssignedTocustomer;
													// cellCommitted->1
		// inlockerCellInfo; purge Timer;

		C3p0Utils.close(lockerRS);
		C3p0Utils.close(conn);

		return lockerAddressJsonStr;
	}

	// insert committed cellIDs to AssignedToCustomer table;
	private void insertAssignedToCustomer(String lockerListStr) {

		String cellIDSQL = SQLstatement.getSelectInLimitSQL("cellID,lockerID", "LockerCellInfo", "lockerID",
				lockerListStr, packageQty);
		// SQL for select cellID RS from LockerCellInfo table

		ResultSet cellRS = C3p0Utils.getResultSet(conn, cellIDSQL);// get all cellID for LockerID to RS

		String insertToCommitSQL = SQLstatement.getInsertSQL("AssignedToCustomer", "?,?,?,?,?");
		// SQL insert all into AssignedToCustomer

		PreparedStatement pstmt = C3p0Utils.getPstmt(conn, insertToCommitSQL);
		// pstmt for insert into AssignedToCustomer;

		ArrayList<String> cellCommitList = new ArrayList<String>(); // for changing commit->1 in lockerCellInfo use;

		try {
			while (cellRS.next()) {

				String cellID = cellRS.getString("cellID");
				String lockerID = cellRS.getString("lockerID");
				cellCommitList.add(cellID); // export cellIDs to cellList;
				pstmt.setString(1, cellID);
				pstmt.setString(2, lockerID);
				pstmt.setString(3, orderNumber);
				pstmt.setString(4, apiKey);
				pstmt.setInt(5, packageQty);
				pstmt.addBatch(); // looping to insert all cellIDs to AssignedToCustomer;
			}

			pstmt.executeBatch(); // submit all;

			cellCommittedToOne(cellCommitList); // change LockerCellInfo table's cellCommitted to 1;

			purgeTimer.purgeCommittedCellTimer(orderNumber, cellCommitList); // setup purge task;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Utils.close(cellRS);
			C3p0Utils.close(pstmt);

		}

	}

	// change LockerCellInfo table's cellCommitted to 1;
	private void cellCommittedToOne(List<String> cellCommitList) {
		String cellCommittedSQL = SQLstatement.getUpdateInSQL("LockerCellInfo", "cellCommitted", "1", "cellID",
				String.join(",", cellCommitList));
		C3p0Utils.executeUpdate(conn, cellCommittedSQL);

	}

}
