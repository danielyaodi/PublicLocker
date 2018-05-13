package com.publiclockerserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LockerRequestDaoImpl implements LockerRequestDao {

	@Override
	public List<Map<String, String>> addressQuery(String apiKey, String orderNumber, int cellType, int packageQty,
			int[] zipcode) {
		String zipStr = Arrays.toString(zipcode); // convert zipcode array to zipcode string;
		zipStr = zipStr.substring(1, zipStr.length() - 1); // remove [ ]from converted string;
		Connection conn = C3p0Utils.getConnection();
		String getCell = "select cellID,lockerID from CellInfo where lockerID in "
				+ "(select lockerID from LockerAddress where zipcode in (" + zipStr + ") and cellHealth = 1 and "
				+ "cellAvailability=1 and cellCommitted=0 and cellType =" + cellType + ")";

		ResultSet cellRS = C3p0Utils.getResultSet(conn, getCell); // get cellID and lockerID to RS;

		HashMap<String, String> cellMap = new HashMap<>();
		try {
			while (cellRS.next()) {
				cellMap.put(cellRS.getString("cellID"), cellRS.getString("lockerID")); // export cellID and lockerID to
																						// Map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HashMap<String, Integer> lockerCount = new HashMap<>();
		for (Map.Entry<String, String> entry : cellMap.entrySet()) {
			if (lockerCount.containsKey(entry.getValue())) {
				lockerCount.put(entry.getValue(), lockerCount.get(entry.getValue()) + 1); // count how many lockerID
																							// qualify for the
																							// packageQty;

			} else {
				lockerCount.put(entry.getValue(), 1);
			}

		}

		List<String> lockerList = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : lockerCount.entrySet()) {
			if (entry.getValue() >= packageQty) {
				lockerList.add(entry.getKey()); // export all qualified lockerID to a list;

			}

		}
		String lockerStr = String.join(",", lockerList); // convert the list to string for sql;

		String sql = "select street, city,state, zipcode from LockerAddress where lockerID in (" + lockerStr + ")";
		ResultSet addressRS = C3p0Utils.getResultSet(conn, sql); // get address columns to RS;
		List<Map<String, String>> addressList = getAddressMap(addressRS); // call this method to export address columns
																			// to Map list.

		commitRecorder(conn,cellMap, lockerList, orderNumber, apiKey, packageQty);
		C3p0Utils.close(cellRS);
		C3p0Utils.close(addressRS);
		C3p0Utils.close(conn);
		
		
		return addressList;
	}

	private void commitRecorder(Connection conn,HashMap<String, String> cellMap, List<String> lockerList, String orderNumber,
			String apiKey, int packageQty) {
		List<String> list = new ArrayList<String>();
		List<String> cellCommitList = new ArrayList<String>();

		for (String locker : lockerList) {
			List<String> valueList = new ArrayList<String>();
			int counter = 0; // count how many cellID get with same lockerID
			for (Map.Entry<String, String> entry : cellMap.entrySet()) {
				if (counter < packageQty && locker.equals(entry.getValue())) { // check if the current entry's
																				// value(lockerID) == locker(current
																				// lockerID);
					/**
					 * insert format: ('cellID','orderNumber','apiKey',packageQty,'lockerID') for
					 * each record.
					 */
					// add these values to a list, for String.join method
					valueList.add("'" + entry.getKey() + "'");
					valueList.add("'" + orderNumber + "'");
					valueList.add("'" + apiKey + "'");
					valueList.add(String.valueOf(packageQty));
					valueList.add("'" + locker + "'");
					cellCommitList.add("'" + entry.getKey() + "'");
					// setCellCommited(cellCommitList);

					counter++;
				}

			}
			list.add("(" + String.join(",", valueList) + ")"); // convert the list to the insert format for one record,
																// and add to another list.
		}

		String values = String.join(",", list); // covert the list to sql insert format for multiple record.

		String commitSql = "INSERT INTO AssignedToCustomer (cellID, orderNumber,apiKey,packageQty,lockerID) VALUES "
				+ values;
		String cellCommittedSql = "UPDATE LockerCellInfo SET cellCommitted = 1 WHERE cellID in ( "
				+ String.join(",", cellCommitList) + ")";

		C3p0Utils.executeQuery(conn,commitSql); // insert committed cellIDs to AssignedToCustomer table;
		C3p0Utils.executeQuery(conn,cellCommittedSql); // change LockerCellInfo table's cellCommitted to 1;
		purgeTimer.purgeCommittedCellTimer(orderNumber, cellCommitList);

	}

	// private void setCellCommited(List<String> cellCommitList) {
	// ;
	// Connection conn = C3p0Utils.getConnection();
	// PreparedStatement pstmt = C3p0Utils.getPstmt(conn, sql);
	// try {
	// pstmt.executeUpdate();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// if (pstmt != null) {
	// C3p0Utils.close(pstmt);
	// }
	// if (conn != null) {
	// C3p0Utils.close(conn);
	// }
	//
	// }

	private List<Map<String, String>> getAddressMap(ResultSet rs) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		try {
			while (rs.next()) {
				map.put("street", rs.getString("street"));
				map.put("city", rs.getString("city"));
				map.put("state", rs.getString("state"));
				map.put("zipcode", rs.getString("zipcode"));
				list.add(map);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

}
