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
		String getCellAndLockerSQL = SQLstatement.getCellAndLockerSQL(zipStr, cellType);

		ResultSet cellRS = C3p0Utils.getResultSet(conn, getCellAndLockerSQL); // get cellID and lockerID to RS;

		HashMap<String, String> cellMap = new HashMap<>();
		try {
			while (cellRS.next()) {
				cellMap.put(cellRS.getString("cellID"), cellRS.getString("lockerID")); // export cellID and lockerID to
																						// Map;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		HashMap<String, Integer> lockerCountMap = new HashMap<String, Integer>();
		lockerCounter(cellMap, lockerCountMap);

		List<String> lockerList = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : lockerCountMap.entrySet()) {
			if (entry.getValue() >= packageQty) {
				lockerList.add(entry.getKey()); // export all qualified lockerID to a list;

			}

		}
		String lockerStr = String.join(",", lockerList); // convert the list to string for sql;

		String sql = SQLstatement.getLockerAddressSQL(lockerStr);
		ResultSet addressRS = C3p0Utils.getResultSet(conn, sql); // get address columns to RS;
		// List<Map<String, String>> addressList = getAddressMap(addressRS); // call
		// this method to export address columns
		// to Map list.

		commitRecorder(cellMap, lockerList, orderNumber, apiKey, packageQty);
		C3p0Utils.close(cellRS);
		C3p0Utils.close(addressRS);
		C3p0Utils.close(conn);

		return BeanUtils.getJsonStringFromRS(addressRS);
	}

	private HashMap<String, Integer> lockerCounter(HashMap<String, String> cellMap,
			HashMap<String, Integer> lockerCountMap) {

		for (Map.Entry<String, String> entry : cellMap.entrySet()) {
			if (lockerCountMap.containsKey(entry.getValue())) {
				lockerCountMap.put(entry.getValue(), lockerCountMap.get(entry.getValue()) + 1); // count how many
																								// lockerID
				// qualify for the
				// packageQty;

			} else {
				lockerCountMap.put(entry.getValue(), 1);
			}

		}

		return null;
	}

	private void commitRecorder(HashMap<String, String> cellMap, List<String> lockerList, String orderNumber,
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

		insertAssignedToCustomer(list); // insert committed cellIDs to AssignedToCustomer table;

		cellCommittedToOne(cellCommitList); // change LockerCellInfo table's cellCommitted to 1;
		purgeTimer.purgeCommittedCellTimer(orderNumber, cellCommitList);

	}

	private void cellCommittedToOne(List<String> cellCommitList) {
		String cellCommittedSql = SQLstatement.cellCommittedToOneSQL(cellCommitList);

		C3p0Utils.executeUpdate(conn, cellCommittedSql);

	}

	private void insertAssignedToCustomer(List<String> list) {
		String commitSQL = SQLstatement.commitSQL + String.join(",", list);
		C3p0Utils.executeUpdate(conn, commitSQL);

	}

	// private List<Map<String, String>> getAddressMap(ResultSet rs) {
	// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	// Map<String, String> map = new HashMap<String, String>();
	// try {
	// while (rs.next()) {
	// map.put("lockerID", rs.getString("lockerID"));
	// map.put("street", rs.getString("street"));
	// map.put("city", rs.getString("city"));
	// map.put("state", rs.getString("state"));
	// map.put("zipcode", rs.getString("zipcode"));
	// list.add(map);
	//
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// return list;
	// }

}
