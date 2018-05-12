package com.publiclockerserver;

import java.sql.Connection;
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
		String zipStr = Arrays.toString(zipcode);
		zipStr = zipStr.substring(1, zipStr.length() - 1);
		Connection conn = C3p0Utils.getConnection();
		String getCell = "select cellID,lockerID from CellInfo where lockerID in "
				+ "(select lockerID from LockerAddress where zipcode in (" + zipStr + ") and cellHealth = 1 and "
				+ "cellAvailability=1 and cellCommitted=0 and cellType =" + cellType + ")";

		ResultSet cellRS = C3p0Utils.getResultSet(conn, getCell);

		HashMap<String, String> cellMap = new HashMap<>();
		try {
			while (cellRS.next()) {
				cellMap.put(cellRS.getString("cellID"), cellRS.getString("lockerID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HashMap<String, Integer> lockerCount = new HashMap<>();
		for (Map.Entry<String, String> entry : cellMap.entrySet()) {
			if (lockerCount.containsKey(entry.getValue())) {
				lockerCount.put(entry.getValue(), lockerCount.get(entry.getValue()) + 1);

			} else {
				lockerCount.put(entry.getValue(), 1);
			}

		}

		List<String> lockerList = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : lockerCount.entrySet()) {
			if (entry.getValue() >= packageQty) {
				lockerList.add(entry.getKey());

			}

		}
		String lockerStr = String.join(",", lockerList);

		String sql = "select street, city,state, zipcode from LockerAddress where lockerID in (" + lockerStr + ")";
		ResultSet addressRS = C3p0Utils.getResultSet(conn, sql);
		List<Map<String, String>> addressList = getAddressMap(addressRS);
		return addressList;
	}

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
